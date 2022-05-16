package project.rebook.repository.member;

import org.springframework.stereotype.Repository;
import project.rebook.domain.member.Member;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Long save(Member member){
        //member.setId(++sequence);
        Long id = member.getId();
        store.put(id, member);
        return id;
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public Member findByLoginId(String loginId) throws RuntimeException{
        return null;
    }

    @Override
    public Member findByNickname(String nickname) throws RuntimeException {
        return null;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }


    @Override
    public void clear() {
        store.clear();
    }
}
