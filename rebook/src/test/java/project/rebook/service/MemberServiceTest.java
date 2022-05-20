package project.rebook.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.rebook.domain.member.Member;
import project.rebook.exception.UnusableLoginId;
import project.rebook.exception.UnusableNickname;
import project.rebook.repository.member.MemberRepository;
import project.rebook.web.AddMemberForm;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("멤버 저장")
    void save() {
        //given
        Member member = new Member();
        Long memberId = 1L;

        //mocking
        given(memberRepository.save(any()))
                .willReturn(memberId);
        given(memberRepository.findById(memberId))
                .willReturn(member);

        //when
        Long saveId = memberService.save(member);
        Member findMember = memberRepository.findById(saveId);

        //then
        assertThat(findMember).isEqualTo(member);

    }

    @Test
    @DisplayName("멤버 모두 찾기")
    void findAll() {
        //given
        Member member = new Member();
        Member member2 = new Member();

        List<Member> members = new ArrayList<>();
        members.add(member);
        members.add(member2);

        //mocking
        given(memberRepository.findAll())
                .willReturn(members);

        //when
        List<Member> findBooks = memberService.findAll();

        //then
        assertThat(findBooks).isEqualTo(members);
    }

    @Test
    @DisplayName("멤버 폼으로 멤버 생성")
    void createMember() {
        //given
        AddMemberForm addMemberForm = new AddMemberForm();
        addMemberForm.setLoginId("testLoginId");
        addMemberForm.setPassword("testPassword");
        addMemberForm.setNickname("testNickname");
        Long memberId = 1L;

        //mocking
        given(memberRepository.save(any()))
                .willReturn(memberId);

        // when
        Long findMemberId = memberService.createMember(addMemberForm);

        //then
        assertThat(findMemberId).isEqualTo(memberId);
    }

    @Test
    @DisplayName("로그인 아이디가 중복되면 오류 발생")
    void unusableLoginId() {
        //given
        String loginId = "test";

        //mocking
        given(memberRepository.findByLoginId(loginId))
                .willReturn(new Member());


        //then
        Assertions.assertThrows(UnusableLoginId.class, () -> {
            memberService.isUsableLoginId(loginId);
        });

    }

    @Test
    @DisplayName("로그인 아이디가 중복되지 않으면 오류 발생 x")
    void usableLoginId() {
        //given
        String loginId = "test";

        //mocking
        given(memberRepository.findByLoginId(loginId))
                .willThrow(new RuntimeException());

        //then
        Assertions.assertDoesNotThrow(()->{
            memberService.isUsableLoginId(loginId);
        });
    }

    @Test
    @DisplayName("닉네임이 중복되면 에러 발생")
    void 닉네임_중복o() {
        //given
        String nickname = "test";

        //mocking
        given(memberRepository.findByNickname(nickname))
                .willReturn(new Member());


        //then
        Assertions.assertThrows(UnusableNickname.class, () -> {
            memberService.isUsableNickname(nickname);
        });


    }

    @Test
    @DisplayName("닉네임이 중복되지 않으면 오류 발생 x")
    void 닉네임_중복x() {
        //given
        String loginId = "test";

        //mocking
        given(memberRepository.findByLoginId(loginId))
                .willThrow(new RuntimeException());

        //then
        Assertions.assertDoesNotThrow(()->{
            memberService.isUsableLoginId(loginId);
        });


    }

}