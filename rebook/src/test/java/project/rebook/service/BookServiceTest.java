package project.rebook.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.rebook.domain.book.Book;
import project.rebook.repository.book.BookRepository;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Test
    @DisplayName("책 저장")
    void save() {
        //given
        Book book = new Book();
        Long bookId = 1L;

        //mocking
        given(bookRepository.save(any()))
                .willReturn(bookId);
        given(bookRepository.findById(bookId))
                .willReturn(book);

        //when
        Long saveId = bookService.save(book);
        Book findBook = bookRepository.findById(saveId);

        //then
        assertThat(findBook).isEqualTo(book);
    }

    @Test
    @DisplayName("책 모두 찾기")
    void findAll() {
        //given
        Book book = new Book();
        Book book2 = new Book();

        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book2);

        //mocking
        given(bookRepository.findAll())
                .willReturn(books);

        //when
        List<Book> findBooks = bookService.findAll();

        //then
        assertThat(findBooks).isEqualTo(books);
    }

    @Test
    @DisplayName("책 아이디로 책 찾기")
    void findBooksFromIds() {
        //given
        Book book = new Book();
        Long bookId = 1L;

        Book book2 = new Book();
        Long book2Id = 2L;

        List<Long> testIds = new ArrayList<>();
        testIds.add(bookId);
        testIds.add(book2Id);

        //mocking
        when(bookRepository.findById(bookId))
                .thenReturn(book);
        when(bookRepository.findById(book2Id))
                .thenReturn(book2);


        //when
        List<Book> findBooks = bookService.findBooksFromIds(testIds);

        //then
        assertThat(findBooks).contains(book, book2);
    }
}