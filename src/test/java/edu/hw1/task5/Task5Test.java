package edu.hw1.task5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task5Test {

    private Task5 task5;

    @BeforeEach
    void setUp() {
        task5 = new Task5();
    }

    @ParameterizedTest
    @ValueSource(ints = {11211230, 13001120, 23336014, 11})
    void isNumOrDescendantPalindrome_WhenPalindromeNumber_ReturnsTrue(int number) {
        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isTrue();
    }

    @Test
    void isNumOrDescendantPalindrome_WhenNegativeNumber_ReturnsFalse() {
        int number = -2222;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isFalse();
    }

    @Test
    void isNumOrDescendantPalindrome_WhenOddLengthPalindromeNumber_ReturnsTrue() {
        int number = 363;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isTrue();
    }

    @Test
    void isNumOrDescendantPalindrome_WhenOddLengthNotPalindromeNumber_ReturnsFalse() {
        int number = 123;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isFalse();
    }

    @Test
    void isNumOrDescendantPalindrome_WhenOneDigitNumber_ReturnsTrue() {
        int number = 1;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isTrue();
    }

    @Test
    void isNumOrDescendantPalindrome_WhenOneDigitNumberDescendant_ReturnsFalse() {
        int number = 18;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isFalse();
    }

}
