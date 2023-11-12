package edu.hw5.task5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class RussianLicensePlateCheckerTest {
    @ParameterizedTest
    @ValueSource(strings = {"A123BE777", "O777OО177"})
    void isLicensePlateNumberValid_WhenGivenValidNumber_ReturnsTrue(String plateNumber) {
        RussianLicensePlateChecker plateChecker = new RussianLicensePlateChecker();

        boolean result = plateChecker.isLicensePlateNumberValid(plateNumber);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"123АВЕ777", "А123ВГ77", "А123ВЕ7777"})
    void isLicensePlateNumberValid_WhenGivenInvalidNumber_ReturnsFalse(String plateNumber) {
        RussianLicensePlateChecker plateChecker = new RussianLicensePlateChecker();

        boolean result = plateChecker.isLicensePlateNumberValid(plateNumber);

        assertThat(result).isFalse();
    }

    @Test
    void isLicensePlateNumberValid_WhenGivenNull_ReturnsFalse() {
        RussianLicensePlateChecker plateChecker = new RussianLicensePlateChecker();

        boolean result = plateChecker.isLicensePlateNumberValid(null);

        assertThat(result).isFalse();
    }
}
