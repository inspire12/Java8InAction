package com.inspire12.java8inaction.chapter4;

import com.inspire12.java8inaction.utils.PerformanceChecker;
import com.inspire12.java8inaction.utils.StopWatch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Chapter4StreamExam {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("rice", true, 350, Dish.Type.OTHER)
        );
        PerformanceChecker.run(() -> testStream(menu), "stream performance check");
    }

    private static void testStream(List<Dish> menu) {
        List<String> threeHighCaloricDishNames =
                menu.stream()
                        .filter(dish -> dish.getCalories() > 300)
                        .map(Dish::getName)
                        .limit(3)
                        .collect(Collectors.toList());
        System.out.println(threeHighCaloricDishNames);
    }
}
