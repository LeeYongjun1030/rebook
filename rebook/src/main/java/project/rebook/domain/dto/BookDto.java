package project.rebook.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import project.rebook.domain.book.Book;
import project.rebook.domain.book.Category;

@Getter
@Setter
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String bookName;
    private String publisher;
    private Category category;
    private int price;

    public static BookDto from(Book book) {
        return new BookDto(
                book.getId(),
                book.getBookName(),
                book.getPublisher(),
                book.getCategory(),
                book.getPrice()
        );
    }
}
