package com.kaiyujin.sb.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AspectLog {

    @Before("execution(* com.kaiyujin.sb.controller..*.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info(joinPoint.getTarget().getClass() + "." +
                joinPoint.getSignature().getName() + " " +
                Arrays.toString(joinPoint.getArgs())
        );
    }
}
