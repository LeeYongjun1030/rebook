package project.rebook.web;

import lombok.Getter;
import lombok.Setter;
import project.rebook.domain.book.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class OrderForm {
    private List<Long> ids;
    private List<Integer> quantities;
}
