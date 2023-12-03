package edu.hw8.task2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class FibonacciCalculatorTest {
    @ParameterizedTest
    @CsvSource({"0,5,0", "5,3,5", "2,2,1", "9,10,34"})
    void calculateWithThreads_WhenGivenNumberPositionAndThreadsAmount_ReturnsFibonacciNumber(
        int numberPosition,
        int threadsAmount,
        long expectedNumber
    ) {
        Long[] numbers = FibonacciCalculator.calculateWithThreads(numberPosition, threadsAmount);

        assertThat(numbers).allMatch((number) -> number == expectedNumber).hasSize(threadsAmount);
    }
}
