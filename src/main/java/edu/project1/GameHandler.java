package edu.project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameHandler {
    private static GameHandler gameHandler;
    private HangmanPictureHandler hangmanPictureGenerator;
    private Player player;
    private Menu menu;
    private WordHandler wordHandler;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    public static final int MAX_MISTAKES = 5;

    private GameHandler() {
    }

    public Player getPlayer() {
        return player;
    }

    public static GameHandler getInstance() {
        if (gameHandler == null) {
            gameHandler = new GameHandler();
        }

        return gameHandler;
    }

    public void initialize() {
        hangmanPictureGenerator = HangmanPictureHandler.getInstance();
        player = Player.getInstance();
        menu = Menu.getInstance();
        wordHandler = WordHandler.getInstance();
    }

    public void printMenu() {
        LOGGER.info(menu.outputMenu() + menu.askForPlayerChoice());
    }

    public void prepareForNewGame() {
        player.newGame();
        wordHandler.setRandomWord();
        wordHandler.fillHashMap();
    }

    public void prepareForNewGame(String word) {
        player.newGame();
        wordHandler.setNewWord(word);
        wordHandler.fillHashMap();
    }

    public void playerMakesChoice() {
        int playerChoice = readChoice();

        switch (playerChoice) {
            case 1 -> {
                prepareForNewGame();
                playRound();
            }
            case 2 -> exitGame();
            default -> LOGGER.info("Unknown choice.");
        }
    }

    private int readChoice() {
        int choice = 0;

        while (choice != 1 && choice != 2) {
            try {
                String line = BUFFERED_READER.readLine();

                if (line.charAt(0) == '-') {
                    LOGGER.info("Enter option 1 or 2.");
                }

                choice = Integer.valueOf(line);
            } catch (NumberFormatException exception) {
                LOGGER.info("Incorrect input. Enter option 1 or 2.");
            } catch (IOException exception) {
                LOGGER.info(exception);
                System.exit(2);
            }
        }

        return choice;
    }

    private static final String BYE_MESSAGE = "GOOD BYE!😁";

    private void exitGame() {
        LOGGER.info(BYE_MESSAGE);
        System.exit(0);
    }

    private static final String ALREADY_TRIED_RESPONSE = "YOU'VE ALREADY TRIED THIS LETTER 🤔";

    private void playRound() {
        while (player.isPlaying()) {
            printCurrentGameState();

            char playerLetter = playerInputsLetter();

            if (playerLetter == '/') {
                return;
            }

            int guessedLettersAmount = wordHandler.checkIfPlayerGuessedLetter(playerLetter);

            makeReactionToGuessedLettersAmount(guessedLettersAmount);
        }

    }

    public void makeReactionToGuessedLettersAmount(int guessedLettersAmount) {
        if (guessedLettersAmount > 0) {
            reactToCorrectGuess(guessedLettersAmount);
        } else if (guessedLettersAmount == -1) {
            reactToIncorrectGuess();
        } else {
            LOGGER.info(ALREADY_TRIED_RESPONSE);
        }
    }

    public void reactToPlayerSurrendered() {
        LOGGER.info("🤨");
        LOGGER.info("THE SECRET WORD WAS: " + wordHandler.getWord());
    }

    private void printCurrentGameState() {
        LOGGER.info(hangmanPictureGenerator.getHangmanPicture(player.getMistakesMade()));
        printGuessesMade();
        LOGGER.info(wordHandler.getGuessedLettersString());
        LOGGER.info("AVAILABLE LETTERS: " + wordHandler.getNotUsedLetters());
    }

    private void printGuessesMade() {
        LOGGER.info("TRIES: " + player.getMistakesMade() + " out of " + MAX_MISTAKES);
    }

    public char playerInputsLetter() {
        LOGGER.info("GUESS A LETTER (OR WRITE 'I SURRENDER'): ");

        String string = null;
        char letter = ' ';

        do {
            if (string != null) {
                LOGGER.info("WRITE ENGLISH LETTER.");
            }

            try {
                string = BUFFERED_READER.readLine().trim().toUpperCase();

                if (string.equals("I SURRENDER")) {
                    reactToPlayerSurrendered();
                    player.setPlaying(false);
                    return '/';
                }

                letter = string.charAt(0);

            } catch (IOException exception) {
                LOGGER.info(exception);
                System.exit(2);
            }

        } while (string.length() != 1 || !((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z')));

        return letter;
    }

    private static final String CORRECT_RESPONSE = "CORRECT 😎";

    private void reactToCorrectGuess(int guessedLettersAmount) {
        player.increaseGuessedLettersAmount(guessedLettersAmount);
        LOGGER.info(CORRECT_RESPONSE);

        if (player.getGuessedLettersAmount() == wordHandler.getWordLength()) {
            outputPlayerWon();
            player.setPlaying(false);
        }
    }

    private static final String WON_MESSAGE = "YOU WON!🥳";

    private void outputPlayerWon() {
        LOGGER.info(hangmanPictureGenerator.getHangmanPicture(HangmanPictureHandler.WIN_INDEX));
        LOGGER.info("THE GUESSED WORD: " + wordHandler.getWord());
        LOGGER.info(WON_MESSAGE);
    }

    private static final String NOT_CORRECT_RESPONSE = "NO LUCK 😔";

    private void reactToIncorrectGuess() {
        player.increaseMistakesMadeByOne();
        LOGGER.info(NOT_CORRECT_RESPONSE);

        if (player.getMistakesMade() == MAX_MISTAKES) {
            outputPlayerLost();
            player.setPlaying(false);
        }
    }

    private static final String LOST_MESSAGE = "YOU LOST!😦";

    private void outputPlayerLost() {
        LOGGER.info(hangmanPictureGenerator.getHangmanPicture(player.getMistakesMade()));
        printGuessesMade();
        LOGGER.info(LOST_MESSAGE);
        LOGGER.info("THE SECRET WORD: " + wordHandler.getWord());
    }
}
