package project.rebook.repository.member;

import project.rebook.domain.member.Grade;
import project.rebook.domain.member.Member;

import java.util.List;

public interface MemberRepository {

    public Long save(Member member);

    public Member findById(Long id);

    public Member findByLoginId(String loginId) throws Exception;

    public List<Member> findAll();

    public boolean existLoginId(String loginId);

    public boolean existNickname(String nickname);

    public void updateGrade(Long id, Grade grade);

    public void clear();
}
