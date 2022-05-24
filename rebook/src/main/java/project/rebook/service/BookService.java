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

    private final BookRepository bookRepository;

    @Transactional
    public Long save(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

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
