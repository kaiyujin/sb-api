package com.kaiyujin.sb.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AspectLog {

    @Before("within(com.kaiyujin.sb.controller.*)")
    public void before(JoinPoint joinPoint) {
        log.info(joinPoint.getTarget().getClass() + "." +
                joinPoint.getSignature().getName() + " " +
                Arrays.toString(joinPoint.getArgs())
        );
    }
}
