package edu.project1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class HangmanPictureHandler {

    private static HangmanPictureHandler hangmanPictureHandler;
    private static final int hangmanPicsAmount = 6;
    private static final String[] hangmanPictures;

    static {
        hangmanPictures = new String[hangmanPicsAmount];
        readTxtFileWithHangmanPictures();
    }

    private static void readTxtFileWithHangmanPictures() {
        StringBuilder picture = new StringBuilder();

        try (FileReader fileReader = new FileReader("src/main/resources/project1/hangman-pictures.txt")) {
            char symbol;
            int symbolCode;
            int index = 0;

            while (index < hangmanPicsAmount && (symbolCode = fileReader.read()) != -1) {
                symbol = (char) symbolCode;
                if (symbol == '$') {
                    hangmanPictures[index++] = picture.toString();
                    picture.setLength(0);
                } else {
                    picture.append(symbol);
                }
            }

        } catch (IOException exception) {
            Arrays.fill(hangmanPictures, "");
        }
    }

    public String getHangmanPicture(int amountOfMadeMistakes) {
        return switch (amountOfMadeMistakes) {
            case 0 -> hangmanPictures[0];
            case 1 -> hangmanPictures[1];
            case 2 -> hangmanPictures[2];
            case 3 -> hangmanPictures[3];
            case 4 -> hangmanPictures[4];
            case 5 -> hangmanPictures[5];

            default -> "Undefined argument.";
        };
    }

    public static HangmanPictureHandler getInstance () {
        if (hangmanPictureHandler == null) {
            hangmanPictureHandler = new HangmanPictureHandler();
        }

        return hangmanPictureHandler;
    }

    private HangmanPictureHandler() {
    }
}
