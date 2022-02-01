package com.webver.jpabook.jpashop_webver.controller;

import com.webver.jpabook.jpashop_webver.domain.Address;
import com.webver.jpabook.jpashop_webver.domain.Member;
import com.webver.jpabook.jpashop_webver.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * Model model :
     *
     * @param model
     * @return
     */
    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());

        return "members/createMemberForm";
    }

    /**
     * 회원가입 form 액션
     * @Valid : 유효성 검증(MemberForm 에서의 @NotNull 과 같은 Annotation 을 검증.
     * 매개변수 BindingResult result : 에러발생시 페이지가 튕기지 않고, result에 담겨서 그대로 온다.
     * @param memberForm
     * @param result
     * @return
     */
    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result) {

        // 만약 이름을 기입하지 않았을 때 처럼 오류 발생시 에러 핸들링
        if (result.hasErrors()) {
            /**
             * GetMapping 이므로 form 페이지 다시 호출.
             * createMemberForm.html 속 내용을 보면, 만약 fields.hasErrors('name') 으로
             * MemberForm 속 에러 문구 "회원 이름은 필수입니다"
             * 라는 내용을 같이 렌더링한다. 개간지.
             */
            return "members/createMemberForm";
        }

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);
        Long joinId = memberService.join(member);

        // 새로 로딩되지않게 그냥 리다이렉트로 넘겨버린다.
        return "redirect:/";
    }
}
