package project.rebook.repository.member;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import project.rebook.domain.member.Grade;
import project.rebook.domain.member.Member;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class DbMemberRepository implements MemberRepository {

    //em 자동 주입
    private final EntityManager em;

    @Override
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    @Override
    public Member findById(Long id) {
        Member member = em.find(Member.class, id);
        return member;
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public void updateGrade(Member member) {
        member.setGrade(Grade.VIP); // 변경 감지 -> 자동 update
    }

    @Override
    public void clear() {
        em.createQuery("delete from Member").executeUpdate();
    }
}
