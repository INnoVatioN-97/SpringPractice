package com.practice.spring.youngil.songjava.lecture.configuration.http;

import lombok.Data;

/**
 * 공통으로 사용할 기본 응답의 형태를 잡아주는 클래스.
 * 응답의 규격을 통일화 하여 프론트엔드에서
 * api 호출 및 메시지를 확인하는데 혼선을
 * 방지하고자 하는 목적이 있다.
 */
@Data
public class BaseResponse<T> {

    private BaseResponseCode code;
    private String message;
    private T data;

    public BaseResponse(T data){
        this.code = BaseResponseCode.SUCCESS;
        this.data = data;
    }

    public BaseResponse(BaseResponseCode code, String message) {
        this.code = code;
        this.message = message;
    }
}
