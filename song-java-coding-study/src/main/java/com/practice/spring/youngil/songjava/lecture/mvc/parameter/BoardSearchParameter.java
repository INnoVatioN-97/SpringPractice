package com.practice.spring.youngil.songjava.lecture.mvc.parameter;

import com.practice.spring.youngil.songjava.lecture.mvc.domain.BoardType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 게시물 검색 파라미터 클래스
 */
@Data
@NoArgsConstructor
public class BoardSearchParameter {
    private String keyword;
    private List<BoardType> boardTypes;

}
