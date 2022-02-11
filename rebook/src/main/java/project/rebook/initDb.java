package project.rebook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.rebook.domain.Review;
import project.rebook.domain.book.Book;
import project.rebook.domain.book.Category;
import project.rebook.domain.member.Grade;
import project.rebook.domain.member.Member;
import project.rebook.service.BookService;
import project.rebook.service.MemberService;
import project.rebook.service.ReviewService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class initDb {

    private final MemberService memberService;
    private final BookService bookService;
    private final ReviewService reviewService;

    @PostConstruct
    public void initData() {
        // 책
        Long bookId = bookService.save(new Book("book A", "star", Category.SCIENCE, 10000));
        bookService.save(new Book("book B", "star", Category.COMPUTER, 20000));
        bookService.save(new Book("book C", "sun", Category.ECONOMY, 30000));
        bookService.save(new Book("book D", "sun", Category.COMPUTER, 40000));
        bookService.save(new Book("book E", "moon", Category.SCIENCE, 50000));

        // 테스트용 회원 생성
        Long memberId = memberService.save(new Member("관리자", "123", "123", Grade.NORMAL));

        // 테스트용 리뷰 생성
        Member findMember = memberService.findById(memberId);
        Book findBook = bookService.findById(bookId);
        Review review = new Review("재밌다!!", 5, LocalDate.now(), findMember, findBook);
        reviewService.save(review);
        Review review2 = new Review("재밌네요!!", 4, LocalDate.now(), findMember, findBook);
        reviewService.save(review2);
        Review review3 = new Review("꿀잼!!", 5, LocalDate.now(), findMember, findBook);
        reviewService.save(review3);
    }
}
