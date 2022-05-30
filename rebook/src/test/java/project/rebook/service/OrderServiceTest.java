package project.rebook.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import project.rebook.domain.Order;
import project.rebook.domain.OrderBook;
import project.rebook.domain.book.Book;
import project.rebook.domain.book.Category;
import project.rebook.domain.member.Member;
import project.rebook.repository.book.BookRepository;
import project.rebook.repository.order.OrderRepository;
import project.rebook.web.OrderForm;
import java.util.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    BookRepository bookRepository;

    @Test
    @DisplayName("주문 저장")
    void save() {

        //given
        Order order = new Order();
        Long orderId = 1L;

        //mocking
        given(orderRepository.save(any()))
                .willReturn(orderId);
        given(orderRepository.findById(orderId))
                .willReturn(order);

        //when
        Long findId = orderService.save(order);
        Order findOrder = orderRepository.findById(findId);

        //then
        assertThat(findOrder).isEqualTo(order);
    }

    @Test
    @DisplayName("회원의 주문 목록 조회")
    void findByMemberId() {
        //given
        List<Order> orders = new ArrayList<>();
        Long memberId = 1L;

        //mocking
        given(orderRepository.findByMemberId(memberId))
                .willReturn(orders);

        //when
        List<Order> findOrders = orderService.findByMemberId(memberId);

        //then
        assertThat(findOrders).isEqualTo(orders);

    }

    @Test
    @DisplayName("주문 생성")
    void createOrder() {
        //given
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);

        List<Integer> quantities = new ArrayList<>();
        quantities.add(10);
        quantities.add(20);

        OrderForm orderForm = new OrderForm(ids, quantities);

        List<Book> books = new ArrayList<>();
        books.add(new Book("test1", "testPublisher", Category.COMPUTER, 10000));
        books.add(new Book("test2", "testPublisher", Category.COMPUTER, 20000));

        //mocking
        given(bookRepository.findByIdList(any())).willReturn(books);

        //when
        Order findOrder = orderService.order(new Member(), orderForm);

        //then

        //총 수량 : 10 + 20 = 30
        //총 가격 : 10*10,000 + 20*20,000 = 500,000
        Assertions.assertThat(findOrder.getTotalQuantities()).isEqualTo(30);
        Assertions.assertThat(findOrder.getTotalPrice()).isEqualTo(500000);
    }
}