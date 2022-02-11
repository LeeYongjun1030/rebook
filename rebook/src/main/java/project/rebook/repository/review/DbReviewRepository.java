package project.rebook.repository.review;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import project.rebook.domain.Review;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class DbReviewRepository implements ReviewRepository {

    private final EntityManager em;

    @Override
    public Long save(Review review) {
        em.persist(review);
        return review.getId();
    }

    @Override
    public Review findById(Long id) {
        return em.find(Review.class, id);
    }

    @Override
    public List<Review> findByMemberId(Long memberId) {
        return em.createQuery(
                "select r from Review r" +
                " where r.member.id = :memberId", Review.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    @Override
    public List<Review> findByBookId(Long bookId) {
        return em.createQuery(
                "select r from Review r" +
                        " where r.book.id = :bookId", Review.class)
                .setParameter("bookId", bookId)
                .getResultList();
    }

    @Override
    public void delete(Review review) {
        em.createQuery(
                "delete from Review r" +
                " where r.id = :reviewId")
                .setParameter("reviewId", review.getId())
                .executeUpdate();
    }

    @Override
    public void clear() {
        em.createQuery("delete from Review").executeUpdate();
    }
}
