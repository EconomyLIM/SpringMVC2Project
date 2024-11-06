package springmvc2.alltest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import springmvc2.alltest.member.domain.Member;
import springmvc2.alltest.session.SessionConst;

/**
 * date           : 2024-11-06
 * created by     : 임경재 
 * description    :
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model
    , @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember){

        if (loginMember == null){
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
