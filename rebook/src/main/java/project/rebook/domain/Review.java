package project.rebook.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import project.rebook.domain.book.Book;
import project.rebook.domain.member.Member;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Review {

    @Id @GeneratedValue
    @Column(name = "REVIEW_ID")
    private Long id;

    private String comment;

    private int rate;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

}
