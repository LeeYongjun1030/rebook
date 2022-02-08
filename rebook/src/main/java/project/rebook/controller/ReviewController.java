package project.rebook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.rebook.domain.book.Book;
import project.rebook.service.BookService;
import project.rebook.service.ReviewService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;

    @GetMapping("/add/{bookId}")
    public String add(@PathVariable Long bookId) {

        Book book = bookService.findById(bookId);
        return "review/add";
    }

//    @PostMapping("/add/{bookId")
//    public String addReview() {
//
//    }

}
