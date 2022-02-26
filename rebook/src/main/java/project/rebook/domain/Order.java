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
@Getter @Setter
@NoArgsConstructor
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    private LocalDate localDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderBook> orderBooks = new ArrayList<>();


    /**
     * 주문 생성
     * 주의할 점 : orderBooks는 연관관계의 주인이 아니다.
     * 연관관계 주인인 orderbook의 order에도 반드시 값을 대입해주어야 함
     */
    public static Order makeOrder(Member member, List<OrderBook> orderBooks) {
        Order order = new Order();
        order.setMember(member);
        order.setLocalDate(LocalDate.now());

        List<OrderBook> orderBookList = new ArrayList<>();
        for (OrderBook orderBook : orderBooks) {
            orderBook.setOrder(order); //연관관계 주인
            orderBookList.add(orderBook); // 리스트 조회를 위한 메서드
        }
        order.setOrderBooks(orderBookList);
        return order;
    }
}
