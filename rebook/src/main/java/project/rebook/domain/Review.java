package project.rebook.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import project.rebook.domain.book.Book;
import project.rebook.domain.member.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id @GeneratedValue
    @Column(name = "REVIEW_ID")
    private Long id;

    private String comment;

    private int rate;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public Review(String comment, int rate, LocalDate date, Member member, Book book) {
        this.comment = comment;
        this.rate = rate;
        this.date = date;
        this.member = member;
        this.book = book;
    }
}
