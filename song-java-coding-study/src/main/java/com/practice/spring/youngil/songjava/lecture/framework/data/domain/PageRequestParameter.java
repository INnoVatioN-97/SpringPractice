package com.practice.spring.youngil.songjava.lecture.framework.data.domain;

import lombok.Data;

/**
 * 페이지 요청 정보와 파라미터 정보를 가지고 있다.
 * 이는 MyBatis 에서 사용할 수 있다.
 * @param <T>
 */
@Data
public class PageRequestParameter<T> {
    private MySQLPageRequest pageRequest;
    private T parameter;

    public PageRequestParameter(MySQLPageRequest pageRequest, T parameter) {
        this.pageRequest = pageRequest;
        this.parameter = parameter;
    }
}
