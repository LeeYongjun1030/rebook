package project.rebook.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.rebook.domain.Order;
import project.rebook.domain.OrderBook;
import project.rebook.domain.member.Grade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private LocalDate localDate;
    private int totalQuantities;
    private int totalPrice;
    private List<OrderBookDto> orderBooks = new ArrayList<>();

    public static OrderDto from(Order order) {
        List<OrderBookDto> orderBookDtos = order.getOrderBooks().stream().map(OrderBookDto::from).collect(Collectors.toList());

        return new OrderDto(order.getId(),
                order.getLocalDate(),
                order.getTotalQuantities(),
                order.getTotalPrice(),
                orderBookDtos);
    }
}
