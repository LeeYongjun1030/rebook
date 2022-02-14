package project.rebook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.rebook.domain.Order;
import project.rebook.domain.OrderBook;
import project.rebook.domain.book.Book;
import project.rebook.domain.member.Member;
import project.rebook.service.BookService;
import project.rebook.service.OrderService;
import project.rebook.web.MemberDto;
import project.rebook.web.OrderDto;
import project.rebook.web.OrderForm;
import project.rebook.web.SessionConst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final BookService bookService;

    /**
     * 주문 하기 폼
     */
    @GetMapping("/order")
    public String order(@ModelAttribute OrderForm orderForm,
                        Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String orderCheck(@ModelAttribute OrderForm orderForm,
                             Model model) {
        List<Book> books = new ArrayList<>();
        List<Long> ids = orderForm.getIds();

        //선택된 항목이 없을 시 화면이 넘어가면 안됨
        if (ids.isEmpty()) {
            return "redirect:/order";
        }

        for (Long id : ids) {
            Book book = bookService.findById(id);
            books.add(book);
        }
        model.addAttribute("books", books);

        return "order/addOrder";
    }

    /**
     * 주문 수량 입력 폼
     */
    @PostMapping("/order/add")
    public String addOrder(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                           @ModelAttribute OrderForm orderForm) {

        // 책과 수량을 확인하여 주문 생성
        Map<Long, Integer> orderBooks = new HashMap<>();
        List<Long> ids = orderForm.getIds();
        List<Integer> quantities = orderForm.getQuantities();
        for (int i = 0; i < ids.size(); i++) {
            orderBooks.put(ids.get(i), quantities.get(i));
        }
        Order order = orderService.order(loginMember, orderBooks);

        // 주문 저장
        orderService.save(order);

        return "redirect:/";
    }

    /**
     * 주문 목록 조회
     */
    @GetMapping("/orders")
    public String orders(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                         Model model) {

        List<Order> orders = orderService.findByMemberId(loginMember.getId());

        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setMemberName(loginMember.getNickname());
            orderDto.setLocalDate(order.getLocalDate());
            orderDto.setTotalOrderQuantities(orderService.getOrderTotalQuantities(order));
            orderDto.setTotalOrderPrice(orderService.getOrderTotalPrice(order));
            orderDtos.add(orderDto);
        }

        model.addAttribute("orders", orderDtos);

        return "/order/orders";
    }

    /**
     * 주문 상세 조회
     */
    @GetMapping("/orders/{orderId}")
    public String orderDetail(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                              @PathVariable Long orderId, Model model) {

        Order order = orderService.findById(orderId);

        // memberDto 생성
        MemberDto memberDto = new MemberDto();
        memberDto.setNickname(loginMember.getNickname());
        memberDto.setGrade(loginMember.getGrade());
        model.addAttribute("member", memberDto);

        // orderDto 생성
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setMemberName(order.getMember().getNickname());
        orderDto.setLocalDate(order.getLocalDate());
        orderDto.setTotalOrderQuantities(orderService.getOrderTotalQuantities(order));
        orderDto.setTotalOrderPrice(orderService.getOrderTotalPrice(order));
        orderDto.setOrderBooks(order.getOrderBooks());

        // 할인 정보 생성
        int totalPriceWithDiscount = orderService.getTotalPriceWithDiscount(loginMember, orderDto.getTotalOrderPrice());

        model.addAttribute("member", memberDto);
        model.addAttribute("order", orderDto);
        model.addAttribute("orderBooks", orderDto.getOrderBooks());
        model.addAttribute("totalPriceWithDiscount", totalPriceWithDiscount);
        return "order/detail";
    }

    /**
     * 주문 취소
     */
    @GetMapping("/order/cancel/{orderId}")
    public String orderCancel(@PathVariable Long orderId) {

        Order order = orderService.findById(orderId);

        // CASCADE 설정으로 인해 order가 삭제되면 orderBook가 자동으로 삭제 됨
        orderService.delete(order);


        return "redirect:/orders";
    }



}
