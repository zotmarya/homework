package edu.hw1.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {
    private Task1 task1;

    @BeforeEach
    void setUp() {
        task1 = new Task1();
    }

    @ParameterizedTest
    @CsvSource({"13:56,836", "01:00,60"})
    void timeToSeconds_WhenTimeIsInCorrectFormat_ConvertsToSeconds(String time, int expectedSeconds) {
        int timeInSeconds = task1.timeToSeconds(time);

        assertThat(timeInSeconds).isEqualTo(expectedSeconds);
    }

    @Test
    void timeToSeconds_WhenTimeWithExtraMinutes_ConvertsToSeconds() {
        String time = "999:59";

        int timeInSeconds = task1.timeToSeconds(time);

        assertThat(timeInSeconds).isEqualTo(59999);
    }

    @Test
    void timeToSeconds_WhenIncorrectFormatOfSeconds_CannotBeConverted() {
        String time = "10:60";

        int result = task1.timeToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void timeToSeconds_WhenIncorrectFormatOfMinutes_CannotBeConverted() {
        String time = "1:10";

        int result = task1.timeToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void timeToSeconds_WhenIncorrectAmountOfColons_CannotBeConverted() {
        String time = "10:60:00";

        int result = task1.timeToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void timeToSeconds_WhenIncorrectInput_CannotBeConverted() {
        String time = "abc";

        int result = task1.timeToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void timeToSeconds_WhenUnwantedSymbol_CannotBeConverted() {
        String time = "-07:01";

        int result = task1.timeToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }
}
