package com.webver.jpabook.jpashop_webver.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j // Lombok 이 제공하는 로거
public class HomeController {

    // root URL 로 진입하면 홈 화면 출력하도록. (인덱스 대신)
    @RequestMapping("/")
    public String home() {
        log.info("home controller: entered home.html");
        return "home";
    }
}
