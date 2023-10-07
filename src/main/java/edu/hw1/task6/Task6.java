package edu.hw1.task6;

import java.util.Arrays;

public class Task6 {

    private final int multiplyTen = 10;
    private final int kNumber = 6174;

    private final int leftBorder = 1000;
    private final int rightBorder = 9999;

    private final int indexForFourthElement = 3;

    public int countK(int number) {
        if (number < leftBorder || number > rightBorder) {
            return -1;
        }

        if (number == kNumber) {
            return 0;
        }

        char[] digits = String.valueOf(number).toCharArray();

        // check if all digits are same
        if (digits[0] == digits[1] && digits[1] == digits[2] && digits[2] == digits[indexForFourthElement]) {
            return -1;
        }

        return stepsCountForK(number);
    }

    private int stepsCountForK(int number) {
        char[] digits = String.valueOf(number).toCharArray();

        Arrays.sort(digits);

        int descNumber = 0;
        int ascNumber = 0;

        // ascend and descend numbers
        for (int i = 0, j = digits.length - 1; i < digits.length; i++, j--) {
            ascNumber = ascNumber * multiplyTen + digits[i];

            descNumber = descNumber * multiplyTen + digits[j];
        }

        int difference = descNumber - ascNumber;

        if (difference == kNumber) {
            return 1;
        }

        return 1 + stepsCountForK(difference);
    }
}
