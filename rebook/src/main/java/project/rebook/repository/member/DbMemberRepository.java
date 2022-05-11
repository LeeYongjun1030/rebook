package project.rebook.repository.member;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import project.rebook.domain.member.Grade;
import project.rebook.domain.member.GradeConst;
import project.rebook.domain.member.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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
        return em.find(Member.class, id);
    }

    @Override
    public Member findByLoginId(String loginId) throws Exception {
        return em.createQuery(
                "select m from Member m" +
                        " where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getSingleResult();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public void adjustNumberOfReviews(Long memberId, int change) {
        Member member = findById(memberId);
        int current = member.getNumberOfReviews();
        member.setNumberOfReviews(current+change);

        // 리뷰 개수에 따른 등급 업데이트
        if (member.getNumberOfReviews() >= GradeConst.NUM_of_REVIEWS_to_VIP) {
            member.setGrade(Grade.VIP);
        } else {
            member.setGrade(Grade.NORMAL);
        }
    }

    @Override
    public Member findByNickname(String nickname) throws Exception {
        return em.createQuery(
                "select m from Member m" +
                        " where m.nickname = :nickname", Member.class)
                .setParameter("nickname", nickname)
                .getSingleResult();
    }

    @Override
    public void clear() {
        em.createQuery("delete from Member").executeUpdate();
    }
}
