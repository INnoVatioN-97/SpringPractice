package com.practice.spring.youngil.songjava.lecture.configuration.servlet.handler;


import com.practice.spring.youngil.songjava.lecture.configuration.exception.BaseException;
import com.practice.spring.youngil.songjava.lecture.configuration.http.BaseResponseCode;
import com.practice.spring.youngil.songjava.lecture.framework.web.bind.annotation.RequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 강의상 HandlerInterceptorAdapter 를 상속받도록 되었으나,
 * Deprecated 되어 직접 인터페이스를 구현하는 방식으로 변경됨
 */
public class BaseHandlerInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle requestURI : {}", request.getRequestURI());
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            logger.info("handlerMethod : {}", handlerMethod);
            RequestConfig requestConfig = handlerMethod.getMethodAnnotation(RequestConfig.class);
            if(requestConfig != null){
                logger.info("requestConfig.loginCheck 체크중! {}", requestConfig.loginCheck());
                // 로그인 체크가 필수적인 경우
                if(requestConfig.loginCheck()){
                    logger.info("requestConfig.loginCheck 발생!");
                    throw new BaseException(BaseResponseCode.LOGIN_REQUIRED);
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle requestURI : {}", request.getRequestURI());
    }
}
