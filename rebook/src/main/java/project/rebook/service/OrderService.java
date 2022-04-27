package project.rebook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.DiscountPolicy;
import project.rebook.domain.Order;
import project.rebook.domain.OrderBook;
import project.rebook.domain.member.Grade;
import project.rebook.domain.member.Member;
import project.rebook.repository.book.BookRepository;
import project.rebook.repository.order.OrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final DiscountPolicy discountPolicy;

    @Transactional
    public Long save(Order order) {
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Order> findByMemberId(Long memberId) {
        return orderRepository.findByMemberId(memberId);
    }

    @Transactional
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Transactional
    public void clear() {
        orderRepository.clear();
    }


    /**
     * orderBook, order를 만드는 메서드는 이미 클래스 안에 static으로 작성해두었다.
     * 여기선 그 기능을 이용하여 직접 order를 작성해주는 서비스를 만들면 된다.
     * 인자로 받는 orderInfo의 키는 책 id, 값은 주문 수량이다.
     */
    @Transactional
    public Order order(Member member, Map<Long, Integer> orderInfo) {

        List<OrderBook> orderBooks = new ArrayList<>();
        for (Long bookId : orderInfo.keySet()) {
            Integer quantity = orderInfo.get(bookId);

            OrderBook orderBook = OrderBook.makeOrderBook(bookRepository.findById(bookId), quantity);
            orderBooks.add(orderBook);
        }
        return Order.makeOrder(member, orderBooks);
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

    public int getOrderTotalQuantities(Order order) {
        int totalQuantities = 0;

        List<OrderBook> orderBooks = order.getOrderBooks();
        for (OrderBook orderBook : orderBooks) {
            totalQuantities += orderBook.getQuantity();
        }
        return totalQuantities;
    }

    public int getTotalPriceWithDiscount(Member member, int totalPrice) {
        return discountPolicy.discount(member, totalPrice);
    }
}
