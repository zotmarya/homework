package edu.project1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class HangmanPictureHandler {

    private static HangmanPictureHandler hangmanPictureHandler;
    public static final int WIN_INDEX = 6;
    private static final int HANGMAN_PICS_AMOUNT = 7;
    private static final String[] HANGMAN_PICTURES;

    static {
        HANGMAN_PICTURES = new String[HANGMAN_PICS_AMOUNT];
        readTxtFileWithHangmanPictures();
    }

    private static void readTxtFileWithHangmanPictures() {
        StringBuilder picture = new StringBuilder();

        try (FileReader fileReader = new FileReader("src/main/resources/project1/hangman-pictures.txt")) {
            char symbol;
            int symbolCode;
            int index = 0;

            while (index < HANGMAN_PICS_AMOUNT && (symbolCode = fileReader.read()) != -1) {
                symbol = (char) symbolCode;
                if (symbol == '$') {
                    HANGMAN_PICTURES[index++] = picture.toString();
                    picture.setLength(0);
                } else {
                    picture.append(symbol);
                }
            }

        } catch (IOException exception) {
            Arrays.fill(HANGMAN_PICTURES, "");
        }
    }

    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;

    public String getHangmanPicture(int amountOfMadeMistakes) {
        return switch (amountOfMadeMistakes) {
            case 0 -> HANGMAN_PICTURES[0];
            case 1 -> HANGMAN_PICTURES[1];
            case 2 -> HANGMAN_PICTURES[2];
            case THREE -> HANGMAN_PICTURES[THREE];
            case FOUR -> HANGMAN_PICTURES[FOUR];
            case FIVE -> HANGMAN_PICTURES[FIVE];
            case SIX -> HANGMAN_PICTURES[SIX];

            default -> "Undefined argument.";
        };
    }

    public static HangmanPictureHandler getInstance() {
        if (hangmanPictureHandler == null) {
            hangmanPictureHandler = new HangmanPictureHandler();
        }

        return hangmanPictureHandler;
    }

    private HangmanPictureHandler() {
    }
}
