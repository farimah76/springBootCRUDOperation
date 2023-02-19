package com.javacrud.services;

import com.javacrud.SpringBootCrudOperationApplication;
import com.javacrud.model.Books;
import com.javacrud.repository.BooksRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BooksServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(SpringBootCrudOperationApplication.class);

    private BooksRepository booksRepository;
    @Autowired
    public BooksServiceImplTest(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }





    @Test
    @Order(1)
    public void givenBookObject_whenGetAllBooks_thenReturn200() throws Exception{

            List<Books> booksList = (List<Books>) booksRepository.findAll();
            Assertions.assertThat(booksList.size()).isGreaterThan(-1);
            logger.info("\n givenBookObject_whenGetAllBooks_thenReturn200 is successfully Done");


    }
    @Test
    @Order(2)
    public void givenBookObject_whenGetAllBooks_thenReturn404() throws Exception{

            List<Books> booksList = (List<Books>) booksRepository.findAll();
            Assertions.assertThat(booksList.size()).isGreaterThan(0);
            logger.error("\n givenBookObject_whenGetAllBooks_thenReturn404 is NotFound");


    }


    @Test
    @Order(3)
    public void givenBookObject_whenGetBooksById_thenReturn200()throws Exception {

            Books books = booksRepository.findById(0).orElseGet(Books::new);
            Assertions.assertThat(books.getBookId()).isEqualTo(0);
            logger.info("\n givenBookObject_whenGetBooksById_thenReturn200 is successfully Done");


    }

    @Test
    @Order(4)
    public void givenBookObject_whenGetBooksById_thenReturn404()throws Exception {

            Books books = booksRepository.findById(0).orElseGet(Books::new);
            Assertions.assertThat(books.getBookId()).isEqualTo(1);
            logger.info("\n givenBookObject_whenGetBooksById_thenReturn404 is NotFound");


    }

    @Test
    @Order(5)
    public void givenBookObject_whenSaveBooks_thenReturn200() throws Exception {

            Books savedBooks = Books.builder()
                    .bookName("Programming with Java")
                    .author("E. Balagurusamy")
                    .price(890)
                    .build();
            booksRepository.save(savedBooks);
            Assertions.assertThat(savedBooks.getBookId()).isGreaterThan(0);
            logger.info("\n givenBookObject_whenSaveBooks_thenReturn200 is successfully Done");

    }
 @Test
    @Order(6)
    public void givenBookObject_whenSaveBooks_thenReturn404() throws Exception {

            Books savedBooks = Books.builder()
                    .bookName("Programming with Java")
                    .author("E. Balagurusamy")
                    .price(890)
                    .build();
            booksRepository.save(savedBooks);
            Assertions.assertThat(savedBooks.getBookId()).isGreaterThan(2);
            logger.info("\n givenBookObject_whenSaveBooks_thenReturn404 is NotFound");

    }

    @Test
    @Order(7)
    public void givenBookObject_whenUpdateBooks_thenReturn200() throws Exception {

            Books books = booksRepository.findById(1).orElseGet(Books::new);
            books.setBookId(1);
            Books booksUpdate = booksRepository.save(books);
            Assertions.assertThat(booksUpdate.getBookId()).isEqualTo(1);
            logger.info("\n givenBookObject_whenUpdateBooks_thenReturn200() is successfully Done");



    }@Test
    @Order(8)
    public void givenBookObject_whenUpdateBooks_thenReturn404() throws Exception {

            Books books = booksRepository.findById(1).orElseGet(Books::new);
            books.setBookId(1);
            Books booksUpdate = booksRepository.save(books);
            Assertions.assertThat(booksUpdate.getBookId()).isEqualTo(0);
            logger.info("\n givenBookObject_whenUpdateBooks_thenReturn404 is NotFound");



    }

    @Test
    @Order(9)
    @Rollback(value = false)
    public void givenBookObject_whenDeleteBooks_thenReturn200() throws Exception {
        // given - precondition or setup
        int bookId = 1;

            if (booksRepository.findById(bookId) != null) {


                Books books = booksRepository.findById(0).orElseGet(Books::new);

                booksRepository.delete(books);
                Books books1 = null;
                Optional<Books> optionalBooks = booksRepository.findById(0);
                if (optionalBooks.isPresent()) {
                    books1 = optionalBooks.get();
                }

                Assertions.assertThat(books1).isNull();
                logger.info("\n givenBookObject_whenDeleteBooks_thenReturn200() is successfully Done");
            }

    } @Test
    @Order(10)
    @Rollback(value = false)
    public void givenBookObject_whenDeleteBooks_thenReturn404() throws Exception {
        // given - precondition or setup
        int bookId = 1;

            if (booksRepository.findById(bookId) != null) {


                Books books = booksRepository.findById(0).orElseGet(Books::new);

                booksRepository.delete(books);
                Books books1 = null;
                Optional<Books> optionalBooks = booksRepository.findById(0);
                if (optionalBooks.isPresent()) {
                    books1 = optionalBooks.get();
                }

                Assertions.assertThat(books1).isNotNull();
                logger.info("\n givenBookObject_whenDeleteBooks_thenReturn404 is NotFound");
            }

    }
}