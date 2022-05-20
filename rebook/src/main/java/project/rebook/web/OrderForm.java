package project.rebook.web;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
public class OrderForm {

    private List<Long> ids;
    private List<Integer> quantities;


    public OrderForm(List<Long> ids, List<Integer> quantities) {
        this.ids = ids;
        this.quantities = quantities;
    }
}
