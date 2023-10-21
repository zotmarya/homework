package edu.project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameSettings {
    private static GameSettings gameSettings;
    public static final ArrayList<String> WORDS = new ArrayList<>();;
    public static final int MAX_MISTAKES = 5;
    private int mistakesMade;
    private int guessedLettersAmount;
    private int playerChoice;

    private boolean isPlaying;

    static {
        readTxtFileWithWordsToGuess();
    }

    private static void readTxtFileWithWordsToGuess() {
        try (
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/project1/words.txt"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                WORDS.add(line.trim().toUpperCase());
            }
        } catch (IOException exception) {
        }
    }

    public static GameSettings getInstance() {
        if (gameSettings == null) {
            gameSettings = new GameSettings();
        }

        return gameSettings;
    }

    public int getMistakesMade() {
        return mistakesMade;
    }

    public int getGuessedLettersAmount() {
        return guessedLettersAmount;
    }

    public int getPlayerChoice() {
        return playerChoice;
    }

    public void setPlayerChoice(int playerChoice) {
        this.playerChoice = playerChoice;
    }

    public void increaseMistakesMadeByOne() {
        mistakesMade++;
    }

    public void increaseGuessedLettersAmount(int increaseAmount) {
        this.guessedLettersAmount += increaseAmount;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public void newGame() {
        mistakesMade = 0;
        guessedLettersAmount = 0;
    }

    private GameSettings() {
    }
}
