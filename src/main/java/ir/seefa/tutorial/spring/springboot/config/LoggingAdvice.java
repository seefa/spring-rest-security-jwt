package ir.seefa.tutorial.spring.springboot.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 07 Sep 2020 T 17:29:29
 */
// 1. define Logging advice
@Component
// 2. define bean as Aspect
@Aspect
public class LoggingAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 3. define pointcut expression for Logging Around start and finish a method with @Around annotation
    @Around("execution(* ir.seefa.tutorial.spring.springboot.controller.*.*Contact*(..))")
    public Object logAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch watch = new StopWatch();
        watch.start();
        Object proceed = joinPoint.proceed();
        watch.stop();
        logger.info("**** @Around call took:" + watch.getTotalTimeMillis() + " ms");
        return proceed;
    }

    // 4. define pointcut expression for Logging Before a method with @Before annotation
    @Before("execution(* ir.seefa.tutorial.spring.springboot.controller.*.*Contact*(..))")
    public void logBeforeMethodAdvice(JoinPoint joinPoint) {
        logger.info("**** LoggingAspect.logBeforeMethodAdvice() : " + joinPoint.getSignature().getName());

    }

    // 5. define pointcut expression for Logging After a method with @After annotation
    @After("execution(* ir.seefa.tutorial.spring.springboot.controller.*.*Contact*(..))")
    public void logAfterMethodAdvice(JoinPoint joinPoint) {
        logger.info("**** LoggingAspect.logAfterMethodAdvice() : " + joinPoint.getSignature().getName());
    }

    // 6. define pointcut expression for Logging After Throwing an exception with @AfterThrowing annotation
    @AfterThrowing(value = "execution(* ir.seefa.tutorial.spring.springboot.controller.*.*Contact*(..))", throwing = "ex")
    public void logAfterThrowingAdvice(Exception ex) throws Throwable {
        logger.info("**** @AfterThrowing exception raised: " + ex.toString());
        throw ex;
    }
}
