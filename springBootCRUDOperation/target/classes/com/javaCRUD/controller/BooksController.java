package com.javaCRUD.controller;

import com.javaCRUD.SpringBootCrudOperationApplication;
import com.javaCRUD.model.Books;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.javaCRUD.services.BooksServiceImpl;

import java.util.List;

@RestController
public class BooksController {
    private static final Logger logger = LoggerFactory.getLogger(SpringBootCrudOperationApplication.class);
    //autowire the BooksService class
    @Autowired
    BooksServiceImpl booksService;
    //creating a get mapping that retrieves all the books detail from the database
    @GetMapping("/book")
    private List<Books> getAllBooks()
    {

        logger.info("getting All Books");
        return booksService.getAllBooks();
    }
    //creating a get mapping that retrieves the detail of a specific book
    @GetMapping("/book/{bookid}")
    private Books getBooks(@PathVariable("bookid") int bookid)
    {

        logger.info("getting bookId");
        return booksService.getBooksById(bookid);
    }
    //creating a delete mapping that deletes a specified book
    @DeleteMapping("/book/{bookid}")
    private void deleteBook(@PathVariable("bookid") int bookid)
    {
        logger.info("deleting book by book id,id is:"+ bookid);
        booksService.delete(bookid);
    }

    //creating post mapping that post the book detail in the database
    @PostMapping("/books")
    private int saveBook(@RequestBody Books books)
    {
        logger.info("Creating a book");
        booksService.saveOrUpdate(books);
        return books.getBookid();
    }
    //creating put mapping that updates the book detail
    @PutMapping("/books")
    private Books update(@RequestBody Books books)
    {
        logger.info("updating the book");
        booksService.saveOrUpdate(books);
        return books;
    }
}
