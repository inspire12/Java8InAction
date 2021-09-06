package com.inspire12.java8inaction.chapter6;

import java.util.List;
import java.util.stream.IntStream;

public class CustomCollector {

    public boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double)candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    // 최적화
    // 1. 소수로만 나누기(제수)
    // 2. primes는 정렬된 리스트이므로 쇼트서킷이 적용된 takewhile을 사용
    public static boolean isPrime(List<Integer> primes,int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return primes.stream()
                .takeWhile(i -> i <= candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }
}
