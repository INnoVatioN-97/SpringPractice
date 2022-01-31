package com.webver.jpabook.jpashop_webver.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Slf4j
public class MemberForm {

    /**
     * 이름 항목이 비어서 들어오지않게 Annotation 으로 잡아준다.
     * 만약 비면 에러를 내던 한다.
     */
    @NotEmpty(message = "회원 이름은 필수 입력사항 입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;

}
