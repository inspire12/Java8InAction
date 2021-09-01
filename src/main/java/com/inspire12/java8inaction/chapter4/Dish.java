package com.inspire12.java8inaction.chapter4;
import lombok.*;

@Getter
@AllArgsConstructor
public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public enum Type {
        MEAT, OTHER, FISH;
    }
}
