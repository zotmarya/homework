package edu.hw5.task1;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SessionTimeAnalyzerTest {

    private static Stream<Arguments> sessionsAndAverageDuration() {
        return Stream.of(
            arguments(
                List.of(
                    "2022-03-12, 20:20 - 2022-03-12, 23:50",
                    "2022-04-01, 21:30 - 2022-04-02, 01:20"
                ),
                Duration.ofHours(3).plusMinutes(40)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("sessionsAndAverageDuration")
    void calculateSessionTime_WhenGivenListOfSessions_ReturnsAverage(List<String> sessions, Duration expectedDuration) {
        SessionTimeAnalyzer sessionTimeAnalyzer = new SessionTimeAnalyzer();

        Duration resultDuration = sessionTimeAnalyzer.calculateSessionTime(sessions);

        assertThat(resultDuration).isEqualTo(expectedDuration);
    }

    private static Stream<Arguments> invalidSessions() {
        return Stream.of(
            arguments(
                List.of(
                    "2022-03-2, 20:20 - 2022-03-12, 23:50",
                    "2022-04-01, 21:30 - 2022-04-02, 01:20"
                ),
                List.of(
                    "2022",
                    "2022-04-01, 21:30 - 2022-04-02, 01:20"
                ),
                List.of(
                    "2022-03-2, 20:20 - 2022-03-12, 23:50",
                    "2022-04-01, 21:30 - 2022-04-02, 01:20"
                ),
                List.of(
                    "2022-04-01, 21:30 - 2022-04-02, 01:20",
                    ""
                ),
                null
            )
        );
    }

    @ParameterizedTest
    @MethodSource("invalidSessions")
    void calculateSessionTime_WhenGivenInvalidSessionFormat_ReturnsNull(List<String> sessions) {
        SessionTimeAnalyzer sessionTimeAnalyzer = new SessionTimeAnalyzer();

        Duration resultDuration = sessionTimeAnalyzer.calculateSessionTime(sessions);

        assertThat(resultDuration).isNull();
    }

}
