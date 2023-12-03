package edu.hw8.task2;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FibonacciCalculator {

    private static final Logger LOGGER = LogManager.getLogger();

    private static long calculateFibonacciNumber(int number) {
        if (number <= 1) {
            return number;
        } else {
            return calculateFibonacciNumber(number - 1) + calculateFibonacciNumber(number - 2);
        }
    }

    public static Long[] calculateWithThreads(int numberPosition, int threadsAmount) {
        List<Long> numbers = new ArrayList<>();

        try (ThreadPool threadPool = FixedThreadPool.createFixedThreadPool(threadsAmount)) {
            threadPool.execute(() -> {
                numbers.add(calculateFibonacciNumber(numberPosition));
            });

            threadPool.start();
        } catch (Exception exception) {
            LOGGER.info(exception);
        }

        return numbers.toArray(new Long[0]);
    }

    private FibonacciCalculator() {
    }
}
