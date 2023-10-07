package edu.hw1.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {
    Task1 task1;

    @BeforeEach
    void setUp() {
        task1 = new Task1();
    }

    @Test
    void timeToSecondsInGivenFormat() {
        String time = "13:56";

        int timeInSeconds = task1.timeToSeconds(time);

        assertThat(timeInSeconds).isEqualTo(836);
    }

    @Test
    void timeToSecondsWithExtraMinutes() {
        String time = "999:59";

        int timeInSeconds = task1.timeToSeconds(time);

        assertThat(timeInSeconds).isEqualTo(59999);
    }

    @Test
    void incorrectFormatOfSeconds() {
        String time = "10:60";

        int result = task1.timeToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void incorrectFormatOfMinutes() {
        String time = "1:10";

        int result = task1.timeToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void incorrectAmountOfColons() {
        String time = "10:60:00";

        int result = task1.timeToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void incorrectInput() {
        String time = "abc";

        int result = task1.timeToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void unwantedSymbol() {
        String time = "-07:01";

        int result = task1.timeToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }
}
