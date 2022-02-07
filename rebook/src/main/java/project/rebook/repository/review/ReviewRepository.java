package project.rebook.repository.review;

import project.rebook.domain.Review;

import java.util.List;

public interface ReviewRepository {

    public Long save(Review review);

    public Review findById(Long id);

    public List<Review> findByMemberId(Long memberId);

    public List<Review> findByBookId(Long bookId);

}
