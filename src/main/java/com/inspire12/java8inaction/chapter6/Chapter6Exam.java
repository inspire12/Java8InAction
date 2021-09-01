package com.inspire12.java8inaction.chapter6;

import java.util.List;
import java.util.function.Predicate;

public class Chapter6Exam {
    // takewhile java9 에 추가된 stream 함수 구현 but eager 형태로 작동
    //
    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }

    // Collector 클래스 시그니처 정의

}
