package com.vds.springbootx.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class MyAspect {

    @Around("execution(* *..*.CustomLogger.info(..)) || execution(* *..*.CustomLogger.warn(..))")
    public Object beforeMethodExecutionINFO(ProceedingJoinPoint joinPoint) throws Throwable {
        return false ? joinPoint.proceed() : null;
    }
}
