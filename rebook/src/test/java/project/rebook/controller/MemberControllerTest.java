package project.rebook.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.rebook.domain.member.Member;
import project.rebook.exception.UnusableLoginId;
import project.rebook.exception.UnusableNickname;
import project.rebook.service.MemberService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    MemberService memberService;


    @Test
    @DisplayName("회원가입 성공")
    void add() throws Exception{
        mvc.perform(
                post("/member/add")
                        .param("loginId", "test1234")
                        .param("password", "testTEST1")
                        .param("nickname", "테스트"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

    }



    @Test
    @DisplayName("회원가입 실패 - 폼 검증 오류")
    void invalidForm() throws Exception{
        mvc.perform(
                post("/member/add")
                        .param("loginId", "test1234")
                        .param("password", "test1234")
                        .param("nickname", "test1234"))
                .andExpect(status().isOk())
                .andExpect(view().name("/member/addForm"));
    }




    @Test
    @DisplayName("회원가입 실패 - 아이디 중복")
    void duplicateLoginId() throws Exception{
        //mocking
        when(memberService.isUsableLoginId(any())).thenThrow(new UnusableLoginId());

        mvc.perform(
                post("/member/add")
                        .param("loginId", "test1234")
                        .param("password", "testTEST1")
                        .param("nickname", "테스트"))
                .andExpect(status().isOk())
                .andExpect(view().name("/member/addForm"));
    }

    @Test
    @DisplayName("회원가입 실패 - 닉네임 중복")
    void duplicateNickname() throws Exception{
        //mocking
        when(memberService.isUsableNickname(any())).thenThrow(new UnusableNickname());

        mvc.perform(
                post("/member/add")
                        .param("loginId", "test1234")
                        .param("password", "testTEST1")
                        .param("nickname", "테스트"))
                .andExpect(status().isOk())
                .andExpect(view().name("/member/addForm"));
    }
}