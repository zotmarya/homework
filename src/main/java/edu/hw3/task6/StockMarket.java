package edu.hw3.task6;

import java.util.PriorityQueue;

public class StockMarket {
    private PriorityQueue<Stock> stockQueue;

    public StockMarket() {
        stockQueue = new PriorityQueue<>();
    }

    public void add(Stock stock) {
        stockQueue.add(stock);
    }

    public void remove(Stock stock) {
        stockQueue.remove(stock);
    }

    public Stock mostValuableStock() {
        return stockQueue.peek();
    }

}
