package project.rebook.service;

import org.aspectj.weaver.ast.Or;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.Order;
import project.rebook.domain.OrderBook;
import project.rebook.domain.book.Book;
import project.rebook.domain.member.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired MemberService memberService;
    @Autowired BookService bookService;

    @Test
    void save() {
        //given
        Order order = new Order();
        Long orderId = orderService.save(order);

        //when
        Order findOrder = orderService.findById(orderId);

        //then
        assertThat(findOrder).isEqualTo(order);
    }

    @Test
    void findByMemberId() {
        //given
        Member member = new Member();
        Long memberId = memberService.save(member);

        Order order1 = new Order();
        order1.setMember(member);
        orderService.save(order1);

        Order order2 = new Order();
        order2.setMember(member);
        orderService.save(order2);

        //when
        List<Order> orders = orderService.findByMemberId(memberId);

        //then
        assertThat(orders).contains(order1, order2);

    }

    @Test
    void getOrderTotalPrice() {

        // given
        Book book1 = new Book();
        book1.setPrice(3000);
        bookService.save(book1);

        Book book2 = new Book();
        book2.setPrice(4000);
        bookService.save(book2);

        OrderBook orderBook1 = new OrderBook();
        orderBook1.setBook(book1);
        orderBook1.setQuantity(3);

        OrderBook orderBook2 = new OrderBook();
        orderBook2.setBook(book2);
        orderBook2.setQuantity(5);

        Order order = new Order();
        order.setOrderBooks(orderBook1, orderBook2);
        Long orderId = orderService.save(order);

        //when
        Order findOrder = orderService.findById(orderId);

        //then
        int orderTotalPrice = orderService.getOrderTotalPrice(findOrder);
        assertThat(orderTotalPrice).isEqualTo(3000*3 + 4000*5);
    }

}