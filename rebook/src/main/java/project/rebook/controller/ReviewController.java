package project.rebook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.rebook.domain.Review;
import project.rebook.domain.book.Book;
import project.rebook.domain.member.Member;
import project.rebook.service.BookService;
import project.rebook.service.ReviewService;
import project.rebook.web.AddReviewForm;
import project.rebook.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;

    @GetMapping("/add/{bookId}")
    public String add(@PathVariable Long bookId,
                      @ModelAttribute AddReviewForm addReviewForm,
                      Model model) {

        Book book = bookService.findById(bookId);
        model.addAttribute("book", book);
        return "review/add";
    }

    @PostMapping("/add/{bookId}")
    public String addReview(@PathVariable Long bookId,
                            @Validated @ModelAttribute AddReviewForm addReviewForm,
                            BindingResult bindingResult,
                            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                            Model model) {

        Book book = bookService.findById(bookId);
        model.addAttribute("book", book);

        if (bindingResult.hasErrors()) {
            return "review/add";
        }

        // 리뷰 등록 성공 -> 생성 및 저장
        Review review = new Review();
        review.setRate(addReviewForm.getRate());
        review.setComment(addReviewForm.getComment());
        review.setDate(LocalDate.now());
        review.setMember(loginMember);
        review.setBook(bookService.findById(bookId));
        reviewService.save(review);

        return "redirect:/books/" + bookId;
    }

}
