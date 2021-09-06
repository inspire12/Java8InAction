package com.inspire12.java8inaction.chapter3;

import com.inspire12.java8inaction.utils.PerformanceChecker;
import lombok.ToString;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Chapter3Performance {
    private static final int TEST_MAX_RANGE = 30_000_000;

//    public static void main(String[] args) {
//        PerformanceChecker.run(() -> {
//            IntStream.rangeClosed(1, TEST_MAX_RANGE).sum();
//        }, "int-stream");
//    }

    public static Runnable createLambda() {
        int portNumber = 8080;
        return () -> System.out.println(portNumber);
    }
    public static void main(String[] args) {
        List<Niniz> ninizs = init();
        List<Niniz> result = ninizs.stream()
                .filter(niniz -> niniz.getColor().equals("초록색")) .collect(Collectors.toList());
        System.out.println(result);
    }
    private static List<Niniz> init() {
        return Arrays.asList(new Niniz("죠르디", "초록색", "파충류")
                , new Niniz("스카피", "분홍색", "포유류"), new Niniz("앙몬드", "흰색", "포유류"));
    }
    @ToString
    public static class Niniz {
        private String name;
        private String color;
        private String classification;
        public Niniz(String name, String color, String classification) { this.name = name;
            this.color = color;
            this.classification = classification;
        }
        public String getName() {
            return name;
        }
        public String getColor() {
            return color;
        }
        public String getClassification() {
            return classification;
        }
    }
}
