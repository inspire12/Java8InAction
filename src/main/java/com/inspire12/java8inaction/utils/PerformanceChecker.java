package com.inspire12.java8inaction.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
public class PerformanceChecker {

    public static void run(Runnable runnable, String name) {
        StopWatch watch = new StopWatch();
        log.info("[performance] {} start", name);
        watch.start();
        runnable.run();
        watch.stop();
        log.info("[performance] {} elapse time: {}", name, watch.getTotalTimeMillis());
    }
}
