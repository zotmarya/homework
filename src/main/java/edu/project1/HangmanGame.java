package edu.project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {

    static final ArrayList<String> words = new ArrayList<>(Arrays.asList("AMAZING", "CAT"));
    static final int wordsAmount = 2;

    static int mistakesMade = 0;
    static final int maxMistakes = 5;

    static int guessedAmount = 0;

    public static void main(String[] args) {
        HangmanPicture hangmanPictureGenerator = new HangmanPicture();
        Random random = new Random();
        WordHandler wordHandler = new WordHandler(words.get(random.nextInt(wordsAmount)));

        Scanner scanner = new Scanner(System.in);

        char yourLetter;

        while (true) {
            System.out.println("____________________________________________________");
            System.out.println(hangmanPictureGenerator.getHangmanPicture(mistakesMade));
            System.out.println(wordHandler.getGuessedLetters());
            System.out.println("GUESS A LETTER: ");

            while (!scanner.hasNext("[a-z]|[A-Z]")) {
                System.out.println("WRITE ENGLISH LETTERS.");
                scanner.nextLine();
            }

            yourLetter = scanner.nextLine().toUpperCase().charAt(0);

            String result = wordHandler.checkIfGuessedLetter(yourLetter);

            System.out.println(result);

            if (result.charAt(0) == 'N') {
                mistakesMade++;

                if(mistakesMade ==  maxMistakes) {
                    System.out.println(hangmanPictureGenerator.getHangmanPicture(mistakesMade));
                    System.out.println(youLost());

                    return;
                }
            } else if (result.charAt(0) == 'C') {
                guessedAmount++;

                if(guessedAmount == wordHandler.getWordLen()) {
                    System.out.println(wordHandler.getGuessedLetters());
                    System.out.println(youWon());

                    return;
                }
            }

        }

    }

    public static String youWon() {
        return "YOU WON!";
    }

    public static String youLost() {
        return "YOU LOST!";
    }

}
