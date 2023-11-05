package edu.project2;

import java.util.Scanner;

public class UserInputHandler {
    private static final Scanner SCANNER = new Scanner(System.in);

    public int makeIntChoice() {
        int choice = -1;

        if (SCANNER.hasNextInt()) {
            choice = SCANNER.nextInt();
        }

        return choice;
    }

    public String inputFileName() {
        SCANNER.nextLine();

        String fileName = SCANNER.nextLine().trim();

        return fileName;
    }
}
