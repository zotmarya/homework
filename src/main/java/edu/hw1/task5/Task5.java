package edu.hw1.task5;

public class Task5 {
    public boolean isNumOrDescendantPalindrome(int number) {

        if (number < 0) {
            return false;
        }

        int descendant = number;

        while (true) {
            char[] digits = String.valueOf(descendant).toCharArray();
            if (isPalindrome(digits)) {
                return true;
            }
            if (digits.length % 2 != 0) {
                return false;
            }
            descendant = createDescendant(digits);

            if (descendant <= MAX_DIGIT) {
                return false;
            }
        }
    }

    private boolean isPalindrome(char[] symbols) {

        for (int i = 0, j = symbols.length - 1; i < j; i++, j--) {
            if (symbols[i] != symbols[j]) {
                return false;
            }
        }

        return true;
    }

    private final static int MULTIPLY_TEN = 10;
    private final static int MULTIPLY_HUNDRED = 100;

    private final static int MAX_DIGIT = 9;

    private int createDescendant(char[] digits) {
        int number = 0;

        for (int i = 0; i < digits.length; i += 2) {
            int sum = (digits[i] - '0') + (digits[i + 1] - '0');

            number = number * (sum > MAX_DIGIT ? MULTIPLY_HUNDRED : MULTIPLY_TEN) + sum;

        }

        return number;
    }

}
