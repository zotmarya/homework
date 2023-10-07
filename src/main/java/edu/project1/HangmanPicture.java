package edu.project1;

public class HangmanPicture {

    public String getHangmanPicture (int amountOfMadeMistakes) {
        return switch (amountOfMadeMistakes) {
            case 0 -> hangmanZero;
            case 1 -> hangmanOne;
            case 2 -> hangmanTwo;
            case 3 -> hangmanThree;
            case 4 -> hangmanFour;
            case 5 -> hangmanFive;

            default -> "Undefined argument.";
        };
    }

    private final String hangmanZero =
        """
                       __________HANGMAN__________
                       |                     |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                _______|__________________________
            """;

    private final String hangmanOne =
        """
                       __________HANGMAN__________
                       |                     |
                       |                     |
                       |					 |
                       |                     |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                       |
                _______|__________________________
            """;

    private final String hangmanTwo =
        """

                __________HANGMAN__________
                |                     |
                |                     |
                |					  |
                |                     |____
                |                     | 00|
                |                     |___|
                |
                |
                |
                |
                |
                |
                |
                |
                |
                |
         _______|__________________________
        """;

    private final String hangmanThree =
            """

                       __________HANGMAN__________
                       |                     |
                       |                     |
                       |					 |
                       |                     |____
                       |                     | 00|
                       |                     |___|
                       |					  |
                       |					  |
                       |					  |
                       |					  |
                       |
                       |
                       |
                       |
                       |
                       |
                _______|__________________________
            """;

    private final String hangmanFour =
        """

                       __________HANGMAN__________
                       |                     |
                       |                     |
                       |					 |
                       |                     |____
                       |                     | 00|
                       |                     |___|
                       |					 /|\\
                       |					| | |
                       |					| | |
                       |					  |
                       |
                       |
                       |
                       |
                       |
                       |
                _______|__________________________
            """;

    private final String hangmanFive =
       """

               __________HANGMAN__________
               |                     |
               |                     |
               |					  |
               |                     |____
               |                     | __|
               |                     |___|
               |					 /|\\
               |					| | |
               |					| | |
               |					  |
               |					 | |
               |					 | |
               |					 | |
               |					 | |
               |
               |
        _______|__________________________
       """;

}
