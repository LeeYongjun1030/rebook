package project.rebook.web;

import lombok.Getter;
import lombok.Setter;
import project.rebook.domain.book.Book;
import project.rebook.domain.member.Member;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter @Setter
public class ReviewDeleteDto {

    private Long id;

    private String bookName;

    private int rate;

    private String comment;

    private LocalDate date;
}
