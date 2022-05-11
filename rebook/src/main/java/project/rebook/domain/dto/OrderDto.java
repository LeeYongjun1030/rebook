package project.rebook.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import project.rebook.domain.Order;
import project.rebook.domain.OrderBook;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private String memberName;
    private LocalDate localDate;
    private int totalOrderQuantities;
    private int totalOrderPrice;
    private List<OrderBook> orderBooks = new ArrayList<>();

    public static OrderDto from(Order order) {
        return new OrderDto(order.getId(),
                order.getMember().getNickname(),
                order.getLocalDate(),
                order.getTotalQuantities(),
                order.getTotalPrice(),
                order.getOrderBooks());
    }
}
