package edu.hw1.task6;

import java.util.Arrays;

public class Task6 {

    private final static int MULTIPLY_TEN = 10;
    private final static int K_NUMBER = 6174;

    private final static int LEFT_BORDER = 1000;
    private final static int RIGHT_BORDER = 9999;

    private final static int INDEX_FOR_FOURTH_ELEMENT = 3;

    public int countK(int number) {
        if (number < LEFT_BORDER || number > RIGHT_BORDER) {
            return -1;
        }

        if (number == K_NUMBER) {
            return 0;
        }

        char[] digits = String.valueOf(number).toCharArray();

        // check if all digits are same
        if (digits[0] == digits[1] && digits[1] == digits[2] && digits[2] == digits[INDEX_FOR_FOURTH_ELEMENT]) {
            return -1;
        }

        return stepsCountForK(number);
    }

    private final static int DIGITS_IN_NUMBER = 4;

    private int stepsCountForK(int number) {
        int[] digits = new int[DIGITS_IN_NUMBER];

        char[] digitsToTransfer = String.valueOf(number).toCharArray();

        for (int i = digitsToTransfer.length - 1, j = digits.length; i >= 0; i--, j--) {
            digits[j] = digitsToTransfer[i] - '0';
        }

        Arrays.sort(digits);

        int descNumber = 0;
        int ascNumber = 0;

        // ascend and descend numbers
        for (int i = 0, j = digits.length - 1; i < digits.length; i++, j--) {
            ascNumber = ascNumber * MULTIPLY_TEN + digits[i];

            descNumber = descNumber * MULTIPLY_TEN + digits[j];
        }

        int difference = descNumber - ascNumber;

        if (difference == K_NUMBER) {
            return 1;
        }

        return 1 + stepsCountForK(difference);
    }
}
