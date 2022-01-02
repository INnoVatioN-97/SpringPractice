package com.practice.spring.youngil.songjava.lecture.mvc.parameter;

import lombok.Data;

@Data
public class BoardParameter {
    private int boardSeq;
    private String title, contents;
}
