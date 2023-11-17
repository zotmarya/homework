package edu.hw5.task8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PatternCheckerVer2Test {
    private PatternCheckerVer2 patternCheckerVer2;

    @BeforeEach
    void setUp() {
        patternCheckerVer2 = new PatternCheckerVer2();
    }

    @ParameterizedTest
    @ValueSource(strings = {"111", "01010", "0"})
    void isOddLength_WhenGivenValidInput_ReturnsTrue(String input) {
        boolean result = patternCheckerVer2.isOddLength(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1111", "010101", "00"})
    void isOddLength_WhenGivenInvalidInput_ReturnsFalse(String input) {
        boolean result = patternCheckerVer2.isOddLength(input);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "01010", "11", "1101"})
    void doesStartWith0AndOddOrStartsWith1AndEven_WhenGivenValidInput_ReturnsTrue(String input) {
        boolean result = patternCheckerVer2.doesStartWith0AndOddOrStartsWith1AndEven(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"00", "010101", "110", "11011"})
    void doesStartWith0AndOddOrStartsWith1AndEven_WhenGivenInvalidInput_ReturnsFalse(String input) {
        boolean result = patternCheckerVer2.doesStartWith0AndOddOrStartsWith1AndEven(input);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"000000", "01010", "10100000", "110111010"})
    void isDivisibleBy3_WhenGivenValidInput_ReturnsTrue(String input) {
        boolean result = patternCheckerVer2.isDivisibleBy3(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"00", "01011", "110", "1111"})
    void isDivisibleBy3_WhenGivenInvalidInput_ReturnsFalse(String input) {
        boolean result = patternCheckerVer2.isDivisibleBy3(input);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"000000", "01010", "10100000", "110111010"})
    void isAnyLineExcept11Or111_WhenGivenValidInput_ReturnsTrue(String input) {
        boolean result = patternCheckerVer2.isAnyLineExcept11Or111(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"11", "111"})
    void isAnyLineExcept11Or111_WhenGivenInvalidInput_ReturnsFalse(String input) {
        boolean result = patternCheckerVer2.isAnyLineExcept11Or111(input);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"101010", "1", "101", "10111110111"})
    void isEveryOddSymbol1_WhenGivenValidInput_ReturnsTrue(String input) {
        boolean result = patternCheckerVer2.isEveryOddSymbol1(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "01", "010101", "1010101000"})
    void isEveryOddSymbol1_WhenGivenInvalidInput_ReturnsFalse(String input) {
        boolean result = patternCheckerVer2.isEveryOddSymbol1(input);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"001", "010000", "100000", "00100"})
    void hasAtLeastTwo0AndAtMostOne1_WhenGivenValidInput_ReturnsTrue(String input) {
        boolean result = patternCheckerVer2.hasAtLeastTwo0AndAtMostOne1(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "01", "010101", "1", "00"})
    void hasAtLeastTwo0AndAtMostOne1_WhenGivenInvalidInput_ReturnsFalse(String input) {
        boolean result = patternCheckerVer2.hasAtLeastTwo0AndAtMostOne1(input);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "001", "101010001", "010000", "100000", "00100"})
    void doesNotHaveConsecutive1_WhenGivenValidInput_ReturnsTrue(String input) {
        boolean result = patternCheckerVer2.doesNotHaveConsecutive1(input);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"011", "1111101", "1010011", "111"})
    void doesNotHaveConsecutive1_WhenGivenInvalidInput_ReturnsFalse(String input) {
        boolean result = patternCheckerVer2.doesNotHaveConsecutive1(input);

        assertThat(result).isFalse();
    }

}
