package com.webver.jpabook.jpashop_webver.controller.form.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm extends ItemForm {
    private String author;
    private String isbn;
}
