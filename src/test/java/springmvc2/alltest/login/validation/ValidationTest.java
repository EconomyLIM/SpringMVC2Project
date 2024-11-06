package springmvc2.alltest.login.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import springmvc2.alltest.member.controller.MemberForm;

import java.util.Set;

/**
 * date           : 2024-11-06
 * created by     : 임경재 ( bmw4117@entropykorea.com )
 * description    :
 */
public class ValidationTest {

    @Test
    void 회원가입폼_검증_테스트(){
        // when
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        // then
        MemberForm memberForm = new MemberForm();
        Set<ConstraintViolation<MemberForm>> validate = validator.validate(memberForm);
        for (ConstraintViolation<MemberForm> violation : validate) {
            System.out.println("violation = " + violation);
            System.out.println("violation.getMessage() = " + violation.getMessage());
        }

        // given

    }
}
