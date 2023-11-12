package edu.hw5.task2;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FridayFinderTest {
    @ParameterizedTest
    @CsvSource(value = {"1925;[1925-02-13, 1925-03-13, 1925-11-13]", "2024;[2024-09-13, 2024-12-13]"}, delimiter = ';')
    void findFridays13thByYear_WhenGivenYear_ReturnsListOfFridays13(int year, String fridays13) {
        FridayFinder fridayFinder = new FridayFinder();

        List<LocalDate> resultFridays = fridayFinder.findFridays13thByYear(year);

        assertThat(resultFridays.toString()).isEqualTo(fridays13);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -20, 1000000})
    void findFridays13thByYear_WhenGivenInvalidYear_ReturnsNull(int year) {
        FridayFinder fridayFinder = new FridayFinder();

        List<LocalDate> resultFridays = fridayFinder.findFridays13thByYear(year);

        assertThat(resultFridays).isNull();
    }

    private static Stream<Arguments> datesAndNextFriday13() {
        return Stream.of(
            arguments(
                LocalDate.of(1925, 3, 13),
                LocalDate.of(1925, 11, 13)
            ),
            arguments(
                LocalDate.of(2024, 2, 2),
                LocalDate.of(2024, 9, 13)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("datesAndNextFriday13")
    void findFriday13thByDate_WhenGivenDate_ReturnsNextFriday13(LocalDate currentDate, LocalDate expectedDate) {
        FridayFinder fridayFinder = new FridayFinder();

        LocalDate resultDate = fridayFinder.findFriday13thByDate(currentDate);

        assertThat(resultDate).isEqualTo(expectedDate);
    }

    @Test
    void findFriday13thByDate_WhenGivenNull_ReturnsNull() {
        FridayFinder fridayFinder = new FridayFinder();

        LocalDate resultDate = fridayFinder.findFriday13thByDate(null);

        assertThat(resultDate).isNull();
    }
}
