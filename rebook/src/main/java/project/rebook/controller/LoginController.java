package project.rebook.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.rebook.domain.member.Member;
import project.rebook.service.MemberService;
import project.rebook.web.LoginForm;
import project.rebook.web.SessionConst;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    /**
     * 로그인
     */
    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {

        // 검증 결과: 오류
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        // 로그인
        try {
            Member member = memberService.findByLoginId(loginForm.getLoginId());
            if (memberService.verify(member, loginForm.getLoginId(), loginForm.getPassword())) {
                // 로그인 성공 -> 로그인 유지를 위한 세션 생성
                HttpSession session = request.getSession();
                session.setAttribute(SessionConst.LOGIN_MEMBER, member);
                return "redirect:" + redirectURL;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            // 로그인 실패
            bindingResult.reject("fail");
            return "login/loginForm";
        }
    }

    /**
     * 로그아웃
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
