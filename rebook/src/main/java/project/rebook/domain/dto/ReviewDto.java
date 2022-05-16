package project.rebook.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import project.rebook.domain.Review;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReviewDto {

    private Long id;
    private String bookName;
    private String nickname;
    private int rate;
    private String comment;
    private LocalDate date;

    public static ReviewDto from(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getBook().getBookName(),
                review.getMember().getNickname(),
                review.getRate(),
                review.getComment(),
                review.getDate()
        );
    }

}
