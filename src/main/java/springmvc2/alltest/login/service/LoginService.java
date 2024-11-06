package springmvc2.alltest.login.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import springmvc2.alltest.login.domain.Member;
import springmvc2.alltest.login.domain.MemberRepository;

/**
 * date           : 2024-11-06
 * created by     : 임경재 ( bmw4117@entropykorea.com )
 * description    :
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password){
        return memberRepository.findByLoginId(loginId)
                .stream().filter(m -> m.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
