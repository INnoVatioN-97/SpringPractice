package com.webver.jpabook.jpashop_webver.controller.form.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemForm{
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
}
