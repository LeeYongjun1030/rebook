package project.rebook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.member.Grade;
import project.rebook.domain.member.Member;
import project.rebook.repository.member.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    //memberRepository 자동 주입
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(Member member) {
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Member findByLoginId(String loginId) {
        try {
            return memberRepository.findByLoginId(loginId);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public boolean existLoginId(String loginId) {
        return memberRepository.existLoginId(loginId);
    }

    @Transactional(readOnly = true)
    public boolean existNickname(String nickname) {
        return memberRepository.existNickname(nickname);
    }

    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Transactional
    public void updateGrade(Long id, Grade grade) {
        memberRepository.updateGrade(id, grade);
    }

    @Transactional
    public void clear() {
        memberRepository.clear();
    }
}
