package project.rebook.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.Review;
import project.rebook.domain.book.Book;
import project.rebook.domain.book.Category;
import project.rebook.domain.member.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewServiceTest {

    @Autowired ReviewService reviewService;
    @Autowired MemberService memberService;
    @Autowired BookService bookService;

    @BeforeEach
    void beforeEach() {
        reviewService.clear();
        memberService.clear();
    }


    @AfterEach
    void afterEach() {
        reviewService.clear();
        memberService.clear();
    }
    @Test
    void save() {
        //given
        Review review = new Review();
        Long saveId = reviewService.save(review);

        //when
        Review findReview = reviewService.findById(saveId);

        //then
        assertThat(findReview).isEqualTo(review);
    }

    /**
     * 상황 : 멤버는 A, B 두 명이 있고 책은 a, b, c 세 권이 있다.

     * 멤버 A -> 책 a, b에 리뷰
     * 멤버 B -> 책 b, c에 리뷰
     */

    @Test
    void findByMemberId() {
        //given
        Member memberA = new Member();
        memberA.setNickname("member A");
        Long saveIdA = memberService.save(memberA);

        Member memberB = new Member();
        memberB.setNickname("member B");
        Long saveIdB = memberService.save(memberB);

        Book bookA = new Book("bookA", "star", Category.SCIENCE, 10000);
        bookService.save(bookA);

        Book bookB = new Book("bookB", "moon", Category.SCIENCE, 20000);
        bookService.save(bookB);

        Book bookC = new Book("bookC", "sun", Category.SCIENCE, 30000);
        bookService.save(bookC);

        Review review1 = new Review();
        review1.setComment("comment from member A to book A");
        review1.setMember(memberA);
        review1.setBook(bookA);
        reviewService.save(review1);

        Review review2 = new Review();
        review2.setComment("comment from member A to book B");
        review2.setMember(memberA);
        review2.setBook(bookB);
        reviewService.save(review2);

        Review review3 = new Review();
        review3.setComment("comment from member B to book B");
        review3.setMember(memberB);
        review3.setBook(bookB);
        reviewService.save(review3);

        Review review4 = new Review();
        review4.setComment("comment from member B to book C");
        review4.setMember(memberB);
        review4.setBook(bookC);
        reviewService.save(review4);

        //when
        List<Review> reviewFromMemberA = reviewService.findByMemberId(saveIdA);
        for (Review review : reviewFromMemberA) {
            System.out.println("review.getComment = " + review.getComment());
        }

        List<Review> reviewFromMemberB = reviewService.findByMemberId(saveIdB);
        for (Review review : reviewFromMemberB) {
            System.out.println("review.getComment = " + review.getComment());
        }

        //then
        assertThat(reviewFromMemberA.size()).isEqualTo(2);
        assertThat(reviewFromMemberB.size()).isEqualTo(2);

    }

}