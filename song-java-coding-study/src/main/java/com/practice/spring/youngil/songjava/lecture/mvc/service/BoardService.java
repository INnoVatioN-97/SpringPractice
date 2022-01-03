package com.practice.spring.youngil.songjava.lecture.mvc.service;


import com.practice.spring.youngil.songjava.lecture.framework.data.domain.PageRequestParameter;
import com.practice.spring.youngil.songjava.lecture.mvc.domain.Board;
import com.practice.spring.youngil.songjava.lecture.mvc.parameter.BoardParameter;
import com.practice.spring.youngil.songjava.lecture.mvc.parameter.BoardSearchParameter;
import com.practice.spring.youngil.songjava.lecture.mvc.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Board 서비스 클래스
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
    public List<Board> getList(PageRequestParameter<BoardSearchParameter> pageRequestParameter) {
        return boardRepository.getList(pageRequestParameter);
    }

    // 한건 조회
    public Board getBoard(int boardSeq) {
        return boardRepository.getBoard(boardSeq);
    }

    // 게시글 저장
    public void saveBoard(BoardParameter parameter) {
        Board board = boardRepository.getBoard(parameter.getBoardSeq());
        if(board == null) {
            boardRepository.saveBoard(parameter);
        }else boardRepository.updateBoard(parameter);
    }

    /**
     * 대용량 데이터 저장 (foreach 방식으로 하나하나 insert)
     * @param list
     */
    public void saveList1(List<BoardParameter> list) {
        for(BoardParameter parameter : list){
            boardRepository.saveBoard(parameter);
        }
    }

    /**
     * 대용량 데이터 저장 ( myBatis 에서 map 객체 자체를 한번에 저장시킴.)
     * @param boardList
     */
    public void saveList2(List<BoardParameter> boardList) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("boardList", boardList);
        boardRepository.saveList(paramMap);
    }

//    // 게시글 수정
//    public void updateBoard(Board board) {
//        boardRepository.updateBoard(board);
//    }

    // 게시글 삭제
    public void deleteBoard(int boardSeq) {
        boardRepository.deleteBoard(boardSeq);

    }

}
