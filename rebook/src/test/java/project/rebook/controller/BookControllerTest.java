package project.rebook.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import project.rebook.domain.book.Book;
import project.rebook.domain.book.Category;
import project.rebook.service.BookService;
import project.rebook.service.ReviewService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private ReviewService reviewService;


    @Test
    @DisplayName("책 목록")
    public void books() throws Exception {
        mvc.perform(get("/books"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("책 상세 보기")
    public void bookInfo() throws Exception {
        Book book = new Book("testBook", "publisher", Category.SCIENCE, 10000);

        Long bookId = 1L;
        when(bookService.findById(bookId))
                .thenReturn(book);

        MvcResult mvcResult = mvc.perform(
                get("/books/bookId")
                .param("bookId", bookId.toString()))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println("mvcResult = " + mvcResult);
    }
}