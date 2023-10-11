package edu.hw1.task8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task8Test {
    private Task8 task8;

    @BeforeEach
    void setUp() {
        task8 = new Task8();
    }

    @Test
    void areKnightsSafe_WhenKnightsAreSafe_ReturnsTrue() {
        int[][] chessBoard = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        boolean areSafe = task8.areKnightsSafe(chessBoard);

        assertThat(areSafe).isTrue();
    }

    @Test
    void areKnightsSafe_WhenKnightsAreNotSafe_ReturnsFalse() {
        int[][] chessBoard = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        boolean areSafe = task8.areKnightsSafe(chessBoard);

        assertThat(areSafe).isFalse();
    }
}
