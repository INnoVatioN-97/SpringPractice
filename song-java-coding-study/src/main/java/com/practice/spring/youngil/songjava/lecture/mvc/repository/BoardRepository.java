package com.practice.spring.youngil.songjava.lecture.mvc.repository;

import com.practice.spring.youngil.songjava.lecture.mvc.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 간단한 CRUD 기능을 위한 게시판 리포지토리 인터페이스.
 * 기본적으로 DB 리포지토리를 만들 때는 무적권 DB에서 사용할
 * 기능들을 명시한 인터페이스를 만들고 이를 여러 리포지토리에서
 * 구현하는 식으로 짜자. 뇌절을 줄일 수 있다.
 */
@Repository
public interface BoardRepository {

    // 전체 조회
    List<Board> getList();

    // 한건 조회
    Board getBoard(int boardSeq);

    // 게시글 저장
    int saveBoard(Board board);

    // 게시글 수정
    void updateBoard(Board board);

    // 게시글 삭제
    void deleteBoard(int boardSeq);

}
