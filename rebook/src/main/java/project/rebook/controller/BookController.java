package project.rebook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.rebook.domain.Review;
import project.rebook.domain.book.Book;
import project.rebook.service.BookService;
import project.rebook.service.ReviewService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final ReviewService reviewService;

    @GetMapping
    public String books(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "book/books";
    }

    @GetMapping("/{bookId}")
    public String bookInfo(@PathVariable Long bookId, Model model) {
        Book book = bookService.findById(bookId);
        List<Review> reviews = reviewService.findByBookId(bookId);

        model.addAttribute("book", book);
        model.addAttribute("reviews", reviews);
        return "book/detail";
    }

}
