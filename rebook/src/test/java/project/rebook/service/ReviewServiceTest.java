package project.rebook.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.rebook.domain.Review;
import project.rebook.domain.book.Book;
import project.rebook.domain.book.Category;
import project.rebook.domain.member.Grade;
import project.rebook.domain.member.Member;
import project.rebook.repository.member.MemberRepository;
import project.rebook.repository.review.ReviewRepository;
import project.rebook.web.AddReviewForm;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @InjectMocks
    ReviewService reviewService;

    @Mock
    MemberRepository memberRepository;

    @Mock
    ReviewRepository reviewRepository;

    @Test
    @DisplayName("리뷰 저장")
    void save() {
        //given
        Review review = new Review();
        Long reviewId = 1L;

        //mocking
        given(reviewRepository.save(review))
                .willReturn(reviewId);
        given(reviewRepository.findById(reviewId))
                .willReturn(review);

        //when
        Long findId = reviewService.save(review);
        Review findReview = reviewRepository.findById(findId);


        //then
        assertThat(findReview).isEqualTo(review);
    }

    @Test
    @DisplayName("멤버 아이디로 리뷰 목록 조회")
    void findByMemberId() {
        //given
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review());

        //mocking
        given(reviewRepository.findByMemberId(any()))
                .willReturn(reviews);

        //when
        List<Review> findReviews = reviewService.findByMemberId(1L);

        //then
        assertThat(findReviews).isEqualTo(reviews);
    }

    @Test
    @DisplayName("책 아이디로 리뷰 목록 조회")
    void findByBookId() {
        //given
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review());

        //mocking
        given(reviewRepository.findByBookId(any()))
                .willReturn(reviews);

        //when
        List<Review> findReviews = reviewService.findByBookId(1L);

        //then
        assertThat(findReviews).isEqualTo(reviews);

    }

    @Test
    @DisplayName("리뷰 삭제 및 삭제 개수만큼 리뷰 수 감소")
    void deleteReviews() {

        //given
        int initReviews = 10;
        int deleteReviews = 2;
        List<Long> ids = new ArrayList<>();
        for (Long i = 0L; i < deleteReviews; i++) {
            ids.add(i);
        }

        Member member = new Member("test", "testLoginId", "testPassword", initReviews, Grade.NORMAL);
        Long memberId = 1L;
        
        //mocking
        given(memberRepository.findById(memberId))
                .willReturn(member);

        //when
        Member findMember = reviewService.deleteReviews(memberId, ids);

        //then
        int laterReviews = initReviews - deleteReviews;
        assertThat(findMember.getNumberOfReviews()).isEqualTo(laterReviews);

    }

    @Test
    @DisplayName("리뷰 등록 및 리뷰 수 증가")
    void createReview() {

        //given
        int initReviews = 10;
        Member member = new Member("test", "testLoginId", "testPassword", initReviews, Grade.NORMAL);
        Long memberId = 1L;

        //mocking
        given(memberRepository.findById(memberId))
                .willReturn(member);

        //when
        Member findMember = reviewService.createReview(new AddReviewForm(), memberId, new Book());

        //then : 리뷰 수 1 증가
        int laterReviews = initReviews + 1;
        assertThat(findMember.getNumberOfReviews()).isEqualTo(laterReviews);

    }

}