package edu.hw5.task7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PatternCheckerVer1Test {

    private PatternCheckerVer1 patternCheckerVer1;

    @BeforeEach
    void setUp() {
        patternCheckerVer1 = new PatternCheckerVer1();
    }

    @ParameterizedTest
    @ValueSource(strings = {"110111", "000", "10011101010"})
    void hasLength3AndThirdSymbol0_WhenGivenValidInput_ReturnsTrue(String input) {
        boolean result = patternCheckerVer1.hasLength3AndThirdSymbol0(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "00101010", "111"})
    void hasLength3AndThirdSymbol0_WhenGivenInvalidInput_ReturnsTrue(String input) {
        boolean result = patternCheckerVer1.hasLength3AndThirdSymbol0(input);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"110111", "000", "10011101011"})
    void hasSameFirstAndLastSymbol_WhenGivenValidInput_ReturnsTrue(String input) {
        boolean result = patternCheckerVer1.hasSameFirstAndLastSymbol(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"01", "00101011", "110"})
    void hasSameFirstAndLastSymbol_WhenGivenInvalidInput_ReturnsTrue(String input) {
        boolean result = patternCheckerVer1.hasSameFirstAndLastSymbol(input);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"00", "010", "1"})
    void hasLengthInRangeFrom1To3_WhenGivenValidInput_ReturnsTrue(String input) {
        boolean result = patternCheckerVer1.hasLengthInRangeFrom1To3(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "01001", "00001001010001010"})
    void hasLengthInRangeFrom1To3_WhenGivenInvalidInput_ReturnsTrue(String input) {
        boolean result = patternCheckerVer1.hasLengthInRangeFrom1To3(input);

        assertThat(result).isFalse();
    }
}
