package springmvc2.alltest.member.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * date           : 2024-11-06
 * created by     : 임경재 ( bmw4117@entropykorea.com )
 * description    :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Long id;

    private String loginId;
    private String name;
    private String password;
    private String email;
    private String access;

    public Member(String loginId, String name, String password, String email, String access) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.access = access;
    }
}
