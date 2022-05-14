package sk.stuba.fei.uim.oop.assignment3.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.logic.AuthorServices;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookRepository;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

@Service
public class BookServices implements IBookServices {
    @Autowired
    private BookRepository repository;
    @Autowired
    private AuthorServices authorServices;

    @Autowired
    public BookServices(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return this.repository.findAll();
    }

    @Override
    public Book postBook(BookRequest request) throws NotFoundException {
        Book newBook = new Book();

        newBook.setName(request.getName());
        newBook.setDescription(request.getDescription());
        if (this.authorServices.getById(request.getAuthor()) == null) throw new NotFoundException();
        newBook.setAuthor(request.getAuthor());
        newBook.setPages(request.getPages());
        newBook.setAmount(request.getAmount());
        newBook.setLendCount(request.getLendCount());

        this.repository.save(newBook);

        this.authorServices.addBook(newBook.getId(), newBook.getAuthor());
        return newBook;
    }

    @Override
    public Book getBookById(Long id) throws NotFoundException {
        Book book = this.repository.findBookById(id);
        if (book == null) throw new NotFoundException();
        return book;
    }

    @Override
    public Book changeBook(Long id, BookRequest request) throws NotFoundException {
        Book book = this.getBookById(id);

        if (request.getName() != null) book.setName(request.getName());
        if (request.getDescription() != null) book.setDescription(request.getDescription());
        if (request.getAuthor() != null && request.getAuthor() != 0) {
            authorServices.removeBook(id, book.getAuthor());
            book.setAuthor(request.getAuthor());
            authorServices.addBook(id, request.getAuthor());
        }
        if (request.getPages() > 0) book.setPages(request.getPages());

        return this.repository.save(book);
    }

    @Override
    public void deleteBook(Long id) throws NotFoundException {
        if (this.repository.findBookById(id) == null) throw new NotFoundException();
        authorServices.removeBook(id, this.repository.findBookById(id).getAuthor());

        this.repository.delete(this.getBookById(id));
    }

    @Override
    public int getAmount(Long id) throws NotFoundException {
        return this.getBookById(id).getAmount();
    }

    @Override
    public int addAmount(Long id, int incrementation) throws NotFoundException {
        Book book = this.getBookById(id);
        book.setAmount(book.getAmount() + incrementation);
        this.repository.save(book);
        return book.getAmount();
    }

    @Override
    public int getLendCount(Long id) throws NotFoundException {
        return this.getBookById(id).getLendCount();
    }

    @Override
    public void lendCount(int plusMinus, Book book) throws NotFoundException {
        this.getBookById(book.getId()).setLendCount(this.getBookById(book.getId()).getLendCount()+plusMinus);
    }
}
