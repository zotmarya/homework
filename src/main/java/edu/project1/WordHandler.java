package edu.project1;

import java.util.Arrays;

public class WordHandler {

    private static WordHandler wordHandler;
    private char[] wordSymbols;
    private char[] guessedLetters;
    private int wordLength;

    public void setNewWord(String word) {
        this.wordSymbols = word.toCharArray();
        guessedLetters = new char[wordSymbols.length];

        wordLength = wordSymbols.length;

        fillArrayWithAsterisks();
    }

    public static WordHandler getInstance() {
        if (wordHandler == null) {
            wordHandler = new WordHandler();
        }

        return wordHandler;
    }

    private WordHandler() {
    }

    private void fillArrayWithAsterisks() {
        Arrays.fill(guessedLetters, '*');
    }

    public int checkIfPlayerGuessedLetter(char letter) {
        int guessedAmount = 0;
        boolean hasGuessed = false;

        for (int i = 0; i < wordSymbols.length; i++) {
            if (letter == wordSymbols[i]) {
                if (guessedLetters[i] == letter) {
                    return 0;
                }

                guessedLetters[i] = wordSymbols[i];
                guessedAmount++;

                hasGuessed = true;
            }
        }

        return hasGuessed ? guessedAmount : -1;
    }

    public int getWordLength() {
        return wordLength;
    }

    public String getGuessedLettersString() {
        return new String(guessedLetters);
    }

    public String getWord() {
        return new String(wordSymbols);
    }
}
