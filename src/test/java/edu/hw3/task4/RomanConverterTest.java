package edu.hw3.task4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class RomanConverterTest {

    private RomanConverter romanConverter;

    @BeforeEach
    void setUp() {
        romanConverter = new RomanConverter();
    }

    @ParameterizedTest
    @CsvSource({"2,II", "12, XII", "10,X"})
    void convertNumberFromArabToRoman_WhenCorrectNumberFormat_ReturnsCorrectRomanNumber(
        int arabNumber,
        String romanNumber
    ) {
        String convertedNumber = romanConverter.convertNumberFromArabToRoman(arabNumber);

        assertThat(convertedNumber).isEqualTo(romanNumber);
    }
}
