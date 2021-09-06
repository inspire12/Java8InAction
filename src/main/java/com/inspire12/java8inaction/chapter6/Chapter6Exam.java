package com.inspire12.java8inaction.chapter6;

import com.inspire12.java8inaction.chapter4.Dish;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.inspire12.java8inaction.chapter4.Chapter4StreamExam.menu;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

@Slf4j
public class Chapter6Exam {
    // 예제 6 01
    // 통화별로 트랜잭션을 그룹화한 다음, 통화로 모든 트랜잭션 합계 Map<Currency, Integer> 반환
    // 트랜잭션을 비싼 트랜잭션과 저렴한 트랜잭션 두 그룹으로 분류 Map<Boolean, List<Transaction>> 반환
    @Getter
    public static class Transaction {
        private Currency currency;
    }

    public static void main(String[] args) {
//        List<Transaction> transactions = new ArrayList();
//        Map<Currency, List<Transaction>> transactionsByCurrencies
//                = transactions
//                .stream()
//                .collect(groupingBy(Transaction::getCurrency));

        // reduce로 collector 구현한 예제
//        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5).stream();
//
//        List<Integer> numbers = stream.reduce(new ArrayList<Integer>(),
//                (List<Integer> l , Integer e) -> {
//                    l.add(e);
//                    return l;
//                },
//                (List<Integer> l1, List<Integer> l2) -> {
//                    l1.addAll(l2);
//                    return l1;
//                });
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream()
                .collect(groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));
        log.info(String.valueOf(dishesByCaloricLevel));
//        Map<Dish.Type, List<Dish>> caloricDishesByType =
//                menu.stream().filter(dish -> dish.getCalories() > 500)
//                        .collect(groupingBy(Dish::getType));
//        System.out.println(caloricDishesByType);
        // java 9
        Map<Dish.Type, List<Dish>> caloricDishesByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                filtering(dish -> dish.getCalories() > 500, toList())));
        System.out.println(caloricDishesByType);

        //
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel
                = menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400)
                                return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700)
                                return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })));

        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);

        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType
                                , maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);

        // 분할 함수
        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream().collect(partitioningBy(Dish::isVegetarian));

        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
                menu.stream().collect(
                        partitioningBy(Dish::isVegetarian,
                                collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)),
                                        Optional::get)));


        menu.stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(d -> d.getCalories() > 500)));
        // menu.stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(Dish::getCalories))); compile fail
    }

    public boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.range(2, candidate)
                .noneMatch(i -> candidate % i == 0);
    }

    public enum CaloricLevel {
        DIET, NORMAL, FAT
    }
    // takewhile java9 에 추가된 stream 함수 구현 but eager 형태로 작동
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
