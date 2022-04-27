package project.rebook.repository.member;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import project.rebook.domain.member.Grade;
import project.rebook.domain.member.Member;
import project.rebook.repository.member.MemberRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Long save(Member member){
        member.setId(++sequence);
        Long id = member.getId();
        store.put(id, member);
        return id;
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public Member findByLoginId(String loginId) throws Exception {
        return null;
    }

    @Override
    public boolean existLoginId(String loginId) {
        return false;
    }

    @Override
    public boolean existNickname(String nickname) {
        return false;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void updateGrade(Long id, Grade grade) {
        Member findMember = findById(id);
        findMember.setGrade(grade);
        store.put(findMember.getId(), findMember);
    }

    @Override
    public void clear() {
        store.clear();
    }
}
