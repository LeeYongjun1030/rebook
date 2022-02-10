package project.rebook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.member.Member;
import project.rebook.repository.member.MemberRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    //memberRepository 자동 주입
    private final MemberRepository memberRepository;

    public Long save(Member member) {
        return memberRepository.save(member);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public void updateGrade(Member member) {
        memberRepository.updateGrade(member);
    }

    public void clear() {
        memberRepository.clear();
    }
}
