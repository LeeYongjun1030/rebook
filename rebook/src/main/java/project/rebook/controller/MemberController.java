package project.rebook.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.rebook.domain.Member;
import project.rebook.repository.MemberRepository;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping
    @ResponseBody
    public String test() {
        Member member = new Member();
        member.setName("lee");
        memberRepository.save(member);

        return "ok";
    }
}
