package springmvc2.alltest.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springmvc2.alltest.member.domain.Member;
import springmvc2.alltest.member.domain.MemberRepository;

/**
 * date           : 2024-11-06
 * created by     : 임경재 
 * description    :
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String add(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String saveMember(@Valid @ModelAttribute("member") MemberForm memberForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "members/addMemberForm";
        }

        Member member = new Member(
                memberForm.getLoginId()
                , memberForm.getName()
                , memberForm.getPassword()
                , memberForm.getEmail()
                , memberForm.getAccess()
        );
        memberRepository.saveMember(member);
        return "redirect:/";
    }
}
