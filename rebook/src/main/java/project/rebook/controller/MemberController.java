package project.rebook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.rebook.service.MemberService;
import project.rebook.web.AddMemberForm;

@Controller
@Slf4j
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

    /**
     * 회원 가입 검증 및 등록
     */
    @PostMapping("/add")
    public String addMember(@Validated @ModelAttribute AddMemberForm addMemberForm, BindingResult bindingResult) {
        // 아이디, 닉네임 중복 검사
        duplicateTest(addMemberForm, bindingResult);

        // 검증 오류로 인한 회원 가입 실패
        if (bindingResult.hasErrors()) {
            return "/member/addForm";
        }

        // 회원가입 성공 -> 회원 정보 저장
        memberService.createMember(addMemberForm);
        return "redirect:/";
    }

    private void duplicateTest(AddMemberForm addMemberForm, BindingResult bindingResult) {
        // 아이디 중복 검사
        if(!memberService.isUsableLoginId(addMemberForm.getLoginId())){
            bindingResult.rejectValue("loginId", "duplicate");
        }

        // 닉네임 중복 검사
        if(!memberService.isUsableNickname(addMemberForm.getNickname())){
            bindingResult.rejectValue("nickname", "duplicate");
        }
    }
}
