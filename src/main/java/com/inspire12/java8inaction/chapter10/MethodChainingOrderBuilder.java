package com.inspire12.java8inaction.chapter10;

public class MethodChainingOrderBuilder {
    public final Order order = new Order();

    private MethodChainingOrderBuilder(String customer) {
        order.setCustomer(customer);
    }

    public static MethodChainingOrderBuilder forCustomer(String customer) {
        return new MethodChainingOrderBuilder(customer);
    }

    public Trade1Builder buy(int quantity) {
        return new Trade1Builder(this, Trade.Type.BUY, quantity);
    }

    public Trade1Builder sell(int quantity) {
        return new Trade1Builder(this, Trade.Type.SELL, quantity);
    }

    public MethodChainingOrderBuilder addTrade(Trade trade) {
        order.addTrade(trade);
        return this;
    }

    public Order end() {
        return order;
    }
}
