package project.rebook.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import project.rebook.domain.member.Member;
import project.rebook.service.MemberService;
import project.rebook.web.LoginForm;
import project.rebook.web.SessionConst;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    MemberService memberService;

    @MockBean
    Member member;

    @Test
    @DisplayName("로그인 폼 리턴")
    void loginForm() throws Exception {
        mvc.perform(
                get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login/loginForm"));
    }


    @Test
    @DisplayName("로그인 실패 : 폼 검증 실패")
    void loginFormFail() throws Exception {
        mvc.perform(
                post("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login/loginForm"));
    }

    @Test
    @DisplayName("로그인 실패 : 회원 정보 불일치")
    void loginInfoFail() throws Exception {
        //mocking
        when(memberService.findByLoginId(any()))
                .thenReturn(null);

        //when, then
        mvc.perform(
                post("/login")
                        .param("loginId", "test1234")
                        .param("password", "test1234"))
                .andExpect(status().isOk())
                .andExpect(view().name("login/loginForm"));
    }


    /**
     * 로그인 성공 시 원래 URL로 돌아가야 한다.
     */
    @Test
    @DisplayName("로그인 성공")
    void loginSuccess() throws Exception {

        //mocking
        when(memberService.findByLoginId(any())).thenReturn(member);
        when(member.verify(any(),any(),any())).thenReturn(true);

        //when, then
        mvc.perform(
                post("/login")
                        .param("loginId", "test1234")
                        .param("password", "test1234")
                        .param("redirectURL", "mockUrl"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:mockUrl"));
    }

    @Test
    @DisplayName("로그 아웃")
    void logout() throws Exception {
        Member loginMember = new Member();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        mvc.perform(
                get("/logout").session(session))
                .andExpect(status().is3xxRedirection());
    }

}