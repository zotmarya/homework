package edu.project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameHandler {
    private static GameHandler gameHandler;
    private GameView gameView;
    private Player player;
    private WordHandler wordHandler;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));
    public static final int MAX_MISTAKES = 5;

    private GameHandler() {
        player = Player.getInstance();
        gameView = GameView.getInstance();
        wordHandler = WordHandler.getInstance();
    }

    public static GameHandler getInstance() {
        if (gameHandler == null) {
            gameHandler = new GameHandler();
        }

        return gameHandler;
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

    public void startGame() {
        gameView.printMenu();
        playerMakesMenuChoice();
    }

    public void playerMakesMenuChoice() {
        int playerChoice = readMenuChoice();

        switch (playerChoice) {
            case 1 -> {
                prepareForNewGame();
                playRound();
            }
            case 2 -> exitGame();
            default -> LOGGER.info("Unknown choice.");
        }
    }

    private int readMenuChoice() {
        int choice = 0;

        while (choice != 1 && choice != 2) {
            try {
                String line = BUFFERED_READER.readLine();

                if (line.charAt(0) == '-') {
                    LOGGER.info("Enter option 1 or 2.");
                }

                choice = Integer.parseInt(line);
            } catch (NumberFormatException exception) {
                LOGGER.info("Incorrect input. Enter option 1 or 2.");
            } catch (IOException exception) {
                LOGGER.info(exception);
                System.exit(2);
            }
        }

        return choice;
    }

    private void exitGame() {
        gameView.outputByeMessage();
        System.exit(0);
    }

    private void playRound() {
        while (player.isPlaying()) {
            gameView.printCurrentGameState(player, wordHandler, MAX_MISTAKES);

            char playerLetter = playerInputsLetter();

            if (playerLetter == '/') {
                return;
            }

            int guessedLettersAmount = wordHandler.checkIfPlayerGuessedLetter(playerLetter);

            reactToGuessedLettersAmount(guessedLettersAmount);
        }
    }

    public void reactToGuessedLettersAmount(int guessedLettersAmount) {
        if (guessedLettersAmount > 0) {
            reactToCorrectGuess(guessedLettersAmount);
        } else if (guessedLettersAmount == -1) {
            reactToIncorrectGuess();
        } else {
            gameView.outputAlreadyTried();
        }
    }

    public char playerInputsLetter() {
        LOGGER.info("GUESS A LETTER (OR WRITE 'I SURRENDER'): ");

        String userInput = null;

        do {
            if (userInput != null) {
                LOGGER.info("WRITE ENGLISH LETTER.");
            }

            try {
                userInput = BUFFERED_READER.readLine().trim().toUpperCase();

                if ("I SURRENDER".equals(userInput)) {
                    gameView.reactToPlayerSurrendered(wordHandler.getWord());
                    player.setPlaying(false);
                    return '/';
                }

            } catch (IOException exception) {
                LOGGER.info(exception);
                System.exit(2);
            }

        } while (!checkIfStringIsOneSymbolLengthAndEngLetter(userInput));

        return userInput.charAt(0);
    }

    private boolean checkIfStringIsOneSymbolLengthAndEngLetter(String userInput) {
        char firstSymbol = userInput.charAt(0);

        return userInput.length() == 1
            && (firstSymbol >= 'A' && firstSymbol <= 'Z');
    }

    private void reactToCorrectGuess(int guessedLettersAmount) {
        player.increaseGuessedLettersAmount(guessedLettersAmount);
        gameView.outputCorrectGuess();

        if (player.getGuessedLettersAmount() == wordHandler.getWordLength()) {
            gameView.outputPlayerWon(wordHandler.getWord());
            player.setPlaying(false);
        }
    }

    private void reactToIncorrectGuess() {
        player.increaseMistakesMadeByOne();
        gameView.outputNotCorrectResponse();

        if (player.getMistakesMade() == MAX_MISTAKES) {
            gameView.outputPlayerLost(player, wordHandler, MAX_MISTAKES);
            player.setPlaying(false);
        }
    }
}
