package sk.stuba.fei.uim.oop.assignment3.list.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.BookServices;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperation;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendList;
import sk.stuba.fei.uim.oop.assignment3.list.data.ListRepository;

import java.util.List;

@Service
public class ListServices implements IListServices {
    @Autowired
    private ListRepository repository;
    @Autowired
    private BookServices bookServices;

    @Autowired
    public ListServices(ListRepository repository) {
        this.repository = repository;
    }

    public List<LendList> getAllLists() {
        return this.repository.findAll();
    }

    @Override
    public LendList postList() {
        LendList newList = new LendList();

        newList.setLended(false);

        this.repository.save(newList);
        return newList;
    }

    @Override
    public LendList getListById(Long id) throws NotFoundException {
        LendList lendList = this.repository.findListById(id);
        if (lendList == null) throw new NotFoundException();
        return lendList;
    }

    @Override
    public void deleteList(Long id) throws NotFoundException {
        LendList newList = this.repository.findListById(id);

        if (this.repository.findListById(id) == null) throw new NotFoundException();

        if (this.repository.findListById((id)).isLended()){
            int listSize = 0;
            while (listSize < newList.getLendingList().size()){
                bookServices.lendCount(-1, newList.getLendingList().get(listSize));
                listSize += 1;
            }
        }
        this.repository.delete(newList);
    }

    @Override
    public LendList addBookToList(Long bookId, Long listId) throws NotFoundException, IllegalOperation{
        if (this.repository.findListById(listId) == null) throw new NotFoundException();
        if (bookServices.getBookById(bookId) == null) throw new NotFoundException();

        LendList newList = this.repository.findListById(listId);
        Book book = bookServices.getBookById(bookId);

        if (this.repository.findListById(listId).isLended()) throw new IllegalOperation();
        if (this.repository.findListById(listId).getLendingList().contains(book)) throw new IllegalOperation();

        newList.getLendingList().add(book);

        this.repository.save(newList);
        return newList;
    }

    @Override
    public LendList removeBookFromList(Long bookId, Long listId) throws NotFoundException{
        if (this.repository.findListById(listId) == null) throw new NotFoundException();
        if (this.bookServices.getBookById(bookId) == null) throw new NotFoundException();

        LendList newList = this.repository.findListById(listId);
        Book book = bookServices.getBookById(bookId);

        newList.getLendingList().remove(book);

        this.repository.save(newList);
        return newList;
    }

    @Override
    public void lendListById(Long id) throws NotFoundException, IllegalOperation {
        LendList newList = this.repository.findListById(id);
        if (this.repository.findListById(id) == null) throw new NotFoundException();
        else if (newList.isLended()) throw new IllegalOperation();

        int listSize = 0;
        while (listSize < newList.getLendingList().size()){
            bookServices.lendCount(1, newList.getLendingList().get(listSize));
            listSize += 1;
        }

        newList.setLended(true);

        this.repository.save(newList);
    }
}
