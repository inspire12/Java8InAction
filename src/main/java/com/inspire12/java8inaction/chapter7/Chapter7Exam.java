package com.inspire12.java8inaction.chapter7;

import java.util.Spliterator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Chapter7Exam {
    public static void main(String[] args) {
//        long n = 10;
//        System.out.println(forkJoinSum(n));

        String n = " asdfasdf aasdfasdf eaw efwefawfsdf as dsafasdf";
//        Stream<Character> stream = IntStream.range(0, n.length())
//                        .mapToObj(n::charAt);
//        int count = countWords(stream);
//        System.out.println("FOUND : " + count + " words" );

        Stream<Character> stream2 = IntStream.range(0, n.length())
                .mapToObj(n::charAt);
        Spliterator<Character> spliterator = new WordCounterSpliterator(n);
        Stream<Character> stream3 = StreamSupport.stream(spliterator, true);
        int count = countWords(stream3.parallel()); // 문자열을 임의의 위치로 나누다보니 하나의 단어가 둘로 쪼개지는 경우가 생길 수 있다.
        System.out.println("FOUND : " + count + " words" );


    }

    public static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
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
