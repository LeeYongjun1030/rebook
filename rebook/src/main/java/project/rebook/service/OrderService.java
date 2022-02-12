package project.rebook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.Order;
import project.rebook.domain.OrderBook;
import project.rebook.repository.order.OrderRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Long save(Order order) {
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findByMemberId(Long memberId) {
        return orderRepository.findByMemberId(memberId);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

    public void clear() {
        orderRepository.clear();
    }

    public int getOrderTotalPrice(Order order) {
        int totalPrice = 0;

        List<OrderBook> orderBooks = order.getOrderBooks();
        for (OrderBook orderBook : orderBooks) {
            int price = orderBook.getBook().getPrice();
            int quantity = orderBook.getQuantity();
            totalPrice += price * quantity;
        }

        return totalPrice;
    }
}
