package com.practice.spring.youngil.songjava.lecture.configuration.http;

/**
 * 기본 응답코드를 관리하는 enumeration 객체.
 *
 */
public enum BaseResponseCode {
    SUCCESS,            // 성공
    ERROR,              // 에러
    LOGIN_REQUIRED,     // 로그인 필요한 상황시 던져질 응답
    DATA_IS_NULL,       // null
    VALIDATE_REQUIRED,  // 필수 체크
}
