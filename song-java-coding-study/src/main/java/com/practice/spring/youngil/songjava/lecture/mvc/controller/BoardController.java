package com.practice.spring.youngil.songjava.lecture.mvc.controller;


import com.practice.spring.youngil.songjava.lecture.configuration.exception.BaseException;
import com.practice.spring.youngil.songjava.lecture.configuration.http.BaseResponse;
import com.practice.spring.youngil.songjava.lecture.configuration.http.BaseResponseCode;
import com.practice.spring.youngil.songjava.lecture.framework.data.domain.MySQLPageRequest;
import com.practice.spring.youngil.songjava.lecture.framework.data.domain.PageRequestParameter;
import com.practice.spring.youngil.songjava.lecture.framework.web.bind.annotation.RequestConfig;
import com.practice.spring.youngil.songjava.lecture.mvc.domain.Board;
import com.practice.spring.youngil.songjava.lecture.mvc.parameter.BoardParameter;
import com.practice.spring.youngil.songjava.lecture.mvc.parameter.BoardSearchParameter;
import com.practice.spring.youngil.songjava.lecture.mvc.service.BoardService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    Logger logger = LoggerFactory.getLogger(getClass());

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 전체 조회
    @GetMapping("")
    @ApiOperation(value = "목록 조회", notes = "전체 게시물을 조회 가능.")
    public BaseResponse<List<Board>> getList(@ApiParam BoardSearchParameter parameter, @ApiParam MySQLPageRequest pageRequest) {
        logger.info("pageRequest: {}", pageRequest);
        PageRequestParameter<BoardSearchParameter> pageRequestParameter = new PageRequestParameter<>(pageRequest, parameter);

        return new BaseResponse<>(boardService.getList(pageRequestParameter));
    }

    // 한건 조회
    @GetMapping("/{boardSeq}")
    @ApiOperation(value = "상세 조회", notes = "게시물 번호에 해당하는 상세정보를 조회 가능.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시글 번호", example = "1")
    })
    public BaseResponse<Board> getBoard(@PathVariable int boardSeq) {
        Board board = boardService.getBoard(boardSeq);
        if (board == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[]{"게시물"});
        }
        return new BaseResponse<>(boardService.getBoard(boardSeq));
    }

    // 게시글 저장
    @PutMapping
    @RequestConfig(loginCheck = true)
    @ApiOperation(value = "게시글 등록 / 수정 처리", notes = "신규 게시물 저장 및 기존 게시물 업데이트가 가능.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시글 번호", example = "1"),
            @ApiImplicitParam(name = "title", value = "게시글 제목", example = "게시글에 들어갈 제목"),
            @ApiImplicitParam(name = "contents", value = "게시글 내용", example = "게시글에 들어갈 내용"),
    })
    public BaseResponse<Integer> saveBoard(BoardParameter parameter) {
        //제목 필수 제크
        if(ObjectUtils.isEmpty(parameter.getTitle())){
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[]{"title","제목"});
        }
        // 내용 필수 체크
        if(ObjectUtils.isEmpty(parameter.getContents())){
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[]{"contents","내용"});
        }
        boardService.saveBoard(parameter);
        return new BaseResponse<>(parameter.getBoardSeq());
    }

    /**
     * 대용량 등록처리 1
     * 리스트를 foreach 방식으로
     * 하나하나 db connection 을 얻어 넣는 방식
     * @return
     */
    @ApiOperation(value = "대용량 등록처리 1", notes = "대용량 등록 처리 1")
    @PutMapping("/saveList1")
    public BaseResponse<Boolean> saveList1(){
        int count = 0;

        //테스트를 위한 1000건의 랜덤 데이터 생성
        List<BoardParameter> list = new ArrayList<>();
        while(true){
            count++;
            String title = RandomStringUtils.randomAlphabetic(10);
            String contents = RandomStringUtils.randomAlphabetic(10);
            list.add(new BoardParameter(title, contents));
            if(count>=10000) break;
        }
        long start = System.currentTimeMillis();
        boardService.saveList1(list);
        long finish = System.currentTimeMillis();
        logger.info("실행 시간: {}", (finish - start) / 1000.0);
        return new BaseResponse<>(true);
    }

    /**
     * 대용량 등록처리 2
     * list 자체를 때려박아버리는 방식
     * @return
     */
    @ApiOperation(value = "대용량 등록처리 2", notes = "대용량 등록 처리 2")
    @PutMapping("/saveList2")
    public BaseResponse<Boolean> saveList2(){
        int count = 0;

        //테스트를 위한 1000건의 랜덤 데이터 생성
        List<BoardParameter> list = new ArrayList<>();
        while(true){
            count++;
            String title = RandomStringUtils.randomAlphabetic(10);
            String contents = RandomStringUtils.randomAlphabetic(10);
            list.add(new BoardParameter(title, contents));
            if(count>=10000) break;
        }
        long start = System.currentTimeMillis();
        boardService.saveList2(list);
        long finish = System.currentTimeMillis();
        logger.info("실행 시간: {}", (finish - start) / 1000.0);
        return new BaseResponse<>(true);
    }

    // 게시글 삭제
    @DeleteMapping("/{boardSeq}")
    @RequestConfig(loginCheck = true) // loginCheck 이 된 상태여야만 등록 / 삭제가 가능하도록
    @ApiOperation(value = "게시글 삭제 처리", notes = "게시판 번호에 해당하는 게시글을 삭제 가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시글 번호", example = "1"),
    })
    public BaseResponse<Boolean> deleteBoard(@PathVariable int boardSeq) {
        Board board = boardService.getBoard(boardSeq);
        if (board == null) return new BaseResponse<>(false);
        boardService.deleteBoard(boardSeq);
        return new BaseResponse<>(true);
    }

}
