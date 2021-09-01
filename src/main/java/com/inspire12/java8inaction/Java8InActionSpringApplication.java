package com.inspire12.java8inaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Java8InActionSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(Java8InActionSpringApplication.class, args);
    }
}
