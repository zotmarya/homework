package edu.hw1.task6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {

    Task6 task6;

    @BeforeEach
    void setUp() {
        task6 = new Task6();
    }

    @Test
    void correctNumber() {

        int number = 6621;

        int stepsAmount = task6.countK(number);

        assertThat(stepsAmount).isEqualTo(5);
    }

    @Test
    void lessThanThousand() {

        int number = 123;

        int stepsAmount = task6.countK(number);

        assertThat(stepsAmount).isEqualTo(-1);
    }

    @Test
    void moreThanThousand() {

        int number = 12345678;

        int stepsAmount = task6.countK(number);

        assertThat(stepsAmount).isEqualTo(-1);
    }

    @Test
    void sameDigits() {

        int number = 4444;

        int stepsAmount = task6.countK(number);

        assertThat(stepsAmount).isEqualTo(-1);
    }

    @Test
    void numberKaprekar() {

        int number = 6174;

        int stepsAmount = task6.countK(number);

        assertThat(stepsAmount).isEqualTo(0);
    }

}
