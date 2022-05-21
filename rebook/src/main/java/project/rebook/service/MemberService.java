package project.rebook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.member.Grade;
import project.rebook.domain.member.Member;
import project.rebook.exception.UnusableLoginId;
import project.rebook.exception.UnusableNickname;
import project.rebook.repository.member.MemberRepository;
import project.rebook.web.AddMemberForm;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(Member member) {
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Member findByLoginId(String loginId) throws RuntimeException{
        return memberRepository.findByLoginId(loginId);
    }

    @Transactional(readOnly = true)
    public boolean isUsableLoginId(String loginId) {
        boolean isAlreadyPresent;
        try {
            memberRepository.findByLoginId(loginId);
            isAlreadyPresent = true;
        } catch (Exception e) {
            isAlreadyPresent = false;
        }

        if (isAlreadyPresent) {
            throw new UnusableLoginId();
        } else {
            return true;
        }
    }

    @Transactional(readOnly = true)
    public boolean isUsableNickname(String nickname) {
        boolean isAlreadyPresent;
        try {
            memberRepository.findByNickname(nickname);
            isAlreadyPresent = true;
        } catch (Exception e) {
            isAlreadyPresent = false;
        }

        if (isAlreadyPresent) {
            throw new UnusableNickname();
        } else {
            return true;
        }
    }

    @Transactional(readOnly = true)
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
