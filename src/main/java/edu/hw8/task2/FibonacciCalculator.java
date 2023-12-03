package edu.hw8.task2;

import java.util.ArrayList;
import java.util.List;

public class FibonacciCalculator {

    private static long calculateFibonacciNumber(int number) {
        if (number <= 1) {
            return number;
        } else {
            return calculateFibonacciNumber(number - 1) + calculateFibonacciNumber(number - 2);
        }
    }

    public static Long[] calculateWithThreads(int numberPosition, int threadsAmount) {
        ThreadPool threadPool = FixedThreadPool.createFixedThreadPool(threadsAmount);
        List<Long> numbers = new ArrayList<>();

        threadPool.execute(() -> {
            numbers.add(calculateFibonacciNumber(numberPosition));
        });

        threadPool.start();

        return numbers.toArray(new Long[0]);
    }

    private FibonacciCalculator() {
    }
}
