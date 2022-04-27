package project.rebook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.Review;
import project.rebook.repository.review.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

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
    public void delete(Review review) {
        reviewRepository.delete(review);
    }
}
