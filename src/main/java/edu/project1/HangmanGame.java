package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {
    private static HangmanPictureHandler hangmanPictureGenerator;
    private static GameSettings gameSettings;
    private static Menu menu;
    private static WordHandler wordHandler;
    private static char playerLetter;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        initialize();

        while (true) {
            LOGGER.info(menu.outputMenu() + menu.askForPlayerChoice());

            playerMakesChoice();
        }

    }

    private static void initialize() {
        hangmanPictureGenerator = HangmanPictureHandler.getInstance();
        gameSettings = GameSettings.getInstance();
        menu = Menu.getInstance();
    }

    private static void prepareForNewGame() {
        gameSettings.newGame();

        Random random = new Random();
        wordHandler = WordHandler.getInstance();
        wordHandler.setNewWord(GameSettings.WORDS.get(random.nextInt(GameSettings.WORDS.size())));
    }

    private static void playerMakesChoice() {

        while (!SCANNER.hasNext("[0-9]+")) {
            LOGGER.info("Incorrect input.");
            SCANNER.nextLine();
        }

        gameSettings.setPlayerChoice(SCANNER.nextInt());

        switch (gameSettings.getPlayerChoice()) {
            case 1 -> {
                SCANNER.nextLine();

                prepareForNewGame();
                gameSettings.setPlaying(true);
                playRound();
            }
            case 2 -> exitGame();
            default -> LOGGER.info("Unknown choice.");
        }
    }

    private static final String BYE_MESSAGE = "Good bye!😁";

    private static void exitGame() {
        LOGGER.info(BYE_MESSAGE);
        System.exit(0);
    }

    private static final String ALREADY_GUESSED_RESPONSE = "YOU'VE ALREADY GUESSED THIS LETTER 🤔";
    private static final String CORRECT_RESPONSE = "CORRECT 😎";
    private static final String NOT_CORRECT_RESPONSE = "NO LUCK 😔";

    private static void playRound() {
        while (gameSettings.isPlaying()) {
            printCurrentGameState();

            playerGuessesLetter();

            int guessedLettersAmount = wordHandler.checkIfPlayerGuessedLetter(playerLetter);

            if (guessedLettersAmount > 0) {
                reactToCorrectGuess(guessedLettersAmount);
            } else if (guessedLettersAmount == -1) {
                reactToIncorrectGuess();
            } else {
                LOGGER.info(ALREADY_GUESSED_RESPONSE);
            }
        }

    }

    private static void printCurrentGameState() {
        LOGGER.info(hangmanPictureGenerator.getHangmanPicture(gameSettings.getMistakesMade()));
        LOGGER.info(wordHandler.getGuessedLettersString());
    }

    private static void playerGuessesLetter() {
        LOGGER.info("GUESS A LETTER: ");

        while (!SCANNER.hasNext("[a-z]|[A-Z]")) {
            LOGGER.info("WRITE ENGLISH LETTER.");
            SCANNER.nextLine();
        }

        playerLetter = SCANNER.nextLine().toUpperCase().charAt(0);
    }

    private static final String WON_MESSAGE = "YOU WON!🥳";

    private static void reactToCorrectGuess(int guessedLettersAmount) {
        gameSettings.increaseGuessedLettersAmount(guessedLettersAmount);
        LOGGER.info(CORRECT_RESPONSE);

        if (gameSettings.getGuessedLettersAmount() == wordHandler.getWordLength()) {
            outputPlayerWon();
            gameSettings.setPlaying(false);
        }
    }

    private static void outputPlayerWon() {
        LOGGER.info("THE GUESSED WORD: " + wordHandler.getGuessedLettersString());
        LOGGER.info(WON_MESSAGE);
    }

    private static final String LOST_MESSAGE = "YOU LOST!😦";

    private static void reactToIncorrectGuess() {
        gameSettings.increaseMistakesMadeByOne();
        LOGGER.info(NOT_CORRECT_RESPONSE);

        if (gameSettings.getMistakesMade() == GameSettings.MAX_MISTAKES) {
            outputPlayerLost();
            gameSettings.setPlaying(false);
        }
    }

    private static void outputPlayerLost() {
        LOGGER.info(hangmanPictureGenerator.getHangmanPicture(gameSettings.getMistakesMade()));
        LOGGER.info(LOST_MESSAGE);
        LOGGER.info("THE WORD THAT WAS GUESSED: " + wordHandler.getWord());
    }
}
