package edu.project1;

public class WordHandler {
    private char[] wordSymbols;
    private char[] guessedLetters;

    private int wordLen;

    public WordHandler(String word) {
        this.wordSymbols = word.toCharArray();
        guessedLetters = new char[wordSymbols.length];

        wordLen = wordSymbols.length;

        for (int i = 0; i < guessedLetters.length; i++) {
            guessedLetters[i] = '*';
        }
    }

    public int getWordLen() {
        return wordLen;
    }

    public String checkIfGuessedLetter (char letter) {

        boolean hasGuessed = false;

        for (int i = 0; i < wordSymbols.length; i++) {
            if(letter == wordSymbols[i]) {
                if(guessedLetters[i] == letter) return "YOU'VE ALREADY GUESSED THIS LETTER 🤔";

                guessedLetters[i] = wordSymbols[i];

                hasGuessed = true;
            }
        }

        return hasGuessed == true ? "CORRECT 😎" : "NO LUCK 😔";
    }

    public char[] getGuessedLetters() {
        return guessedLetters;
    }

    public String getGuessedLettersString() {
        return new String(guessedLetters);
    }
}
