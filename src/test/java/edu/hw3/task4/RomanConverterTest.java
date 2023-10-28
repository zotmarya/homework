package edu.hw3.task4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class RomanConverterTest {

    private RomanConverter romanConverter;

    @BeforeEach
    void setUp() {
        romanConverter = new RomanConverter();
    }

    @ParameterizedTest
    @CsvSource({"2,II", "12, XII", "10,X", "3999,MMMCMXCIX", "375,CCCLXXV"})
    void convertNumberFromArabToRoman_WhenGivenArabNumber_ReturnsCorrectRomanNumber(
        int arabNumber,
        String romanNumber
    ) {
        String convertedNumber = romanConverter.convertNumberFromArabToRoman(arabNumber);

        assertThat(convertedNumber).isEqualTo(romanNumber);
    }

    @ParameterizedTest
    @ValueSource(ints = {4002, 0, -3})
    void convertNumberFromArabToRoman_WhenGivenOutOfRangeArabNumber_ReturnsNull(int arabNumber) {
        String convertedNumber = romanConverter.convertNumberFromArabToRoman(arabNumber);

        assertThat(convertedNumber).isNull();
    }
}
