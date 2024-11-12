package springmvc2.alltest.login.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import springmvc2.alltest.member.domain.Member;
import springmvc2.alltest.member.domain.MemberRepository;

/**
 * date           : 2024-11-06
 * created by     : 임경재
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