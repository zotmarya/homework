package edu.project1;

public class Player {
    private static Player player;
    private int mistakesMade;
    private int guessedLettersAmount;
    private int playerChoice;
    private boolean isPlaying;

    public static Player getInstance() {
        if (player == null) {
            player = new Player();
        }

        return player;
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
        isPlaying = true;
    }

    private Player() {
    }
}
