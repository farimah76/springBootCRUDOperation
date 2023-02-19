package com.javacrud.controller;

import com.javacrud.model.Books;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.javacrud.services.BooksServiceImpl;

import java.util.List;

@RestController
@Slf4j
public class BooksController {


    //autowire the BooksService class

    BooksServiceImpl booksService;
    @Autowired
    public BooksController(BooksServiceImpl booksService) {
        this.booksService = booksService;
    }



    //creating a get mapping that retrieves all the books detail from the database
    @GetMapping(value = "/welcome")
    public String index() {

        return "welcome to my page";
    }

    @GetMapping(value = "/accessDenied")
    public String accessDenied() {

        return "accessDenied";
    }

    @GetMapping("/book")
    public List<Books> getAllBooks() {

        log.info("BooksController.getAllBooks");
        return booksService.getAllBooks();
    }

    //creating a get mapping that retrieves the detail of a specific book
    @GetMapping("/book/{bookId}")
    public Books getBooks(@PathVariable("bookId")  int bookId) throws Exception {
        try {
            booksService.getBooksById(bookId);
            log.info("BooksController.getBooks by bookId");
        } catch (Exception e) {
            log.info("BooksController.getBooks ServiceException:"+ e);
            throw e;
        }
        return booksService.getBooksById(bookId);
    }

    //creating a delete mapping that deletes a specified book
    @DeleteMapping("/book/{bookId}")
    public void deleteBook(@PathVariable("bookid") int bookId) {
        log.info("BooksController.deleting book by id");
        booksService.delete(bookId);
    }

    //creating post mapping that post the book detail in the database
    @PostMapping("/books")
    public int saveBook(@RequestBody Books books) {
        log.info("BooksController.saveBook start save a new book");
        booksService.saveOrUpdate(books);
        return books.getBookId();
    }

    //creating put mapping that updates the book detail
    @PutMapping("/books")
    public Books update(@RequestBody Books books) {
        log.info("BooksController.update a the book");
        booksService.saveOrUpdate(books);
        return books;
    }

}
