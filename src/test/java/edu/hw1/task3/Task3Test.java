package edu.hw1.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    private Task3 task3;

    @BeforeEach
    void setUp() {
        task3 = new Task3();
    }

    @Test
    void isNestable_WhenSortedArrays_ReturnsTrue() {
        int[] array1 = {1, 2, 3, 4};
        int[] array2 = {0, 6};

        boolean canNest = task3.isNestable(array1, array2);

        assertThat(canNest).isTrue();
    }

    @Test
    void isNestable_WhenNotSortedArrays_ReturnsTrue() {
        int[] array1 = {3, 1};
        int[] array2 = {4, 0};

        boolean canNest = task3.isNestable(array1, array2);

        assertThat(canNest).isTrue();
    }

    @Test
    void isNestable_WhenEqualValuesArrays_ReturnsFalse() {
        int[] array1 = {9, 9, 8};
        int[] array2 = {8, 9};

        boolean canNest = task3.isNestable(array1, array2);

        assertThat(canNest).isFalse();
    }

    @Test
    void isNestable_WhenArraysDoNotMeetCriteria_ReturnsFalse() {
        int[] array1 = {1, 2, 3, 4};
        int[] array2 = {2, 3};

        boolean canNest = task3.isNestable(array1, array2);

        assertThat(canNest).isFalse();
    }

    @Test
    void isNestable_WhenEmptyFirstArray_ReturnsTrue() {
        int[] array1 = {};
        int[] array2 = {1, 3, 6, 7};

        boolean canNest = task3.isNestable(array1, array2);

        assertThat(canNest).isTrue();
    }

    @Test
    void isNestable_WhenSecondArrayEmpty_ReturnsFalse() {
        int[] array1 = {1, 2};
        int[] array2 = {};

        boolean canNest = task3.isNestable(array1, array2);

        assertThat(canNest).isFalse();
    }

    @Test
    void isNestable_WhenBothArraysEmpty_ReturnsFalse() {
        int[] array1 = {};
        int[] array2 = {};

        boolean canNest = task3.isNestable(array1, array2);

        assertThat(canNest).isFalse();
    }

}
