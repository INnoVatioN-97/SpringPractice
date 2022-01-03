package com.practice.spring.youngil.songjava.lecture.mvc.domain;

import lombok.Data;

import java.util.Date;

/**
 * Board Bean 클래스
 */
@Data
public class Board {

    private int boardSeq; //PK, 게시판 번호
    private BoardType boardType;
    private String title;
    private String contents;
    private Date regDate;
}
