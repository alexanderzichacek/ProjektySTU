package sk.stuba.fei.uim.oop.assignment3.author.logic;

import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.web.body.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;

public interface IAutorServices {
    List<Author> getAll();
    Author postAuthor(AuthorRequest request);
    Author getById(Long id) throws NotFoundException;
    Author changeAuthor(Long id, AuthorRequest request) throws NotFoundException;
    void deleteAuthor(Long id) throws NotFoundException;
    void addBook(Long bookId, Long authorId) throws NotFoundException;
    void removeBook(Long bookId, Long authorId) throws NotFoundException;
}
