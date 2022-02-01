package com.webver.jpabook.jpashop_webver.controller.form.item;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class MovieForm extends ItemForm {
    private String director;
    private String actor;
}
