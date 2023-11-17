package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DateParserTest {

        /*
            2020-10-10
            2020-12-2
            1/3/1976
            1/3/20
            tomorrow
            today
            yesterday
            1 day ago
            2234 days ago
     */

    private static Stream<Arguments> requestedDatesAndLocalDateObjects() {
        return Stream.of(
            arguments(
                "2020-10-10",
                LocalDate.of(2020, 10, 10)
            ),
            arguments(
                "2020-12-2",
                LocalDate.of(2020, 12, 2)
            ),
            arguments(
                "1/3/1976",
                LocalDate.of(1976, 3, 1)
            ),
            arguments(
                "1/3/20",
                LocalDate.of(2020, 3, 1)
            ),
            arguments(
                "tomorrow",
                LocalDate.now().plusDays(1)
            ),
            arguments(
                "today",
                LocalDate.now()
            ),
            arguments(
                "yesterday",
                LocalDate.now().minusDays(1)
            ),
            arguments(
                "1 day ago",
                LocalDate.now().minusDays(1)
            ),
            arguments(
                "2234 days ago",
                LocalDate.now().minusDays(2234)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("requestedDatesAndLocalDateObjects")
    void parseDate_WhenGivenValidDateFormat_ReturnsRequestedDate(String date, LocalDate expectedDate) {
        DateParser dateParser = new DateParser();

        Optional<LocalDate> resultDate = dateParser.parseDate(date);

        assertThat(resultDate.get()).isEqualTo(expectedDate);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2020 01 10", "yesterd", "20/20/20", "20120-10-01", "day ago", "1 days ago", "11 day ago",
        "day"})
    void parseDate_WhenGivenInvalidDateFormat_ReturnsNull(String date) {
        DateParser dateParser = new DateParser();

        Optional<LocalDate> resultDate = dateParser.parseDate(date);

        assertThat(resultDate.orElse(null)).isNull();
    }
}
