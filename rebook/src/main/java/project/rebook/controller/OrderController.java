package project.rebook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.rebook.domain.Order;
import project.rebook.domain.dto.BookDto;
import project.rebook.domain.member.Member;
import project.rebook.service.BookService;
import project.rebook.service.OrderService;
import project.rebook.domain.dto.MemberDto;
import project.rebook.domain.dto.OrderDto;
import project.rebook.web.OrderForm;
import project.rebook.web.SessionConst;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final BookService bookService;

    /**
     * 주문 하기 폼
     */
    @GetMapping("/order")
    public String order(@ModelAttribute OrderForm orderForm, Model model) {
        List<BookDto> bookDtos = bookService.findAll().stream().map(BookDto::from).collect(Collectors.toList());
        model.addAttribute("books", bookDtos);
        return "order/orderForm";
    }

    @PostMapping("/order")
    public String orderCheck(@ModelAttribute OrderForm orderForm, Model model) {
        List<Long> ids = orderForm.getIds();
        if (ids.isEmpty()) {
            //선택된 항목이 없을 시 화면이 넘어가면 안됨
            return "redirect:/order";
        }

        List<BookDto> bookDtos = bookService.findBooksFromIds(ids).stream().map(BookDto::from).collect(Collectors.toList());
        model.addAttribute("books", bookDtos);
        return "order/addOrder";
    }

    /**
     * 주문 수량 입력 및 주문 완료
     */
    @PostMapping("/order/add")
    public String addOrder(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                           @ModelAttribute OrderForm orderForm) {

        orderService.order(loginMember, orderForm);
        return "redirect:/";
    }

    /**
     * 주문 목록 조회
     */
    @GetMapping("/orders")
    public String orders(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                         Model model) {

        List<Order> orders = orderService.findByMemberId(loginMember.getId());
        List<OrderDto> orderDtos = orders.stream().map(OrderDto::from).collect(Collectors.toList());
        model.addAttribute("orders", orderDtos);
        return "/order/orders";
    }

    /**
     * 주문 상세 조회
     */
    @GetMapping("/orders/{orderId}")
    public String orderDetail(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                              @PathVariable Long orderId, Model model) {

        OrderDto orderDto = OrderDto.from(orderService.findById(orderId));

        model.addAttribute("member", MemberDto.from(loginMember));
        model.addAttribute("order", orderDto);
        model.addAttribute("orderBooks", orderDto.getOrderBooks());
        model.addAttribute("totalPriceWithDiscount", orderService.getTotalPriceWithDiscount(loginMember, orderDto.getTotalOrderPrice()));
        return "order/detail";
    }

    /**
     * 주문 취소
     */
    @GetMapping("/order/cancel/{orderId}")
    public String orderCancel(@PathVariable Long orderId) {
        // CASCADE 설정으로 인해 order가 삭제되면 orderBook가 자동으로 삭제 됨
        orderService.delete(orderId);
        return "redirect:/orders";
    }
}
