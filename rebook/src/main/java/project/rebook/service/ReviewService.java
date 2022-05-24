package project.rebook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.Review;
import project.rebook.domain.book.Book;
import project.rebook.domain.member.Member;
import project.rebook.repository.member.MemberRepository;
import project.rebook.repository.review.ReviewRepository;
import project.rebook.web.AddReviewForm;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public Long save(Review review) {
        return reviewRepository.save(review);
    }

    public Review findById(Long id) {
        return reviewRepository.findById(id);
    }

    public List<Review> findByMemberId(Long memberId) {
        return reviewRepository.findByMemberId(memberId);
    }

    public List<Review> findByBookId(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    @Transactional
    public void clear() {
        reviewRepository.clear();
    }

    @Transactional
    public Member deleteReviews(Long memberId, List<Long> ids) {

        // 선택된 리뷰들 삭제
        for (Long id : ids) {
            Review review = reviewRepository.findById(id);
            reviewRepository.delete(review);
        }

        // 리뷰 개수 업데이트
        Member member = memberRepository.findById(memberId);
        member.decreaseNumberOfReviews(ids.size());
        return member;
    }

    @Transactional
    public Member createReview(AddReviewForm addReviewForm, Long memberId, Book book) {

        Member member = memberRepository.findById(memberId);

        // 리뷰 생성
        Review review = new Review(
                addReviewForm.getComment(),
                addReviewForm.getRate(),
                LocalDate.now(),
                member,
                book);

        // 리뷰 저장
        reviewRepository.save(review);

        // 리뷰 개수 업데이트
        member.increaseNumberOfReviews();
        return member;
    }

}
