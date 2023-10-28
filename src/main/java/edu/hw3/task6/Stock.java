package edu.hw3.task6;

import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {
    private double price;

    @Override
    public int compareTo(@NotNull Stock stock) {
        int difference;

        if (price == stock.price) {
            difference = 0;
        } else if (price - stock.price > 0) {
            difference = -1;
        } else {
            difference = 1;
        }

        return difference;
    }

    public Stock(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    public double getPrice() {
        return price;
    }
}
