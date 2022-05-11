package project.rebook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.book.Book;
import project.rebook.repository.book.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    //bookRepository 자동 주입
    private final BookRepository bookRepository;

    @Transactional
    public Long save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> findBooksFromIds(List<Long> ids) {
        List<Book> books = new ArrayList<>();
        for (Long id : ids) {
            Book book = findById(id);
            books.add(book);
        }
        return books;
    }

    @Transactional
    public void clear() {
        bookRepository.clear();
    }
}
