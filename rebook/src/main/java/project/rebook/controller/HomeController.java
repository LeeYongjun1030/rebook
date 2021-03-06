package project.rebook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import project.rebook.domain.member.Member;
import project.rebook.domain.dto.MemberDto;
import project.rebook.service.MemberService;
import project.rebook.web.SessionConst;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class HomeController {

    private final MemberService memberService;

    /**
     * 홈 화면
     */
    @GetMapping
    public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {

        // 세션에 회원 데이터가 없으면 홈으로 이동
        if (loginMember == null) {
            return "home";
        }

        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", MemberDto.from(memberService.findById(loginMember.getId())));
        return "loginHome";
    }
}
