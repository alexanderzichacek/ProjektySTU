package sk.stuba.fei.uim.oop.assignment3.list.logic;

import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperation;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendList;

import java.util.List;

public interface IListServices {
    List<LendList> getAllLists();
    LendList postList();
    LendList getListById(Long id) throws NotFoundException;
    void deleteList(Long id) throws NotFoundException;
    LendList addBookToList(Long bookId, Long listId) throws NotFoundException, IllegalOperation;
    LendList removeBookFromList(Long bookId, Long listId) throws NotFoundException;
    void lendListById(Long id) throws NotFoundException, IllegalOperation;
}
