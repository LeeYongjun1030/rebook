package project.rebook.domain.book;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Book {

    @Id @GeneratedValue
    @Column(name = "BOOK_ID")
    private Long id;

    private String bookName;

    private String publisher;

    private Category category;

    private int price;

}
