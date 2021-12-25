package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Spring 은 반드시 컨트롤러에 어노테이션 명시를 해줘야 한다.
@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
        // url 에 hello 라는 글귀가 보이면 이 메소드랑 매핑해서
        // data 라는 속성에 "Hello!!"라는 글자를 넣어놓고 그 상태로
        // return => hello라는 이름의 페이지를 찾아 렌더링하며
        // 속성을 전달
    }

}
