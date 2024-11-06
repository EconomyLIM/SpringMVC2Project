package springmvc2.alltest.login.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * date           : 2024-11-06
 * created by     : 임경재 ( bmw4117@entropykorea.com )
 * description    :
 */
@Data
public class LoginForm {

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
}
