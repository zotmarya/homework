package edu.hw3.task6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StockMarketTest {
    private StockMarket stockMarket;

    @BeforeEach
    void setUp() {
        stockMarket = new StockMarket();

        stockMarket.add(new Stock(100));
        stockMarket.add(new Stock(200));
        stockMarket.add(new Stock(50));
        stockMarket.add(new Stock(400));
    }

    @Test
    void mostValuableStock_WhenCalling_ReturnsStockWithMaxPrice() {
        Stock stock = stockMarket.mostValuableStock();

        assertThat(stock.getPrice()).isEqualTo(400);
    }

}
