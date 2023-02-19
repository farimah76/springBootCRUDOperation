package com.javacrud.services;

import com.javacrud.model.Books;
import com.javacrud.repository.BooksRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooksServiceImpl implements BooksServices {
    @Autowired
    BooksRepository booksRepository;
    //getting all books record by using the method findaAll() of CrudRepository
    @Override
    public List<Books> getAllBooks()
    {
        List<Books> books = new ArrayList<Books>();
        booksRepository.findAll().forEach(books1 -> books.add(books1));
        return books;
    }
    //getting a specific record by using the method findById() of CrudRepository
    @Override
    @NonNull
    public Books getBooksById(int id)
    {

        return booksRepository.findById(id).orElseGet(Books ::new);
    }
    //saving a specific record by using the method save() of CrudRepository
    @Override
    public void saveOrUpdate(Books books)
    {
        booksRepository.save(books);
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    @Override
    public void delete(int id)
    {
        booksRepository.deleteById(id);
    }
    //updating a record
    @Override
    public void update(Books books)
    {
        booksRepository.save(books);
    }

}
