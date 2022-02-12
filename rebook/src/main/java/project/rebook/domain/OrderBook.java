package project.rebook.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.rebook.domain.book.Book;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class OrderBook {

    @Id @GeneratedValue
    @Column(name = "ORDER_BOOK_ID")
    private Long id;

    private int quantity; // 수량

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;
}
