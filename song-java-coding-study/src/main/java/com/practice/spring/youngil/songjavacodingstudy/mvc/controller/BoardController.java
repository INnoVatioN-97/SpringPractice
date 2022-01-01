package com.practice.spring.youngil.songjavacodingstudy.mvc.controller;


import com.practice.spring.youngil.songjavacodingstudy.mvc.domain.Board;
import com.practice.spring.youngil.songjavacodingstudy.mvc.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 간단한 CRUD 기능을 위한 게시판 리포지토리 인터페이스
 */
@RestController
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 전체 조회
    @GetMapping()
    public List<Board> getList() {
        return boardService.getList();
    }

    // 한건 조회
    @GetMapping("/board/{boardSeq}")
    public Board getBoard(@PathVariable int boardSeq) {
        return boardService.getBoard(boardSeq);
    }

    // 게시글 저장
    @GetMapping("/board/save")
    public void saveBoard(Board board) {
        boardService.saveBoard(board);
    }

    // 게시글 수정
    @GetMapping("/board/update")
    public void updateBoard(Board board) {
        boardService.updateBoard(board);
    }

    // 게시글 삭제
    @GetMapping("/board/delete/{boardSeq}")
    public void deleteBoard(@PathVariable int boardSeq) {
        boardService.deleteBoard(boardSeq);

    }

}
