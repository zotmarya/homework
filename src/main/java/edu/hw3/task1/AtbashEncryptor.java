package edu.hw3.task1;

public class AtbashEncryptor {

    public String useAtbashEncryption(String wordToEncrypt) {
        if (wordToEncrypt == null) {
            return null;
        }

        char[] letters = wordToEncrypt.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            char letter = letters[i];

            if (letter <= 'Z' && letter >= 'A') {
                letters[i] = cypher(letter, 'A', 'Z');
            } else if (letter <= 'z' && letter >= 'a') {
                letters[i] = cypher(letter, 'a', 'z');
            }
        }

        return new String(letters);
    }

    private char cypher(char letter, char left, char right) {
        return (char) (left + right - letter);
    }
}
