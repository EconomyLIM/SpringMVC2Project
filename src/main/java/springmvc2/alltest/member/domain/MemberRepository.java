package springmvc2.alltest.member.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * date           : 2024-11-06
 * created by     : 임경재 ( bmw4117@entropykorea.com )
 * description    :
 */
@Slf4j
@Repository
public class MemberRepository {
    private final Map<Long, Member> members = new HashMap<>();
    private static Long sequence = 0L;

    public Member saveMember(Member member) {
        member.setId(++sequence);
        log.info("save member: {}", member);
        members.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return members.get(id);
    }

    public Optional<Member> findByLoginId(String loginId){
        return new ArrayList<>(members.values()).stream().filter(m -> m.getLoginId().equals(loginId)).findFirst();
    }

    public void clearStore(){
        members.clear();
    }
}
