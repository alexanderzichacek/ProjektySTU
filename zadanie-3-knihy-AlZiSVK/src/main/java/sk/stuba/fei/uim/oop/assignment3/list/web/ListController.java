package sk.stuba.fei.uim.oop.assignment3.list.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperation;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.logic.IListServices;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.ListRequest;
import sk.stuba.fei.uim.oop.assignment3.list.web.body.ListResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
public class ListController {
    @Autowired
    private IListServices service;

    @GetMapping
    public List<ListResponse> getAllLists(){
        return this.service.getAllLists().stream().map(ListResponse::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ListResponse> postList() {
        return new ResponseEntity<>(new ListResponse(this.service.postList()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ListResponse getById(@PathVariable("id") Long id) throws NotFoundException {
        return new ListResponse(this.service.getListById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteList(@PathVariable("id") Long id) throws NotFoundException {
        this.service.deleteList(id);
    }

    @PostMapping("/{id}/add")
    public ListResponse addBookToList(@PathVariable("id") Long bookId, @RequestBody ListRequest request) throws NotFoundException, IllegalOperation {
        return new ListResponse(this.service.addBookToList(request.getId(), bookId));
    }

    @DeleteMapping("/{id}/remove")
    public ListResponse removeBookFromList(@PathVariable("id") Long bookId, @RequestBody ListRequest request) throws NotFoundException {
        return new ListResponse(this.service.removeBookFromList(request.getId(), bookId));
    }

    @GetMapping("/{id}/lend")
    public void lendListById(@PathVariable("id") Long id) throws NotFoundException, IllegalOperation {
        this.service.lendListById(id);
    }
}
