package edu.hw3.task4;

public class RomanConverter {
    public String convertNumberFromArabToRoman(int number) {
        StringBuilder romanNumber = new StringBuilder();
        int tens = 10;

        while (number > 0) {
            //digit from right to left
            int tensNumber = number % tens;

            //next tens base
            tens *= 10;

            if (tensNumber < 10) {
                romanNumber.append(switch (tensNumber) {
                    case 1 -> "I";
                    case 2 -> "II";
                    case 3 -> "III";
                    case 4 -> "IV";
                    case 5 -> "V";
                    case 6 -> "VI";
                    case 7 -> "VII";
                    case 8 -> "VIII";
                    case 9 -> "IX";
                    default -> "Unknown argument.";
                });
            } else if (tensNumber >= 10 && tensNumber <= 30) {
                int tempNum = tensNumber;

                while (tempNum > 0) {
                    romanNumber.append("X");
                    tempNum -= 10;
                }
            }

            number = number - tensNumber;
        }

        return romanNumber.reverse().toString();
    }
}
