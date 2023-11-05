package edu.project2;

@SuppressWarnings({"RegexpSinglelineJava", "MagicNumber"})
public class Menu {

    public void printMenuBeforeMazeGeneration() {
        System.out.println("""

                            MAZE GENERATOR AND SOLVER
                            1 - Generate maze
                            2 - Load maze from file
                            3 - Exit
            """);
    }

    public void userMenuChoiceBeforeMazeGen(int choice) {
        switch (choice) {
            case 1 -> printMazeGenMenu();
            case 2 -> printReadMazeFromFile();
            case 3 -> {
                printBye();
                System.exit(0);
            }
            default -> printInvalidInput();
        }
    }

    private void printBye() {
        System.out.println("Bye!");
    }

    public void printInvalidInput() {
        System.out.println("Invalid input.");
    }

    public void printMenuAfterMazeGeneration() {
        System.out.println("""

                            MAZE GENERATOR AND SOLVER
                            1 - Generate Maze
                            2 - Load Maze from file
                            3 - Solve Maze
                            4 - Exit
            """);
    }

    public void userMenuChoiceAfterMazeGeneration(int choice) {
        switch (choice) {
            case 1 -> printMazeGenMenu();
            case 2 -> printReadMazeFromFile();
            case 3 -> printMazeSolveMenu();
            case 4 -> {
                printBye();
                System.exit(0);
            }
            default -> printInvalidInput();
        }
    }

    public void printFileHasInvalidMaze() {
        System.out.println("File has an invalid maze format.");
    }

    public void askForInput() {
        System.out.print("Input your choice: ");
    }

    public void askForHeightOfMaze() {
        System.out.print("Input maze height (range: 3 - 60): ");
    }

    public void askForWidthOfMaze() {
        System.out.print("Input maze width (range: 3 - 60): ");
    }

    private void printMazeGenMenu() {
        System.out.println("""

                            GENERATE MAZE:
                            1 - Recursive Backtracking Method
                            2 - Eller Algorithm Method
            """);
    }

    private void printMazeSolveMenu() {
        System.out.println("""

                            SOLVE MAZE:
                            1 - Depth First Search
                            2 - Breadth First Search
            """);
    }

    public void printReadMazeFromFile() {
        System.out.print("""

            (File should contain A (start point) and B (end point) letters)
            Write name of file with the maze to read:  """);
    }

    public void printIncorrectFileFormat() {
        System.out.println("Incorrect file or no file found.");
    }

}

