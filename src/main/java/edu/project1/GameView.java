package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameView {
    private static GameView gameView;
    private HangmanPictureHandler hangmanPictureGenerator;
    private Menu menu;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String CORRECT_RESPONSE = "CORRECT 😎";
    private static final String BYE_MESSAGE = "GOOD BYE!😁";
    private static final String ALREADY_TRIED_RESPONSE = "YOU'VE ALREADY TRIED THIS LETTER 🤔";
    private static final String WON_MESSAGE = "YOU WON!🥳";
    private static final String NOT_CORRECT_RESPONSE = "NO LUCK 😔";
    private static final String LOST_MESSAGE = "YOU LOST!😦";

    public static GameView getInstance() {
        if (gameView == null) {
            gameView = new GameView();
        }

        return gameView;
    }

    private GameView() {
        menu = Menu.getInstance();
        hangmanPictureGenerator = HangmanPictureHandler.getInstance();
    }

    public void printMenu() {
        LOGGER.info(menu.outputMenu() + menu.askForPlayerChoice());
    }

    public void reactToPlayerSurrendered(String wordGuessed) {
        LOGGER.info("🤨");
        LOGGER.info("THE SECRET WORD WAS: " + wordGuessed);
    }

    public void printCurrentGameState(Player player, WordHandler wordHandler, int maxMistakes) {
        LOGGER.info(hangmanPictureGenerator.getHangmanPicture(player.getMistakesMade()));
        outputGuessesMade(player.getMistakesMade(), maxMistakes);
        LOGGER.info(wordHandler.getGuessedLettersString());
        LOGGER.info("AVAILABLE LETTERS: " + wordHandler.getNotUsedLetters());
    }

    public void outputPlayerWon(String guessedWord) {
        LOGGER.info(hangmanPictureGenerator.getHangmanPicture(HangmanPictureHandler.WIN_INDEX));
        LOGGER.info("THE GUESSED WORD: " + guessedWord);
        LOGGER.info(WON_MESSAGE);
    }

    public void outputNotCorrectResponse() {
        LOGGER.info(NOT_CORRECT_RESPONSE);
    }

    public void outputByeMessage() {
        LOGGER.info(BYE_MESSAGE);
    }

    public void outputAlreadyTried() {
        LOGGER.info(ALREADY_TRIED_RESPONSE);
    }

    public void outputCorrectGuess() {
        LOGGER.info(CORRECT_RESPONSE);
    }

    private void outputGuessesMade(int mistakesMade, int maxMistakes) {
        LOGGER.info("TRIES: " + mistakesMade + " out of " + maxMistakes);
    }

    public void outputPlayerLost(Player player, WordHandler wordHandler, int maxMistakes) {
        LOGGER.info(hangmanPictureGenerator.getHangmanPicture(player.getMistakesMade()));
        outputGuessesMade(player.getMistakesMade(), maxMistakes);
        LOGGER.info(LOST_MESSAGE);
        LOGGER.info("THE SECRET WORD: " + wordHandler.getWord());
    }
}
