package com.practice.spring.youngil.songjava.lecture.configuration.http;

/**
 * 기본 응답코드를 관리하는 enumeration 객체.
 *
 */
public enum BaseResponseCode {
    SUCCESS(200),
    ERROR(500),
    ;

    private int status;

    BaseResponseCode(int status){
        this.status = status;
    }

    public int status(){
        return status;
    }
}
