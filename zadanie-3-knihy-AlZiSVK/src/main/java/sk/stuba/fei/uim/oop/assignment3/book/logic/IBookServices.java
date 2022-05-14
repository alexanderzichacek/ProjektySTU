package sk.stuba.fei.uim.oop.assignment3.book.logic;

import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

public interface IBookServices {
    List<Book> getAllBooks();
    Book postBook(BookRequest request) throws NotFoundException;
    Book getBookById(Long id) throws NotFoundException;
    Book changeBook(Long id, BookRequest request) throws NotFoundException;
    void deleteBook(Long id) throws NotFoundException;
    int getAmount(Long id) throws NotFoundException;
    int addAmount(Long id, int incrementation) throws NotFoundException;
    int getLendCount(Long id) throws NotFoundException;
    void lendCount(int plusMinus, Book book) throws NotFoundException;
}
