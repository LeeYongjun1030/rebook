package project.rebook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.rebook.domain.Review;
import project.rebook.domain.book.Book;
import project.rebook.domain.member.Grade;
import project.rebook.domain.member.GradeConst;
import project.rebook.domain.member.Member;
import project.rebook.service.BookService;
import project.rebook.service.MemberService;
import project.rebook.service.ReviewService;
import project.rebook.web.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        Review review = new Review(
                addReviewForm.getComment(),
                addReviewForm.getRate(),
                LocalDate.now(),
                loginMember,
                bookService.findById(bookId)
        );
        reviewService.save(review);

        // 리뷰 개수에 따른 멤버 등급 조정
        updateMemberGrade(loginMember);

        return "redirect:/books/" + bookId;
    }


    /**
     * 내가 쓴 리뷰 보기
     */
    @GetMapping("/list")
    public String reviewList(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                             @ModelAttribute DeleteReview deleteReview,
                             Model model) {

        // Dto를 사용하여 멤버의 민감 정보가 노출되지 않도록 한다.
        MemberDto memberDto = new MemberDto();
        memberDto.setNickname(loginMember.getNickname());
        memberDto.setGrade(loginMember.getGrade());
        model.addAttribute("member", memberDto);

        // 모델에 넘겨줄 reviews Dto 생성
        List<Review> reviews = reviewService.findByMemberId(loginMember.getId());
        List<ReviewDeleteDto> reviewDeleteDtos = createReviewDto(reviews);
        model.addAttribute("reviews", reviewDeleteDtos);

        return "review/reviews";
    }


    /**
     * 내가 쓴 리뷰 삭제 기능
     */
    @PostMapping("/list")
    public String reviewListPost(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                             @ModelAttribute DeleteReview deleteReview,
                             Model model) {

        // 리뷰 삭제 기능
        if(deleteReview.getIds() != null){
            List<Long> ids = deleteReview.getIds();
            for (Long reviewId : ids) {
                Review review = reviewService.findById(reviewId);
                reviewService.delete(review);
            }
        }

        // 리뷰 개수에 따른 멤버 등급 조정
        updateMemberGrade(loginMember);

        return "redirect:/review/list";
    }


    private void updateMemberGrade(Member loginMember) {
        Long memberId = loginMember.getId();
        if (reviewService.findByMemberId(memberId).size() >= GradeConst.REVIEWS_VIP) {
            memberService.updateGrade(memberId, Grade.VIP);
            loginMember.setGrade(Grade.VIP);
        } else {
            memberService.updateGrade(memberId, Grade.NORMAL);
            loginMember.setGrade(Grade.NORMAL);
        }
    }


    private List<ReviewDeleteDto> createReviewDto(List<Review> reviews) {
        List<ReviewDeleteDto> reviewDeleteDtos = new ArrayList<>();
        for (Review review : reviews) {
            ReviewDeleteDto reviewDeleteDto = new ReviewDeleteDto();
            reviewDeleteDto.setId(review.getId());
            reviewDeleteDto.setBookName(review.getBook().getBookName());
            reviewDeleteDto.setRate(review.getRate());
            reviewDeleteDto.setComment(review.getComment());
            reviewDeleteDto.setDate(review.getDate());

            reviewDeleteDtos.add(reviewDeleteDto);
        }
        return reviewDeleteDtos;
    }
}
