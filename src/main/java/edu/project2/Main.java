package edu.project2;

import java.io.File;
import java.util.LinkedList;

public class Main {
    private static final int SEED_DEFAULT = -1;
    private static final String PATH_TO_FILE = "src\\main\\resources\\project2\\";
    private static LinkedList<Point> wayToExit;
    private static Menu menu = new Menu();
    private static UserInputHandler inputHandler = new UserInputHandler();
    private static MazeGenerator mazeGenerator = new MazeGenerator();
    private static MazeSolver mazeSolver = new MazeSolver();
    private static MazeView mazeView = new MazeView();
    private static Maze maze = null;
    private static int actionChoice;
    private static int optionChoice = -1;
    private static int horizontalCellsAmount;
    private static int verticalCellsAmount;

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        while (true) {
            wayToExit = new LinkedList<>();

            if (maze == null) {
                menu.printMenuBeforeMazeGeneration();
            } else {
                menu.printMenuAfterMazeGeneration();
            }

            menu.askForInput();
            actionChoice = inputHandler.makeIntChoice();
            if (maze == null) {
                menu.userMenuChoiceBeforeMazeGen(actionChoice);
            } else {
                menu.userMenuChoiceAfterMazeGeneration(actionChoice);
            }

            if (maze == null && actionChoice == 3) {
                actionChoice = -1;
            }

            if (actionChoice > 0 && actionChoice != 2) {
                menu.askForInput();
                optionChoice = inputHandler.makeIntChoice();
            }

            switch (actionChoice) {
                case 1 -> chooseMazeGeneration();

                case 2 -> readFromFileChoice();

                case 3 -> chooseMazeSearch();

                default -> {
                }
            }
        }
    }

    private static void chooseMazeGeneration() {
        menu.askForHeightOfMaze();
        verticalCellsAmount = inputHandler.makeIntChoice();

        menu.askForWidthOfMaze();
        horizontalCellsAmount = inputHandler.makeIntChoice();

        Maze tmpMaze;

        switch (optionChoice) {
            case 1 -> {
                tmpMaze = mazeGenerator.createMazeRB(horizontalCellsAmount, verticalCellsAmount, SEED_DEFAULT);
                maze = tmpMaze;
            }
            case 2 -> {
                tmpMaze = mazeGenerator.createMazeEA(horizontalCellsAmount, verticalCellsAmount, SEED_DEFAULT);
                maze = tmpMaze;
            }
            default -> menu.printInvalidInput();
        }

        mazeView.printMaze(maze, wayToExit);
    }

    private static void chooseMazeSearch() {
        switch (optionChoice) {
            case 1 -> {
                mazeSolver.findDFWay(maze, maze.getStartPoint(), maze.getEndPoint(), wayToExit);
                mazeView.printMaze(maze, wayToExit);
            }
            case 2 -> {
                mazeSolver.findBFWay(maze, maze.getStartPoint(), maze.getEndPoint(), wayToExit);
                mazeView.printMaze(maze, wayToExit);
            }
            default -> {
            }
        }
    }

    private static void readFromFileChoice() {
        File file = new File(PATH_TO_FILE + inputHandler.inputFileName());

        if (file.exists() && file.getName().endsWith(".txt")) {
            maze = Maze.getInstanceFromFile(file);
            if (maze != null) {
                mazeView.printMaze(maze, wayToExit);
            } else {
                menu.printFileHasInvalidMaze();
            }
        } else {
            menu.printIncorrectFileFormat();
        }
    }

    private Main() {
    }
}
