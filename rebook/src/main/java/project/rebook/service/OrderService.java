package project.rebook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.Order;
import project.rebook.domain.OrderBook;
import project.rebook.domain.book.Book;
import project.rebook.domain.member.Member;
import project.rebook.repository.book.BookRepository;
import project.rebook.repository.order.OrderRepository;
import project.rebook.web.OrderForm;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    @Transactional
    public Long save(Order order) {
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findByMemberId(Long memberId) {
        return orderRepository.findByMemberId(memberId);
    }

    @Transactional
    public void delete(Long id) {
        orderRepository.delete(id);
    }

    @Transactional
    public void clear() {
        orderRepository.clear();
    }


    /**
     * orderBook, order를 만드는 메서드는 이미 클래스 안에 static으로 작성해두었다.(도메인 주도 설계)
     * 여기선 그 기능을 이용하여 직접 order를 작성해주는 서비스를 만들면 된다.
     * 인자로 받는 orderInfo의 키는 책 id, 값은 주문 수량이다.
     */
    @Transactional
    public Order order(Member member, OrderForm orderForm) {

        // 책과 수량을 확인하여 주문 생성
        List<Book> books = bookRepository.findByIdList(orderForm.getIds());
        List<Integer> quantities = orderForm.getQuantities();

        List<OrderBook> orderBooks = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            // orderbook 생성
            OrderBook orderBook = OrderBook.makeOrderBook(books.get(i), quantities.get(i));
            orderBooks.add(orderBook);
        }

        // 주문 생성
        Order order = Order.makeOrder(member, orderBooks);

        // 주문 저장
        orderRepository.save(order);
        return order;
    }
}
