package edu.hw1.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {

    private Task2 task2;

    @BeforeEach
    void setUp() {
        task2 = new Task2();
    }

    @ParameterizedTest
    @CsvSource({"4666,4", "544,3", "0,1"})
    void digitsCount_WhenCorrectFormatOfNumber_CountsDigits(int number, int expectedDigitsAmount) {
        int digitsAmount = task2.digitsCount(number);

        assertThat(digitsAmount).isEqualTo(expectedDigitsAmount);

    }

    @Test
    void digitsCount_WhenNegativeNumber_CountsDigitsIgnoringMinus() {
        int number = -544;

        int digitsAmount = task2.digitsCount(number);

        assertThat(digitsAmount).isEqualTo(3);

    }

    @Test
    void digitsCount_WhenInputMaxInteger_ReturnsTen() {
        int number = Integer.MAX_VALUE;

        int digitsAmount = task2.digitsCount(number);

        assertThat(digitsAmount).isEqualTo(10);

    }

}
