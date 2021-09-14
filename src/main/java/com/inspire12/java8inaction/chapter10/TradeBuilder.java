package com.inspire12.java8inaction.chapter10;

import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@NoArgsConstructor
public class TradeBuilder {
//    private final MethodChainingOrderBuilder builder;
    public Trade trade = new Trade();

    public TradeBuilder quantity(int quantity) {
        trade.setQuantity(quantity);
        return this;
    }

    public TradeBuilder price(double price) {
        trade.setPrice(price);
        return this;
    }

    public TradeBuilder stock(Consumer<StockBuilder> consumer) {
        StockBuilder builder = new StockBuilder();
        consumer.accept(builder);
        return this;
    }
//    public TradeBuilder(MethodChainingOrderBuilder builder, Trade.Type type, int quantity) {
//        this.builder = builder;
//        trade.setType(type);
//        trade.setQuantity(quantity);
//    }
//
//    public StockBuilder stock(String symbol) {
//        return new StockBuilder(builder, trade, symbol);
//    }
}
