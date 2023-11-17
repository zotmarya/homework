package edu.hw2.task3;

public class RandomNumGenerator {
    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 2;

    static public int randomNum() {
        return (int) (Math.random() * (MAX_BOUND + 1 - MIN_BOUND) + MIN_BOUND);
    }

    private RandomNumGenerator() {
    }

}
