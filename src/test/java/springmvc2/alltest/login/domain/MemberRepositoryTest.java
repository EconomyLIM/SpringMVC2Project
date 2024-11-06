package springmvc2.alltest.login.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * date           : 2024-11-06
 * created by     : 임경재 ( bmw4117@entropykorea.com )
 * description    :
 */
class MemberRepositoryTest {

    MemberRepository memberRepository = new MemberRepository();

    @Test
    public void 회원가입을_성공해야_한다(){
        Member member = new Member();
        member.setLoginId("test");
        member.setName("testName");
        member.setPassword("test!");
        member.setAccess("admin");

        memberRepository.saveMember(member);
        Assertions.assertThat(member).isEqualTo(memberRepository.findById(member.getId()));
    }

}