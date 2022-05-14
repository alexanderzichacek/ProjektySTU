package sk.stuba.fei.uim.oop.assignment3.author.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.data.AuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.BookServices;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

@Service
public class AuthorServices implements IAutorServices{
    @Autowired
    private AuthorRepository repository;
    @Autowired
    private BookServices bookServices;

    @Autowired
    public AuthorServices(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Author> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Author postAuthor(AuthorRequest request) {
        Author newAuthor = new Author();
        newAuthor.setName(request.getName());
        newAuthor.setSurname(request.getSurname());

        this.repository.save(newAuthor);
        return newAuthor;
    }

    @Override
    public Author getById(Long id) throws NotFoundException {
        Author author = this.repository.findAuthorById(id);
        if (author == null) throw new NotFoundException();
        return author;
    }

    @Override
    public Author changeAuthor(Long id, AuthorRequest request) throws NotFoundException {
        Author author = this.getById(id);

        if (request.getName() != null) author.setName(request.getName());
        if (request.getSurname() != null) author.setSurname(request.getSurname());

        return this.repository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) throws NotFoundException {
        if (this.repository.findAuthorById(id) == null) throw new NotFoundException();

        this.repository.delete(this.getById(id));
    }

    @Override
    public void addBook(Long bookId, Long authorId) throws NotFoundException{
        Author author = this.repository.findAuthorById(authorId);
        Book book = bookServices.getBookById(bookId);
        if (authorId == null) throw new NotFoundException();
        author.getBooks().add(book);
        this.repository.save(author);
    }

    @Override
    public void removeBook(Long bookId, Long authorId) throws NotFoundException{
        Author author = this.repository.findAuthorById(authorId);
        Book book = bookServices.getBookById(bookId);
        if (bookId == null) throw new NotFoundException();
        author.getBooks().remove(book);
        this.repository.save(author);
    }
}
