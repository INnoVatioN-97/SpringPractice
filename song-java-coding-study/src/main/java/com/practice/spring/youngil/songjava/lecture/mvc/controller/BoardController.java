package com.practice.spring.youngil.songjava.lecture.mvc.controller;


import com.practice.spring.youngil.songjava.lecture.mvc.domain.Board;
import com.practice.spring.youngil.songjava.lecture.mvc.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Board 컨트롤러 클래스
 * 간단한 CRUD 기능을 위한 게시판 리포지토리 인터페이스
 */
@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 전체 조회
    @GetMapping("")
    public List<Board> getList() {
        return boardService.getList();
    }

    // 한건 조회
    @GetMapping("/{boardSeq}")
    public Board getBoard(@PathVariable int boardSeq) {
        return boardService.getBoard(boardSeq);
    }

    // 게시글 저장
    @GetMapping("/saveBoard")
    public int saveBoard(Board board) {
        return boardService.saveBoard(board);
    }

    // 게시글 수정
    @GetMapping("/updateBoard")
    public void updateBoard(Board board) {
        boardService.updateBoard(board);
    }

    // 게시글 삭제
    @GetMapping("/deleteBoard/{boardSeq}")
    public void deleteBoard(@PathVariable int boardSeq) {
        boardService.deleteBoard(boardSeq);

    }

}
