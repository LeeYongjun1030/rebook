package project.rebook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.rebook.domain.book.Book;
import project.rebook.domain.book.Category;
import project.rebook.service.BookService;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class initDb {

    private final BookService bookService;

    @PostConstruct
    public void initData() {
        bookService.save(new Book("book A", "star", Category.SCIENCE, 10000));
        bookService.save(new Book("book B", "star", Category.COMPUTER, 20000));
        bookService.save(new Book("book C", "sun", Category.ECONOMY, 30000));
        bookService.save(new Book("book D", "sun", Category.COMPUTER, 40000));
        bookService.save(new Book("book E", "moon", Category.SCIENCE, 50000));
    }
}
