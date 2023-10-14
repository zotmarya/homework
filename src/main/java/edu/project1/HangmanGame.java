package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {

    private static final ArrayList<String> WORDS = new ArrayList<>(Arrays.asList("AMAZING", "CAT", "POWER"));
    private static final int WORDS_AMOUNT = 2;

    private static final int MAX_MISTAKES = 5;

    private static int mistakesMade = 0;

    private static int guessedAmount = 0;

    private static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        HangmanPicture hangmanPictureGenerator = new HangmanPicture();
        Random random = new Random();
        WordHandler wordHandler = new WordHandler(WORDS.get(random.nextInt(WORDS_AMOUNT)));

        Scanner scanner = new Scanner(System.in);

        char yourLetter;

        while (true) {
            LOGGER.info(hangmanPictureGenerator.getHangmanPicture(mistakesMade));
            LOGGER.info(wordHandler.getGuessedLettersString());
            LOGGER.info("GUESS A LETTER: ");

            while (!scanner.hasNext("[a-z]|[A-Z]")) {
                LOGGER.info("WRITE ENGLISH LETTERS.");
                scanner.nextLine();
            }

            yourLetter = scanner.nextLine().toUpperCase().charAt(0);

            String result = wordHandler.checkIfGuessedLetter(yourLetter);

            LOGGER.info(result);

            if (result.charAt(0) == 'N') {
                mistakesMade++;

                if (mistakesMade == MAX_MISTAKES) {
                    LOGGER.info(hangmanPictureGenerator.getHangmanPicture(mistakesMade));
                    LOGGER.info(youLost());

                    return;
                }
            } else if (result.charAt(0) == 'C') {
                guessedAmount++;

                if (guessedAmount == wordHandler.getWordLen()) {
                    LOGGER.info(wordHandler.getGuessedLetters());
                    LOGGER.info(youWon());

                    return;
                }
            }

        }

    }

    private static String youWon() {
        return "YOU WON!🥳";
    }

    private static String youLost() {
        return "YOU LOST!😦";
    }

}
