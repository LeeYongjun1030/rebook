package project.rebook.domain.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Book {

    @Id @GeneratedValue
    @Column(name = "BOOK_ID")
    private Long id;

    private String bookName;

    private String publisher;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int price;

    public Book(String bookName, String publisher, Category category, int price) {
        this.bookName = bookName;
        this.publisher = publisher;
        this.category = category;
        this.price = price;
    }
}
