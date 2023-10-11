package edu.hw1.task6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {

    private Task6 task6;

    @BeforeEach
    void setUp() {
        task6 = new Task6();
    }

    @ParameterizedTest
    @CsvSource({"6621,5", "6554,4", "1234,3"})
    void countK_WhenCorrectNumber_CountsSteps(int number, int expectedNumber) {
        int stepsAmount = task6.countK(number);

        assertThat(stepsAmount).isEqualTo(expectedNumber);
    }

    @Test
    void countK_WhenLessThanThousand_ReturnsMinusOne() {

        int number = 123;

        int stepsAmount = task6.countK(number);

        assertThat(stepsAmount).isEqualTo(-1);
    }

    @Test
    void countK_WhenMoreThanThousand_ReturnsMinusOne() {

        int number = 12345678;

        int stepsAmount = task6.countK(number);

        assertThat(stepsAmount).isEqualTo(-1);
    }

    @Test
    void countK_WhenSameDigits_ReturnsMinusOne() {

        int number = 4444;

        int stepsAmount = task6.countK(number);

        assertThat(stepsAmount).isEqualTo(-1);
    }

    @Test
    void countK_WhenNumberKaprekar_ReturnsZero() {

        int number = 6174;

        int stepsAmount = task6.countK(number);

        assertThat(stepsAmount).isEqualTo(0);
    }

}
