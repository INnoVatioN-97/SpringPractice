package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // cmd+p 를 누르면 툴팁 표시됨. 이 줄에 뭘 더 넣을 수 있는지
    // 아래는 템플릿 방식식    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    // ResponseBody: HTTP 의 body 부분에 직접 저 데이터를 넣어주겠다. = API 방식
    // 이경우는 위 처럼 hello~~ .html 이런애들을 찾아서 렌더링하지 않고 하단의 return 되는 문자열 자체를 그냥 뿌려버린다.
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "<h1>hello " + name + "</h1>";
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //toString 으로 JSON의 형태가 반환됨.
        // img_2 참고.
    }

     static class Hello{
        private String name;

         public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }
     }
}
