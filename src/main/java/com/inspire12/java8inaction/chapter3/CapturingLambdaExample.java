package com.inspire12.java8inaction.chapter3;

import java.util.Arrays;
import java.util.List;

public class CapturingLambdaExample {
    public static void main(String[] args) {
//        SubCapturingLambdaExample subCapturingLambdaExample = new SubCapturingLambdaExample();
//        Runnable runnable = subCapturingLambdaExample.getLambda();
//        subCapturingLambdaExample.portNumber = 8443;
//
//        runnable.run(); // 8443
        List<Integer> list = Arrays.asList(1,2,3,5,4);
        list.sort((o1, o2) -> {
            return o1 - o2;
        });
        System.out.println(list);
    }

    static class SubCapturingLambdaExample {
        public int portNumber = 8080;

        public Runnable getLambda() {
            return () -> System.out.println(portNumber);
        }
    }
}
