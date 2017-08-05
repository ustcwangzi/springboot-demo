package com.wz.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * Created by wangzi on 2017/4/23.
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.wz.service..*.*(..))")
    public void logPointcut(){}

    @Before("logPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("[CLASS_METHOD] : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("[ARGS] : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Around("logPointcut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        System.err.println("[CLASS_METHOD] : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.err.println("[ARGS] : " + Arrays.toString(joinPoint.getArgs()));
        Long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed(joinPoint.getArgs());
        System.err.println("[RESPONSE] : " + result);
        System.err.println("[COST TIME] : " + (System.currentTimeMillis() - startTime ));
        return result;
    }

    @AfterReturning(returning = "ret", pointcut = "logPointcut()")
    public void doAfterReturning(Object ret) {
        System.out.println("[RESPONSE] : " + ret);
    }

    @AfterThrowing(throwing="ex", pointcut="logPointcut()")
    public void doAfterThrowing(Throwable ex) {
        System.out.println("[EXCEPTION] : " + ex);
    }
}
