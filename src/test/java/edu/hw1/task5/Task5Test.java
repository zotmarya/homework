package edu.hw1.task5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task5Test {

    Task5 task5;

    @BeforeEach
    void setUp() {
        task5 = new Task5();
    }

    @Test
    void palindromeNumber() {
        int number = 11211230;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isTrue();
    }

    @Test
    void negativeNumber() {
        int number = -2222;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isFalse();
    }

    @Test
    void oddLengthPalindromeNumber() {
        int number = 363;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isTrue();
    }

    @Test
    void oddLengthNotPalindromeNumber() {
        int number = 123;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isFalse();
    }

    @Test
    void oneDigitNumber() {
        int number = 1;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isTrue();
    }

    @Test
    void oneDigitNumberDescendant() {
        int number = 18;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isFalse();
    }

}
