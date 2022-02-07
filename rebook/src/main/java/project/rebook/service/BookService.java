package project.rebook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.book.Book;
import project.rebook.repository.book.BookRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    //bookRepository 자동 주입
    private final BookRepository bookRepository;

    public Long save(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
