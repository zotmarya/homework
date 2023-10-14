package edu.hw1.task3;

public class Task3 {

    public boolean isNestable(int[] array1, int[] array2) {

        // check for empty arrays
        if (array2.length == 0) {
            return false;
        }
        if (array1.length == 0) {
            return true;
        }

        return minArrayValue(array1) > minArrayValue(array2)
            && maxArrayValue(array1) < maxArrayValue(array2);
    }

    private int maxArrayValue(int[] array) {

        int result = array[0];

        for (int value : array) {

            if (value > result) {
                result = value;
            }
        }

        return result;
    }

    private int minArrayValue(int[] array) {

        int result = array[0];

        for (int value : array) {

            if (value < result) {
                result = value;
            }
        }

        return result;
    }

}
