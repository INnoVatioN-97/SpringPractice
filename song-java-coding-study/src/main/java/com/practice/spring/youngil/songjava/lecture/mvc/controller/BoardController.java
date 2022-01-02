package com.practice.spring.youngil.songjava.lecture.mvc.controller;


import com.practice.spring.youngil.songjava.lecture.mvc.domain.Board;
import com.practice.spring.youngil.songjava.lecture.mvc.parameter.BoardParameter;
import com.practice.spring.youngil.songjava.lecture.mvc.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Board 컨트롤러 클래스
 * 간단한 CRUD 기능을 위한 게시판 리포지토리 인터페이스
 */
@RestController
@RequestMapping("/board")
@Api(tags = "게시판 API")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 전체 조회
    @GetMapping("")
    @ApiOperation(value = "목록 조회", notes = "전체 게시물을 조회 가능.")
    public List<Board> getList() {
        return boardService.getList();
    }

    // 한건 조회
    @GetMapping("/{boardSeq}")
    @ApiOperation(value = "상세 조회", notes = "게시물 번호에 해당하는 상세정보를 조회 가능.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시글 번호", example = "1")
    })
    public Board getBoard(@PathVariable int boardSeq) {
        return boardService.getBoard(boardSeq);
    }

    // 게시글 저장
    @PutMapping
    @ApiOperation(value = "게시글 등록 / 수정 처리", notes = "신규 게시물 저장 및 기존 게시물 업데이트가 가능.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시글 번호", example = "1"),
            @ApiImplicitParam(name = "title", value = "게시글 제목", example = "게시글에 들어갈 제목"),
            @ApiImplicitParam(name = "contents", value = "게시글 내용", example = "게시글에 들어갈 내용"),
    })
    public int saveBoard(BoardParameter parameter) {
        boardService.saveBoard(parameter);
        return parameter.getBoardSeq();
    }

//    // 게시글 수정
//    @GetMapping("/updateBoard")
//    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정.")
//    public void updateBoard(Board board) {
//        boardService.updateBoard(board);
//    }

    // 게시글 삭제
    @DeleteMapping("/{boardSeq}")
    @ApiOperation(value = "게시글 삭제 처리", notes = "게시판 번호에 해당하는 게시글을 삭제 가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시글 번호", example = "1"),
    })
    public void deleteBoard(@PathVariable int boardSeq) {
        boardService.deleteBoard(boardSeq);

    }

}
