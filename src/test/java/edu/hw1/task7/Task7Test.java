package edu.hw1.task7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class Task7Test {
    @BeforeEach
    void setUp() {
        task7 = new Task7();
    }

    private Task7 task7;

    @Test
    void shiftBitsLeft() {
        int number = 17;
        int shift = 2;

        int shiftedNumber = task7.rotateBitsLeft(number, shift);

        assertThat(shiftedNumber).isEqualTo(6);
    }

    @Test
    void shiftBitsRight() {
        int number = 45;
        int shift = 3;

        int shiftedNumber = task7.rotateBitsRight(number, shift);

        assertThat(shiftedNumber).isEqualTo(45);
    }

    @Test
    void negativeShiftForLeft() {
        int number = 4;
        int shift = -3;

        assertThatThrownBy(() -> task7.rotateBitsLeft(number, shift)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void negativeShiftForRight() {
        int number = 11;
        int shift = -1;

        assertThatThrownBy(() -> task7.rotateBitsRight(number, shift)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void negativeNumberForLeft() {
        int number = -30;
        int shift = 8;

        assertThatThrownBy(() -> task7.rotateBitsLeft(number, shift)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void negativeNumberForRight() {
        int number = -10;
        int shift = 4;

        assertThatThrownBy(() -> task7.rotateBitsRight(number, shift)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void zeroNumber() {
        int number = 0;
        int shift = 4;

        int shiftedNumber = task7.rotateBitsRight(number, shift);

        assertThat(shiftedNumber).isEqualTo(0);
    }

    @Test
    void zeroShift() {
        int number = 103;
        int shift = 0;

        int shiftedNumber = task7.rotateBitsRight(number, shift);

        assertThat(shiftedNumber).isEqualTo(103);
    }

    @Test
    void allBitsOn() {
        int number = 127;
        int shift = 3;

        int shiftedNumber = task7.rotateBitsRight(number, shift);

        assertThat(shiftedNumber).isEqualTo(127);
    }

}
