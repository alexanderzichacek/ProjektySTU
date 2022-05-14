package sk.stuba.fei.uim.oop.assignment3.book.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookServices;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.Amount;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.book.web.body.LendCount;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookServices service;

    @GetMapping
    public List<BookResponse> getAllBooks(){
        return this.service.getAllBooks().stream().map(BookResponse::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<BookResponse> postBook(@RequestBody BookRequest request) throws NotFoundException{
        return new ResponseEntity<>(new BookResponse(this.service.postBook(request)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable("id") Long id) throws NotFoundException {
        return new BookResponse(this.service.getBookById(id));
    }

    @PutMapping("/{id}")
    public BookResponse changeBook(@PathVariable("id") Long id, @RequestBody BookRequest request) throws NotFoundException {
        return new BookResponse(this.service.changeBook(id, request));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) throws NotFoundException {
        this.service.deleteBook(id);
    }

    @GetMapping("/{id}/amount")
    public Amount getAmount(@PathVariable("id") Long id) throws NotFoundException {
        return new Amount(this.service.getAmount(id));
    }

    @PostMapping("/{id}/amount")
    public Amount addAmount(@PathVariable("id") Long id, @RequestBody Amount request) throws NotFoundException {
        return new Amount(this.service.addAmount(id, request.getAmount()));
    }

    @GetMapping("/{id}/lendCount")
    public LendCount getLendCount(@PathVariable("id") Long id) throws NotFoundException {
        return new LendCount(this.service.getLendCount(id));
    }
}
