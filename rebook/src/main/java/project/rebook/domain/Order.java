package project.rebook.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import project.rebook.domain.member.Member;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@NoArgsConstructor
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private LocalDate localDate;

    private int totalQuantities;

    private int totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderBook> orderBooks = new ArrayList<>();


    public void addOrderInfo(Member member, LocalDate localDate, int totalQuantities, int totalPrice) {
        this.member = member;
        this.localDate = localDate;
        this.totalQuantities = totalQuantities;
        this.totalPrice = totalPrice;
    }

    // 연관 관계 편의 메서드
    public void addOrderBook(OrderBook orderBook) {
        this.orderBooks.add(orderBook);
        orderBook.assignOrder(this);
    }


    /**
     * 비즈니스 로직
     */

    // 주문 생성
    public static Order makeOrder(Member member, List<OrderBook> orderBooks) {

        Order order = new Order();

        int totalQuantities = 0;
        int totalPrice = 0;

        for (OrderBook orderBook : orderBooks) {
            int quantity = orderBook.getQuantity();
            totalQuantities += quantity;
            totalPrice += orderBook.getBook().getPrice()*quantity;

            order.addOrderBook(orderBook);
        }
        order.addOrderInfo(member, LocalDate.now(), totalQuantities, totalPrice);
        return order;
    }

    // 결제 금액
    public int priceWithDiscount(DiscountPolicy discountPolicy) {
        return discountPolicy.discount(this.member, this.totalPrice);
    }
}
