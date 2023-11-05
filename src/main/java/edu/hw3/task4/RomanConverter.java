package edu.hw3.task4;

import java.util.TreeMap;

public class RomanConverter {

    private static final int MAX_NUMBER = 3999;

    private static final TreeMap<Integer, String> ARAB_ROMAN_MAP = new TreeMap<>();

    static {
        initializeMap();
    }

    @SuppressWarnings("MagicNumber")
    private static void initializeMap() {
        ARAB_ROMAN_MAP.put(1000, "M");
        ARAB_ROMAN_MAP.put(900, "CM");
        ARAB_ROMAN_MAP.put(500, "D");
        ARAB_ROMAN_MAP.put(400, "CD");
        ARAB_ROMAN_MAP.put(100, "C");
        ARAB_ROMAN_MAP.put(90, "XC");
        ARAB_ROMAN_MAP.put(50, "L");
        ARAB_ROMAN_MAP.put(40, "XL");
        ARAB_ROMAN_MAP.put(10, "X");
        ARAB_ROMAN_MAP.put(9, "IX");
        ARAB_ROMAN_MAP.put(5, "V");
        ARAB_ROMAN_MAP.put(4, "IV");
        ARAB_ROMAN_MAP.put(1, "I");
    }

    @SuppressWarnings("MagicNumber")
    public String convertNumberFromArabToRoman(int arabNumber) {
        if (arabNumber > MAX_NUMBER || arabNumber < 1) {
            return null;
        }

        int tempNumber = arabNumber;

        StringBuilder romanNumber = new StringBuilder();

        for (Integer number : ARAB_ROMAN_MAP.descendingKeySet()) {
            while (tempNumber >= number) {
                tempNumber -= number;
                romanNumber.append(ARAB_ROMAN_MAP.get(number));
            }
        }

        return romanNumber.toString();
    }

}
