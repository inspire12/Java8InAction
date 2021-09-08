package com.inspire12.java8inaction.chapter6;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Rank<T> {
    @Setter
    int rank;
    T content;
}
