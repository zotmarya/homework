package edu.hw1;

import edu.hw1.task1.Task1;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {

    @Test
    void timeToSecondsInGivenFormat() {
        Task1 task1 = new Task1();

        String time = "13:56";

        int timeInSeconds = task1.timeToSeconds(time);

        assertThat(timeInSeconds).isEqualTo(836);
    }

    @Test
    void timeToSecondsWithExtraMinutes() {
        Task1 task1 = new Task1();

        String time = "999:59";

        int timeInSeconds = task1.timeToSeconds(time);

        assertThat(timeInSeconds).isEqualTo(59999);
    }

    @Test
    void incorrectFormatOfSeconds() {
        Task1 task1 = new Task1();

        String time = "10:60";

        int result = task1.timeToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void incorrectFormatOfMinutes() {
        Task1 task1 = new Task1();

        String time = "1:10";

        int result = task1.timeToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void incorrectAmountOfColons() {
        Task1 task1 = new Task1();

        String time = "10:60:00";

        int result = task1.timeToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void incorrectInput() {
        Task1 task1 = new Task1();

        String time = "abc";

        int result = task1.timeToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void unwantedSymbol() {
        Task1 task1 = new Task1();

        String time = "-07:01";

        int result = task1.timeToSeconds(time);

        assertThat(result).isEqualTo(-1);
    }
}
