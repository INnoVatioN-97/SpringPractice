package com.practice.spring.youngil.songjava.lecture.mvc.parameter;

import com.practice.spring.youngil.songjava.lecture.mvc.domain.BoardType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BoardParameter {
    private int boardSeq;
    private BoardType boardType;
    private String title;
    private String contents;

    public BoardParameter() {

    }

    public BoardParameter(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

}
