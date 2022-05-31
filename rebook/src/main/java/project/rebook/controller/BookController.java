package project.rebook.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.rebook.domain.dto.BookDto;
import project.rebook.domain.dto.ReviewDto;
import project.rebook.service.BookService;
import project.rebook.service.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final ReviewService reviewService;

    /**
     * 책 목록
     */
    @GetMapping
    public String books(Model model) {
        List<BookDto> bookDtos = bookService.findAll().stream().map(BookDto::from).collect(Collectors.toList());
        model.addAttribute("books", bookDtos);
        return "book/bookList";
    }

    /**
     * 책 상세 보기
     */
    @GetMapping("/{bookId}")
    public String bookInfo(@PathVariable Long bookId, Model model) {
        List<ReviewDto> reviewDtos = reviewService.findByBookId(bookId).stream().map(ReviewDto::from).collect(Collectors.toList());

        model.addAttribute("book", BookDto.from(bookService.findById(bookId)));
        model.addAttribute("reviews", reviewDtos);
        return "book/bookDetail";
    }

}
