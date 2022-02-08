package project.rebook.repository.member;

import project.rebook.domain.member.Member;

import java.util.List;

public interface MemberRepository {

    public Long save(Member member);

    public Member findById(Long id);

    public List<Member> findAll();

    public void clear();
}
