package project.rebook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.member.Grade;
import project.rebook.domain.member.Member;
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

    @Transactional
    public void createMember(AddMemberForm addMemberForm) {
        save(new Member(addMemberForm.getNickname(),
                addMemberForm.getLoginId(),
                passwordEncoder.encode(addMemberForm.getPassword()),
                0,
                Grade.NORMAL));
    }

    @Transactional(readOnly = true)
    public boolean verify(Member member, String id, String password) {
        String existLoginId = member.getLoginId();
        String existPassword = member.getPassword();
        return existLoginId.equals(id) && passwordEncoder.matches(password, existPassword);
    }
}
