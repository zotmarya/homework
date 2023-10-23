package edu.hw3.task1;

public class AtbashEncryptor {

    public String useAtbashEncryption(String wordToEncrypt) {
        char[] letters = wordToEncrypt.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            char letter = letters[i];
            int difference;

            if (letter <= 'Z' && letter >= 'A') {
                if (letter < 'N') {
                    difference = letter - 'A';
                    letters[i] = (char) ('Z' - difference);
                } else {
                    difference = 'Z' - letter;
                    letters[i] = (char) ('A' + difference);
                }

            } else if (letter <= 'z' && letter >= 'a') {
                if (letter < 'n') {
                    difference = letter - 'a';
                    letters[i] = (char) ('z' - difference);
                } else {
                    difference = 'z' - letter;
                    letters[i] = (char) ('a' + difference);
                }
            }
        }

        return new String(letters);
    }
}
