package project.rebook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.member.Grade;
import project.rebook.domain.member.Member;
import project.rebook.repository.member.MemberRepository;
import project.rebook.web.AddMemberForm;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(Member member) {
        return memberRepository.save(member);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    public Member findByLoginId(String loginId) throws RuntimeException{
        return memberRepository.findByLoginId(loginId);
    }

    public boolean duplicateLoginId(String loginId) {
        try {
            memberRepository.findByLoginId(loginId);
            return true;
        } catch (RuntimeException e) {
            // loginId 조회 결과 없음 -> 사용 가능한 loginId
            return false;
        }
    }

    public boolean duplicateNickname(String nickname) {
        try {
            memberRepository.findByNickname(nickname);
            return true;
        } catch (RuntimeException e) {
            // 닉네임 조회 결과 없음 -> 사용 가능한 닉네임
            return false;
        }
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Transactional
    public void clear() {
        memberRepository.clear();
    }

    @Transactional
    public Long createMember(AddMemberForm addMemberForm) {
        return memberRepository.save((new Member(addMemberForm.getNickname(),
                addMemberForm.getLoginId(),
                passwordEncoder.encode(addMemberForm.getPassword()),
                0,
                Grade.NORMAL)));
    }
}
