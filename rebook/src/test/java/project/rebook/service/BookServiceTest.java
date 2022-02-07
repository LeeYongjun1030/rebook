package project.rebook.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.rebook.domain.book.Book;
import project.rebook.repository.book.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired BookService bookService;

    @Test
    void save() {
        //given
        Book book = new Book();
        book.setPublisher("hello");
        book.setPrice(10000);
        Long saveId = bookService.save(book);

        //when
        Book findBook = bookService.findById(saveId);

        //then
        assertThat(findBook).isEqualTo(book);
    }

    @Test
    void findAll() {
        //given
        Book book = new Book();
        book.setPublisher("hello");
        book.setPrice(10000);
        bookService.save(book);

        Book book2 = new Book();
        book2.setPublisher("hello2");
        book2.setPrice(20000);
        bookService.save(book2);

        //when
        List<Book> findBooks = bookService.findAll();

        //then
        assertThat(findBooks.size()).isEqualTo(2);


    }
}