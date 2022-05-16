package project.rebook.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.rebook.domain.Order;
import project.rebook.domain.OrderBook;
import project.rebook.domain.member.Grade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderDetailDto {

    private Long id;
    private String memberName;
    private int numOfReviews;
    private Grade memberGrade;
    private LocalDate localDate;
    private int totalQuantities;
    private int totalPrice;
    private List<OrderBook> orderBooks = new ArrayList<>();

    private int priceWithDiscount;

    public static OrderDetailDto from(Order order, int priceWithDiscount) {
        return new OrderDetailDto(order.getId(),
                order.getMember().getNickname(),
                order.getMember().getNumberOfReviews(),
                order.getMember().getGrade(),
                order.getLocalDate(),
                order.getTotalQuantities(),
                order.getTotalPrice(),
                order.getOrderBooks(),
                priceWithDiscount);
    }
}
