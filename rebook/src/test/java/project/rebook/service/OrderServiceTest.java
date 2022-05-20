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

        //static method mocking
        MockedStatic<OrderBook> orderBook = mockStatic(OrderBook.class);
        MockedStatic<Order> order = mockStatic(Order.class);

        //given
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);

        List<Integer> quantities = new ArrayList<>();
        quantities.add(10);
        quantities.add(20);

        OrderForm orderForm = new OrderForm(ids, quantities);

        Book book = new Book();
        Long bookId = 1L;
        Book book2= new Book();
        Long book2Id = 2L;

        List<OrderBook> orderBooks = new ArrayList<>();
        OrderBook orderBook1 = OrderBook.makeOrderBook(book, quantities.get(0));
        OrderBook orderBook2 = OrderBook.makeOrderBook(book2, quantities.get(1));
        orderBooks.add(orderBook1);
        orderBooks.add(orderBook2);

        Member member = new Member();
        Order mockOrder = Order.makeOrder(member, orderBooks);

        //mocking
        given(bookRepository.findById(bookId)).willReturn(book);
        given(bookRepository.findById(book2Id)).willReturn(book2);
        given(OrderBook.makeOrderBook(book, quantities.get(0))).willReturn(orderBook1);
        given(OrderBook.makeOrderBook(book2, quantities.get(1))).willReturn(orderBook2);
        given(Order.makeOrder(member, orderBooks)).willReturn(mockOrder);

        //when
        Order findOrder = orderService.order(new Member(), orderForm);

        //then
        Assertions.assertThat(findOrder).isEqualTo(mockOrder);

        orderBook.close();
        order.close();
    }
}