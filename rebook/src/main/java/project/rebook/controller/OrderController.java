package project.rebook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import project.rebook.domain.Order;
import project.rebook.domain.OrderBook;
import project.rebook.domain.book.Book;
import project.rebook.domain.member.Member;
import project.rebook.service.BookService;
import project.rebook.service.OrderService;
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

    @GetMapping("/order")
    public String order(@ModelAttribute OrderForm orderForm,
                        Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String orderCheck(@ModelAttribute OrderForm orderForm, Model model) {
        List<Book> books = new ArrayList<>();
        List<Long> ids = orderForm.getIds();
        for (Long id : ids) {
            Book book = bookService.findById(id);
            books.add(book);
        }
        model.addAttribute("books", books);

        return "order/addOrder";
    }

    @PostMapping("/order/add")
    public String addOrder(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                                @ModelAttribute OrderForm orderForm,
                                Model model) {

        Map<Long, Integer> orderBooks = new HashMap<>();

        List<Book> books = (List<Book>) model.getAttribute("books");
        System.out.println("books = " + books);

        List<Long> ids = orderForm.getIds();
        System.out.println("ids = " + ids);


        OrderForm orderForm2 = (OrderForm) model.getAttribute("orderForm");
        List<Long> idss = orderForm2.getIds();
        System.out.println("idss = " + idss);

        List<Integer> quantities = orderForm.getQuantities();
        System.out.println("quantities = " + quantities);

        for (int i = 0; i < ids.size(); i++) {
            orderBooks.put(ids.get(i), quantities.get(i));
        }


// 해결 요망
//        books = null
//        ids = null
//        idss = null
//        quantities = [3, 4]


        // 책과 수량을 확인하여 주문 생성
        Order order = orderService.order(loginMember, orderBooks);
        orderService.save(order);
        System.out.println("orderTotalPrice = " + orderService.getOrderTotalPrice(order));

        return "redirect:/";
    }


}
