package project.rebook.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.rebook.domain.OrderBook;

@Getter
@AllArgsConstructor
public class OrderBookDto {

    private Long id;
    private BookDto book;
    private int quantity;

    public static OrderBookDto from(OrderBook orderBook) {
        return new OrderBookDto(
                orderBook.getId(),
                BookDto.from(orderBook.getBook()),
                orderBook.getQuantity());
    }
}
