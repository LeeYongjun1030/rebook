package project.rebook.controller.login;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.rebook.domain.member.Member;
import project.rebook.service.MemberService;
import project.rebook.web.LoginForm;
import project.rebook.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {

        //아이디, 비밀번호 일치 여부 확인
        Member loginMember = login(loginForm);
        if (loginMember == null) {
            bindingResult.reject("fail");
        }

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        // 로그인 성공 -> 로그인 유지를 위한 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";

    }

    private Member login(LoginForm loginForm) {
        String loginId = loginForm.getLoginId();
        String password = loginForm.getPassword();

        return memberService.findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .filter(m -> m.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
