package edu.hw1.task2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {

    @Test
    void countDigitsForNumber() {

        Task2 task2 = new Task2();

        int number = 4666;

        int digitsAmount = task2.digitsCount(number);

        assertThat(digitsAmount).isEqualTo(4);

    }

    @Test
    void negativeNumber () {

        Task2 task2 = new Task2();

        int number = -544;

        int digitsAmount = task2.digitsCount(number);

        assertThat(digitsAmount).isEqualTo(3);

    }

    @Test
    void inputZero () {

        Task2 task2 = new Task2();

        int number = 0;

        int digitsAmount = task2.digitsCount(number);

        assertThat(digitsAmount).isEqualTo(1);

    }

    @Test
    void inputMaxInteger () {

        Task2 task2 = new Task2();

        int number = Integer.MAX_VALUE;

        int digitsAmount = task2.digitsCount(number);

        assertThat(digitsAmount).isEqualTo(10);

    }

}
