package edu.hw1.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {

    Task2 task2;

    @BeforeEach
    void setUp() {
        task2 = new Task2();
    }

    @Test
    void countDigitsForNumber() {
        int number = 4666;

        int digitsAmount = task2.digitsCount(number);

        assertThat(digitsAmount).isEqualTo(4);

    }

    @Test
    void negativeNumber() {
        int number = -544;

        int digitsAmount = task2.digitsCount(number);

        assertThat(digitsAmount).isEqualTo(3);

    }

    @Test
    void inputZero() {
        int number = 0;

        int digitsAmount = task2.digitsCount(number);

        assertThat(digitsAmount).isEqualTo(1);

    }

    @Test
    void inputMaxInteger() {
        int number = Integer.MAX_VALUE;

        int digitsAmount = task2.digitsCount(number);

        assertThat(digitsAmount).isEqualTo(10);

    }

}
