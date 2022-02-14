package project.rebook.web;

import lombok.Getter;
import lombok.Setter;
import project.rebook.domain.OrderBook;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class OrderDto {

    private Long id;
    private String memberName;
    private LocalDate localDate;
    private int totalOrderQuantities;
    private int totalOrderPrice;
    private List<OrderBook> orderBooks = new ArrayList<>();
}
