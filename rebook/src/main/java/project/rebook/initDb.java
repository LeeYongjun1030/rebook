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
        Long bookId = bookService.save(new Book("자바 프로그래밍 입문", "이지퍼블리싱", Category.COMPUTER, 25000));
        Long bookId2 = bookService.save(new Book("이것이 Mysql이다.", "한빛미디어", Category.COMPUTER, 32000));
        bookService.save(new Book("NFT 레볼루션", "더퀘스트", Category.ECONOMY, 16200));
        bookService.save(new Book("부의 추월차선", "토트", Category.ECONOMY, 15000));
        bookService.save(new Book("이기적 유전자", "리처드 도킨스", Category.SCIENCE, 18000));
        bookService.save(new Book("코스모스", "사이언스북스", Category.SCIENCE, 16600));

        // 테스트용 회원 생성
        Long memberId = memberService.save(new Member("관리자", "manager123", "manager123", Grade.NORMAL));

        // 테스트용 리뷰 생성
        Member findMember = memberService.findById(memberId);

        Book findBook = bookService.findById(bookId);
        Review review = new Review("재밌다!!", 5, LocalDate.now(), findMember, findBook);
        reviewService.save(review);

        Book findBook2 = bookService.findById(bookId2);
        Review review2 = new Review("재밌네요!!", 4, LocalDate.now(), findMember, findBook2);
        reviewService.save(review2);
    }
}
