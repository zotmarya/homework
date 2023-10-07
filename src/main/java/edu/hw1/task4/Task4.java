package edu.hw1.task4;

public class Task4 {

    public String fixString(String mixedString) {

        int stringLen = mixedString.length();

        char[] fixedString = new char[stringLen];

        for (int i = 1; i < stringLen; i += 2) {
            fixedString[i - 1] = mixedString.charAt(i);
            fixedString[i] = mixedString.charAt(i - 1);
        }

        // if string length is odd then last symbol doesn't have a pair to swap,
        // so we add it to the end of character array
        if (stringLen % 2 != 0) {
            fixedString[stringLen - 1] = mixedString.charAt(stringLen - 1);
        }

        return new String(fixedString);
    }

}
