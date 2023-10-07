package edu.hw1.task7;

public class Task7 {

//    public int rotateBitsLeft(int number, int shift) {
//        return number << shift | number >> (intBitSize - shift);
//    }

//    public int rotateBitsRight(int number, int shift) {
//        return number >> shift | number << (intBitSize - shift);
//    }

    private final int intBitSize = 32;
    private final String exceptionMessage = "Argument(s) can't be negative.";

    public int rotateBitsRight(int number, int shift) {

        if (number == 0) {
            return 0;
        }

        if (number < 0 || shift < 0) {
            throw new IllegalArgumentException(exceptionMessage);
        }

        int leftBitPosition = findLeftmostBitPosition(number);

        int shiftModulo = shift % leftBitPosition;

        return number >>> shiftModulo | (number << (intBitSize - shiftModulo)) >>> (intBitSize - leftBitPosition);
    }

    public int rotateBitsLeft(int number, int shift) {

        if (number == 0) {
            return 0;
        }

        if (number < 0 || shift < 0) {
            throw new IllegalArgumentException(exceptionMessage);
        }

        int leftBitPosition = findLeftmostBitPosition(number);

        int shiftModulo = shift % leftBitPosition;

        shiftModulo = leftBitPosition - shiftModulo;

        return number >>> shiftModulo | (number << (intBitSize - shiftModulo)) >>> (intBitSize - leftBitPosition);
    }

    private int findLeftmostBitPosition(int number) {
        int position = 0;
        int num = number;

        while (num != 0) {
            num = num >> 1;
            position++;
        }

        return position;
    }

}
