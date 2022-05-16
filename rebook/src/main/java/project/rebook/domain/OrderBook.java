package project.rebook.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import project.rebook.domain.book.Book;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class OrderBook {

    @Id @GeneratedValue
    @Column(name = "ORDER_BOOK_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    private int quantity; // 수량


    private OrderBook(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }


    /**
     * 비즈니스 로직
     */

    // Order 지정
    public void assignOrder(Order order) {
        this.order = order;
    }

    // Orderbook 생성
    public static OrderBook makeOrderBook(Book book, int quantity) {
        return new OrderBook(book, quantity);
    }


}
