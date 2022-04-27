package project.rebook.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.rebook.domain.member.Grade;
import project.rebook.domain.member.Member;
import project.rebook.service.MemberService;
import project.rebook.web.AddMemberForm;

import java.util.Optional;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 가입 폼
     */
    @GetMapping("/add")
    public String add(@ModelAttribute AddMemberForm addMemberForm) {
        return "/member/addForm";
    }

    @PostMapping("/add")
    public String addMember(@Validated @ModelAttribute AddMemberForm addMemberForm, BindingResult bindingResult) {

        // 아이디, 닉네임 중복 검사
        idDuplicateTest(addMemberForm, bindingResult);
        nicknameDuplicateTest(addMemberForm, bindingResult);

        // 검증 오류로 인한 회원 가입 실패
        if (bindingResult.hasErrors()) {
            return "/member/addForm";
        }

        // 회원가입 성공 -> 회원 정보 초기화
        Member member = new Member(
                addMemberForm.getNickname(),
                addMemberForm.getLoginId(),
                addMemberForm.getPassword(),
                Grade.NORMAL);

        //회원 저장
        memberService.save(member);

        return "redirect:/";
    }

    private void idDuplicateTest(AddMemberForm addMemberForm, BindingResult bindingResult) {
        String loginId = addMemberForm.getLoginId();
        if (memberService.existLoginId(loginId)) { //아이디 중복
            bindingResult.rejectValue("loginId", "duplicate");
        }

    }

    private void nicknameDuplicateTest(AddMemberForm addMemberForm, BindingResult bindingResult) {
        String nickname = addMemberForm.getNickname();
        if (memberService.existNickname(nickname)) { //닉네임 중복
            bindingResult.rejectValue("nickname", "duplicate");
        }
    }

}
