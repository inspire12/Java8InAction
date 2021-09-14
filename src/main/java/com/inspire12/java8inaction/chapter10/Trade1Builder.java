package com.inspire12.java8inaction.chapter10;

public class Trade1Builder {
    private final MethodChainingOrderBuilder builder;
    private final Trade trade = new Trade();

    public Trade1Builder(MethodChainingOrderBuilder builder, Trade.Type type, int quantity) {
        this.builder = builder;
        trade.setType(type);
        trade.setQuantity(quantity);
    }
}
