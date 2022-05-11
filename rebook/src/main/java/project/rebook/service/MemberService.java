package project.rebook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.member.Grade;
import project.rebook.domain.member.Member;
import project.rebook.repository.member.MemberRepository;

import java.util.List;
import java.util.Optional;

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
    public Member findByLoginId(String loginId) throws Exception {
        return memberRepository.findByLoginId(loginId);
    }

    @Transactional(readOnly = true)
    public Member findByNickname(String nickname) throws Exception{
        return memberRepository.findByNickname(nickname);

    }

    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Transactional
    public void clear() {
        memberRepository.clear();
    }
}
