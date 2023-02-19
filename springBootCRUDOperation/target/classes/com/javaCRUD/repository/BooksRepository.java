package com.javaCRUD.repository;

import com.javaCRUD.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableJpaRepositories
public interface BooksRepository extends JpaRepository<Books,Integer> {
    @Override
    Optional<Books> findById(Integer id);
}
