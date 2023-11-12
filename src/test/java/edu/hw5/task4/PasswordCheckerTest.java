package edu.hw5.task4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordCheckerTest {
    @ParameterizedTest
    @ValueSource(strings = {"123!cat", "%~star"})
    void isPasswordStrong_WhenGivenPasswordWithSpecialSymbols_ReturnsTrue(String password) {
        PasswordChecker passwordChecker = new PasswordChecker();

        boolean result = passwordChecker.isPasswordStrong(password);

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"_cowboy0", "admin123"})
    void isPasswordStrong_WhenGivenPasswordWithoutSpecialSymbols_ReturnsFalse(String password) {
        PasswordChecker passwordChecker = new PasswordChecker();

        boolean result = passwordChecker.isPasswordStrong(password);

        assertThat(result).isFalse();
    }
}
