package project.rebook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.rebook.domain.Review;
import project.rebook.domain.book.Book;
import project.rebook.domain.dto.BookDto;
import project.rebook.domain.dto.MemberDto;
import project.rebook.domain.dto.ReviewDto;
import project.rebook.domain.member.GradeConst;
import project.rebook.domain.member.Member;
import project.rebook.service.BookService;
import project.rebook.service.MemberService;
import project.rebook.service.ReviewService;
import project.rebook.web.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final MemberService memberService;
    private final ReviewService reviewService;
    private final BookService bookService;

    /**
     * 리뷰 쓰기
     */
    @GetMapping("/add/{bookId}")
    public String add(@PathVariable Long bookId,
                      @ModelAttribute AddReviewForm addReviewForm, Model model) {
        model.addAttribute("book", BookDto.from(bookService.findById(bookId)));
        return "review/add";
    }

    @PostMapping("/add/{bookId}")
    public String addReview(@PathVariable Long bookId,
                            @Validated @ModelAttribute AddReviewForm addReviewForm,
                            BindingResult bindingResult,
                            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                            Model model) {
        // 리뷰 검증
        if (bindingResult.hasErrors()) {
            return "review/add";
        }

        // 리뷰 생성 및 저장
        Book book = bookService.findById(bookId);
        reviewService.createReview(addReviewForm, loginMember.getId(), book);

        model.addAttribute("book", BookDto.from(book));
        return "redirect:/books/" + bookId;
    }


    /**
     * 내가 쓴 리뷰 보기
     */
    @GetMapping("/list")
    public String reviewList(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                             @ModelAttribute DeleteReview deleteReview, Model model) {

        List<Review> reviews = reviewService.findByMemberId(loginMember.getId());
        List<ReviewDto> reviewDtos = reviews.stream().map(ReviewDto::from).collect(Collectors.toList());

        model.addAttribute("reviews", reviewDtos);
        model.addAttribute("member", MemberDto.from(memberService.findById(loginMember.getId())));
        model.addAttribute("vipReview", GradeConst.NUM_of_REVIEWS_to_VIP);
        return "review/reviews";
    }

    /**
     * 내가 쓴 리뷰 삭제 기능
     */
    @PostMapping("/list")
    public String reviewListPost(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                                 @ModelAttribute DeleteReview deleteReview, Model model) {

        if(deleteReview.getIds() != null){
            reviewService.deleteReviews(loginMember.getId(), deleteReview.getIds());
        }
        return "redirect:/review/list";
    }

}
