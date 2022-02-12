package project.rebook.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.member.Grade;
import project.rebook.domain.member.Member;
import project.rebook.repository.member.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    void save() {
        //given
        Member member = new Member();
        member.setNickname("hi");
        member.setLoginId("111");
        member.setPassword("222");
        member.setGrade(Grade.NORMAL);
        member.setMoney(10000);

        //when
        Long memberId = memberService.save(member);

        //then
        Member findMember = memberService.findById(memberId);

        assertThat(findMember).isEqualTo(member);

    }

    @Test
    void findAll() {

        //given
        Member member = new Member();
        member.setNickname("hi");
        memberService.save(member);

        Member member2 = new Member();
        member2.setNickname("hi");
        memberService.save(member2);

        //when
        List<Member> findMembers = memberService.findAll();

        //then
        assertThat(findMembers).contains(member, member2);
    }

}