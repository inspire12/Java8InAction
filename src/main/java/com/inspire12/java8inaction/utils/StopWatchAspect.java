package com.inspire12.java8inaction.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.util.StopWatch;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
public class StopWatchAspect {
    @Around("@annotation(StopWatch)")
    public Object checkPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch watch = new StopWatch();
        watch.start();
        Object result = joinPoint.proceed();
        watch.stop();
        log.info("[performance] {} elapse time: {}", joinPoint.getSignature(), watch.getTotalTimeMillis());
        return result;
    }
}
