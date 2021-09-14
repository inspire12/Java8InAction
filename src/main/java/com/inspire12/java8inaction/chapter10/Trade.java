package com.inspire12.java8inaction.chapter10;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Trade {
    public enum Type { BUY, SELL}
    private Type type;
    private Stock stock;
    private int quantity;
    private double price;

    public double getValue() {
        return quantity * price;
    }
}
