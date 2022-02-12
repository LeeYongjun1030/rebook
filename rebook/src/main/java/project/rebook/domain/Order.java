package project.rebook.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.rebook.domain.member.Member;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter @Setter
@NoArgsConstructor
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    private LocalDate localDate;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderBook> orderBooks = new ArrayList<>();

    private int totalPrice;



    // setOrderBooks 메서드 직접 정의의
   public void setOrderBooks(OrderBook... orderBooks) {
        List<OrderBook> orderBookList = new ArrayList<>();
        for (OrderBook orderBook : orderBooks) {
            orderBookList.add(orderBook);
        }
        this.orderBooks = orderBookList;
    }

}
