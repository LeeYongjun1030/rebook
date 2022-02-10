package project.rebook.web;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter @Setter
public class AddReviewForm {

    @Range(min = 1, max = 5)
    private int rate;

    @Size(min = 1, max = 30)
    private String comment;
}
