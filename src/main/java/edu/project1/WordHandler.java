package edu.project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WordHandler {

    public ArrayList<String> words = new ArrayList<>();
    private static WordHandler wordHandler;
    private String word;
    private char[] wordSymbols;
    private char[] guessedLetters;
    private int wordLength;

    private HashMap<Character, Boolean> lettersUsedMap = new HashMap<>();
    private static final char[] LETTERS_ENG = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private WordHandler() {
        readTxtFileWithWordsToGuess();
        fillHashMap();
    }

    public void fillHashMap() {
        for (char letter : LETTERS_ENG) {
            lettersUsedMap.put(letter, false);
        }
    }

    public String getNotUsedLetters() {
        StringBuilder letters = new StringBuilder();

        for (Map.Entry<Character, Boolean> entry : lettersUsedMap.entrySet()) {
            if (!entry.getValue()) {
                letters.append(entry.getKey() + " ");
            }
        }

        return letters.toString();
    }

    private static final int MAX_WORD_LENGTH = 20;
    private static final int MIN_WORD_LENGTH = 3;

    private void readTxtFileWithWordsToGuess() {
        try (
            BufferedReader bufferedReader =
                new BufferedReader(new FileReader("src/main/resources/project1/words.txt"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() <= MAX_WORD_LENGTH && line.length() >= MIN_WORD_LENGTH) {
                    words.add(line.trim().toUpperCase());
                }
            }

        } catch (IOException exception) {
        } finally {
            if (words.size() == 0) {
                words.addAll(Arrays.asList("WORLD", "COFFEE", "MAGIC"));
            }
        }
    }

    public void setNewWord(String word) {
        if (word.length() > MAX_WORD_LENGTH || word.length() < MIN_WORD_LENGTH) {
            setRandomWord();
        }

        this.word = word;
        this.wordSymbols = word.toCharArray();
        guessedLetters = new char[wordSymbols.length];

        wordLength = wordSymbols.length;

        fillArrayWithAsterisks();
    }

    public void setRandomWord() {
        Random random = new Random();
        word = words.get(random.nextInt(words.size()));

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

    private void fillArrayWithAsterisks() {
        Arrays.fill(guessedLetters, '*');
    }

    public int checkIfPlayerGuessedLetter(char letter) {
        if (lettersUsedMap.get(letter)) {
            return 0;
        }

        int guessedAmount = 0;
        boolean hasGuessed = false;

        lettersUsedMap.put(letter, true);

        for (int i = 0; i < wordSymbols.length; i++) {
            if (letter == wordSymbols[i]) {
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
        return word;
    }
}
