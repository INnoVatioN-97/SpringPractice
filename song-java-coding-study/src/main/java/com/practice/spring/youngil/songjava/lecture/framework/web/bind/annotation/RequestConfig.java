package com.practice.spring.youngil.songjava.lecture.framework.web.bind.annotation;


import java.lang.annotation.*;

/**
 * 로그인 여부를 체크하는 인터페이스.
 * BoardController 에서 save, delete 에서만 사용할 에정.
 * -> 로그인 체크가 필요할만한 상황에서만.
 * @Target : 해당 어노테이션을 다른 클래스에서 사용할 때 클래스/메소드/전역변수 등의 사용을 할 수 있는 위치를 지정. 여기서는 이 인터페이스의 메소드를 쓸 수 있도록
 * @Retension : 어느 시점까지 메모리에 사용/유지할지를 정한다. 여기서는 런타임(컴파일될때)까지만.
 * @Document : 이 Annotation 이 지정된 대상의 Javadoc 존재를 표기하도록 지정.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestConfig {
    boolean loginCheck() default false;

}
