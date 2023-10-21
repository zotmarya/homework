package edu.project1;

public class HangmanGame {
    private static final GameHandler GAME_HANDLER = GameHandler.getInstance();

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        GAME_HANDLER.startGame();

        while (true) {
            GAME_HANDLER.printMenu();
            GAME_HANDLER.playerMakesChoice();
        }

    }

    private HangmanGame() {
    }
}
