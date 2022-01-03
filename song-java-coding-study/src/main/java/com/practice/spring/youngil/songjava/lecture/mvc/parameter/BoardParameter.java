package com.practice.spring.youngil.songjava.lecture.mvc.parameter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //비어있는 기본 생성자 생성
public class BoardParameter {
    private int boardSeq;
    private String title;
    private String contents;

    public BoardParameter(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
