package project.rebook.repository.member;

import project.rebook.domain.member.Grade;
import project.rebook.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    public Long save(Member member);

    public Member findById(Long id);

    public Member findByLoginId(String loginId) throws RuntimeException;

    public Member findByNickname(String nickname) throws RuntimeException;

    public List<Member> findAll();

    public void clear();
}
