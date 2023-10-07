package edu.hw1.task5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task5Test {

    @Test
    void palindromeNumber() {
        Task5 task5 = new Task5();

        int number = 11211230;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isTrue();
    }

    @Test
    void negativeNumber() {
        Task5 task5 = new Task5();

        int number = -2222;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isFalse();
    }

    @Test
    void oddLengthPalindromeNumber() {
        Task5 task5 = new Task5();

        int number = 363;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isTrue();
    }

    @Test
    void oddLengthNotPalindromeNumber() {
        Task5 task5 = new Task5();

        int number = 123;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isFalse();
    }

    @Test
    void oneDigitNumber() {
        Task5 task5 = new Task5();

        int number = 1;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isTrue();
    }

    @Test
    void oneDigitNumberDescendant() {
        Task5 task5 = new Task5();

        int number = 18;

        boolean isPalindrome = task5.isNumOrDescendantPalindrome(number);

        assertThat(isPalindrome).isFalse();
    }

}
