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

    @Transactional(readOnly = true)
    public Review findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Review> findByMemberId(Long memberId) {
        return reviewRepository.findByMemberId(memberId);
    }

    @Transactional(readOnly = true)
    public List<Review> findByBookId(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    @Transactional
    public void clear() {
        reviewRepository.clear();
    }

    @Transactional
    public void deleteReviews(Long memberId, List<Long> ids) {
        ids.stream().map(this::findById).forEach(reviewRepository::delete);
        memberRepository.adjustNumberOfReviews(memberId, -1*ids.size());
    }

    @Transactional
    public void createReview(AddReviewForm addReviewForm, Long memberId, Book book) {
        memberRepository.adjustNumberOfReviews(memberId, 1);
        save(new Review(
                addReviewForm.getComment(),
                addReviewForm.getRate(),
                LocalDate.now(),
                memberRepository.findById(memberId),
                book));

    }

}
