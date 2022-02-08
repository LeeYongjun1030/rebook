package project.rebook.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.book.Book;
import project.rebook.domain.book.Category;
import project.rebook.repository.book.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookServiceTest {

    @Autowired BookService bookService;

    @BeforeEach
    void beforeEach() {
        bookService.clear();
    }

    @AfterEach
    void afterEach() {
        bookService.clear();
    }

    @Test
    void save() {
        //given
        Book book = new Book("book123", "star", Category.SCIENCE, 10000);
        Long saveId = bookService.save(book);

        //when
        Book findBook = bookService.findById(saveId);

        //then
        assertThat(findBook).isEqualTo(book);

    }

    @Test
    void findAll() {
        //given
        Book book = new Book("book1", "star", Category.SCIENCE, 10000);
        bookService.save(book);

        Book book2 = new Book("book2", "moon", Category.SCIENCE, 10000);
        bookService.save(book2);

        //when
        List<Book> findBooks = bookService.findAll();

        //then
        assertThat(findBooks.size()).isEqualTo(2);


    }
}