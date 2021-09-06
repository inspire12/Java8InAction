package com.inspire12.java8inaction.chapter7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Chapter7Exam {
    public static void main(String[] args) {
        long n = 10;
        System.out.println(forkJoinSum(n));
    }
    public static long parallelSum (long n) {
        return Stream.iterate(1L, i -> i+1)
                .limit(n)
                .parallel()
                .reduce(10L, Long::sum);
    }

    public static long test1(int n){
        return Stream.iterate(1L, i -> i+1)
                .limit(n)
                .sequential()
                .parallel()
                .reduce(0L, (i, j) -> {
                    System.out.println(i + " " + j);
                    return i + j;
                });
    }

    public static long test2(int n){
        Long reduce = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .sequential()
                .reduce(0L, (i, j) -> {
                    System.out.println(i + " " + j);
                    return i + j;
                });
        return reduce;
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
}
