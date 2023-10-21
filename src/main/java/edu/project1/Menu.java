package edu.project1;

public class Menu {
    private static Menu menu;

    public static Menu getInstance() {
        if (menu == null) {
            menu = new Menu();
        }

        return menu;
    }

    public String outputMenu() {
        return """


                        HANGMAN (ENGLISH VERSION)
                           1 - PLAY HANGMAN
                           2 - EXIT

            """;
    }

    public String askForPlayerChoice() {
        return "YOUR CHOICE: ";
    }

    private Menu() {
    }
}
