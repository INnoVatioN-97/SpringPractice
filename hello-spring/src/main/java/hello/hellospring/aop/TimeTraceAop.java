package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 이러한 TimeTrace Aop 를 등록하기 위해선 직접 여기에 @Component 를 달던가,
 * SpringConfig 에서 bean 으로 등록해주면 된다.
 * 개인적으로는 @Component 를 다는것보다 config 내에 Bean 으로 등록해 명확하고 직관적으로
 * 확인할 수 있도록 하는게 바람직 하겠다.
 */
@Aspect
@Component
public class TimeTraceAop {
    // 실질적인 타임 스레이스를 어디서 쓸지에 대한 명시를 위해 패키지 주소를 명시
    // 뜻 : hello.hellospring 패키지 내 모든 클래스에게 이 기능을 동작하도록.
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            System.out.format("END : %s finished. __ %dms\n", joinPoint.toString(), finish - start);
        }
    }
}
