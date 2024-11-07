package springmvc2.alltest;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springmvc2.alltest.item.domain.Item;
import springmvc2.alltest.item.domain.ItemRepository;
import springmvc2.alltest.member.domain.Member;
import springmvc2.alltest.member.domain.MemberRepository;

import java.io.IOException;

/**
 * date           : 2024-11-07
 * created by     : 임경재
 * description    :
 */
@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() throws IOException {
        itemRepository.saveItem(new Item("itemA", 10000, 10));
        itemRepository.saveItem(new Item("itemB", 20000, 20));

        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");
        member.setEmail("test@naver.com");
        member.setAccess("admin");

        Member member2 = new Member();
        member2.setLoginId("test2");
        member2.setPassword("test2!");
        member2.setName("테스터2");
        member2.setEmail("test2@naver.com");
        member2.setAccess("normal");

        memberRepository.saveMember(member);
        memberRepository.saveMember(member2);
    }

}
