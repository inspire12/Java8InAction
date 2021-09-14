package com.inspire12.java8inaction.chapter10;

import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.inspire12.java8inaction.chapter10.LambdaOrderBuilder.order;
import static com.inspire12.java8inaction.chapter10.MixedBuilder.buy;


public class Chapter10Exam {
    public static void main(String[] args) throws IOException {
        // thenComparing
        List<Person> persons = Arrays.asList(new Person(10, "서영학"), new Person(11, "서은지"));
        persons.sort(Comparator.comparing(Person::getAge)
                .thenComparing(Person::getName));
        String fileName = "";
        List<String> errors = Files.lines(Paths.get(fileName))
                .filter(line -> line.startsWith("ERROR"))
                .limit(40)
                .collect(Collectors.toList());

        Order order = order(o -> {
            o.forCustomer("BigBank");
            o.buy(t -> {
                t.quantity(80);
                t.price(125.00);
                t.stock(s -> {
                    s.symbol("IBM");
                    s.market("NYSE");
                });
            });
            o.sell(t -> {
                t.quantity(50);
                t.price(375.00);
                t.stock( s -> {
                    s.symbol("GOOGLE");
                    s.market("NASDAQ");
                });
            });
        });

        Order order2 =
                MixedBuilder.forCustomer("BigBank", buy(t -> t.quantity(80)));
    }
}
