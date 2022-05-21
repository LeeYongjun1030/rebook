package project.rebook.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.rebook.domain.member.Member;
import project.rebook.service.MemberService;
import project.rebook.web.SessionConst;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("미로그인 시 홈 화면")
    public void home() throws Exception {
        mvc.perform(
                get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }


    @Test
    @DisplayName("로그인 상태일때 홈 화면")
    public void homeLogin() throws Exception {
        //given
        Member member = new Member();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);

        //mocking
        when(memberService.findById(any()))
                .thenReturn(member);

        //when, then
        mvc.perform(
                get("/").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("loginHome"));


        session.clearAttributes();
    }
}