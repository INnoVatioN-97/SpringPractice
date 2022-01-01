package com.practice.spring.youngil.songjavacodingstudy.mvc.service;


import com.practice.spring.youngil.songjavacodingstudy.mvc.domain.Board;
import com.practice.spring.youngil.songjavacodingstudy.mvc.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 간단한 CRUD 기능을 위한 게시판 리포지토리 인터페이스
 */
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 전체 조회
    public List<Board> getList(){
        return boardRepository.getList();
    }

    // 한건 조회
    public Board getBoard(int boardSeq){
        return boardRepository.getBoard(boardSeq);
    }

    // 게시글 저장
    public void saveBoard(Board board) {
        boardRepository.saveBoard(board);
    }

    // 게시글 수정
    public void updateBoard(Board board) {
        boardRepository.updateBoard(board);
    }

    // 게시글 삭제
    public void deleteBoard(int boardSeq){
        boardRepository.deleteBoard(boardSeq);

    }

}
