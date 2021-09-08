package com.inspire12.java8inaction.chapter6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class RankCollector<T> implements Collector<Rank<T>, List<Rank<T>>, List<Rank<T>>> {

    private static final Set<Characteristics> CHARACTERISTICS = Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    private final Comparator<? super T> comparator;

    public RankCollector(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public Supplier<List<Rank<T>>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Rank<T>>, Rank<T>> accumulator() {
        return (List<Rank<T>> list, Rank<T> current) -> {
            if (list.isEmpty()) {
                current.setRank(1);
                list.add(current);
            } else {
                Rank<T> lastElement = list.get(list.size() - 1);
                if (comparator.compare(lastElement.getContent(), current.getContent()) == 0) {
                    current.setRank(lastElement.getRank());
                } else if (comparator.compare(lastElement.getContent(), current.getContent()) < 0) {
                    current.setRank(list.size() + 1);
                } else {
                    throw new RuntimeException(); // 정렬이 되어있어야함
                }
                list.add(current);
            }
        };
    }

    @Override
    public BinaryOperator<List<Rank<T>>> combiner() {
        return (List<Rank<T>> list1, List<Rank<T>> list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<Rank<T>>, List<Rank<T>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return CHARACTERISTICS;
    }
}
