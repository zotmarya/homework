package edu.project1;

public class HangmanGame {
    private static final GameHandler GAME_HANDLER = GameHandler.getInstance();

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {

        while (true) {
            GAME_HANDLER.startGame();
        }

    }

    private HangmanGame() {
    }
}
