package com.inspire12.java8inaction.chapter3;

public class CapturingLambdaExample {
    public static void main(String[] args) {
        SubCapturingLambdaExample subCapturingLambdaExample = new SubCapturingLambdaExample();
        Runnable runnable = subCapturingLambdaExample.getLambda();
        subCapturingLambdaExample.portNumber = 8443;

        runnable.run(); // 8443
    }

    static class SubCapturingLambdaExample {
        public int portNumber = 8080;

        public Runnable getLambda() {
            return () -> System.out.println(portNumber);
        }
    }
}
