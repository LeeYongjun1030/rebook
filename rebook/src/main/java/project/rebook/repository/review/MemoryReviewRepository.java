package project.rebook.repository.review;

import org.springframework.stereotype.Repository;
import project.rebook.domain.Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MemoryReviewRepository implements ReviewRepository {

    private static Map<Long, Review> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Long save(Review review) {
        //review.setId(++sequence);
        Long id = review.getId();
        store.put(id, review);
        return id;
    }

    @Override
    public Review findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Review> findByMemberId(Long memberId){
        return store.values().stream()
                .filter(review -> review.getMember().getId().equals(memberId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Review> findByBookId(Long bookId) {
        return store.values().stream()
                .filter(review -> review.getBook().getId().equals(bookId))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Review review) {
        //
    }

    @Override
    public void clear() {
        store.clear();
    }
}
