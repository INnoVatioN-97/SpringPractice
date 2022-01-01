package com.practice.spring.youngil.songjavacodingstudy.mvc.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Board {

    private int boardSeq; //PK, 게시판 번호
    private String title;
    private String contents;
    private Date regDate;
}
