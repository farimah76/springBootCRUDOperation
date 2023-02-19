package com.javaCRUD.services;

import com.javaCRUD.model.Books;

import java.util.List;

public interface BooksServices {
    List<Books> getAllBooks();
    Books getBooksById(int id);
    void saveOrUpdate(Books books);
    void delete(int id);
    void update(Books books);

}
