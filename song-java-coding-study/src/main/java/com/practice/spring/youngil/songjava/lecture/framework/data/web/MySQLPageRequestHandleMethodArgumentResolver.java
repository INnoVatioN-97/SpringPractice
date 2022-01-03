package com.practice.spring.youngil.songjava.lecture.framework.data.web;

import com.practice.spring.youngil.songjava.lecture.framework.data.domain.MySQLPageRequest;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * MySQL 쿼리 페이징시 limit, offset 값을 자동 계산해
 * MySqlPageRequest 클래스에 담아 컨트롤러에서
 * 받을 수 있게 한다.
 */
public class MySQLPageRequestHandleMethodArgumentResolver implements HandlerMethodArgumentResolver {

    final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String DEFAULT_PARAMETER_PAGE = "page";
    private static final String DEFAULT_PARAMETER_SIZE = "size";
    private static final int DEFAULT_SIZE = 20;



    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return MySQLPageRequest.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        // current page
        int page = NumberUtils.toInt(request.getParameter(DEFAULT_PARAMETER_PAGE), 1);
        // count of list
        int offset = NumberUtils.toInt(request.getParameter(DEFAULT_PARAMETER_SIZE), DEFAULT_SIZE);
        // starting point
        int limit = (offset * page) - offset;
        logger.info("page: {}", page);
        logger.info("limit: {}, offset: {}", limit, offset );
        return new MySQLPageRequest(page, offset, limit, offset);

    }
}
