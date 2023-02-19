package com.javacrud.repository;

import com.javacrud.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;


import java.util.Optional;


public interface BooksRepository extends JpaRepository<Books,Integer> {
    @Override
    @NonNull
    Optional<Books> findById(Integer id);
}
