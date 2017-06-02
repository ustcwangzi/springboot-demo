package com.wz.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 记录方法名称、入参、耗时、出参、异常信息
 * Created by wangzi on 2017/4/18.
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.wz.service..*.*(..))")
    public void logPointcut() {
    }

    @Around("logPointcut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[CLASS_METHOD] : {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("[ARGS] : {}", Arrays.toString(joinPoint.getArgs()));
        Long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed(joinPoint.getArgs());
        log.info("[RESPONSE] : {}", result);
        log.info("[COST TIME] : {} ms", (System.currentTimeMillis() - startTime ));
        return result;
    }

    @AfterThrowing(throwing = "ex", pointcut = "logPointcut()")
    public void doAfterThrowing(Throwable ex) {
        log.error("[EXCEPTION] : {}", ex);
    }

}
