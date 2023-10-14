package edu.hw1.task2;

public class Task2 {

    int digitsCount(int number) {

        int absNumber = Math.abs(number);

        return (absNumber == 0) ? 1 : (int) (Math.log10(absNumber) + 1);
    }

}
