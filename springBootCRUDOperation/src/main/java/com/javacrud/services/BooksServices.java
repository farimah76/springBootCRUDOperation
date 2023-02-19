package com.javacrud.services;

import com.javacrud.model.Books;

import java.util.List;

public interface BooksServices {
    List<Books> getAllBooks();
    Books getBooksById(int id) throws Exception ;
    void saveOrUpdate(Books books);
    void delete(int id);
    void update(Books books);

}
