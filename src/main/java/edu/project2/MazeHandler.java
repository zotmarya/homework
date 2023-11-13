package edu.project2;

import java.io.File;
import java.util.LinkedList;

public class MazeHandler {
    private static final int SEED_DEFAULT = -1;
    private static final String PATH_TO_FILE = "src/main/resources/project2/";
    private LinkedList<Point> wayToExit;
    private final Menu menu = new Menu();
    private final UserInputHandler inputHandler = new UserInputHandler();
    private final MazeGenerator mazeGeneratorRB = new MazeGeneratorRB();
    private final MazeGenerator mazeGeneratorEA = new MazeGeneratorEA();
    private final MazeSolver mazeSolverBF = new MazeSolverBF();
    private final MazeSolver mazeSolverDF = new MazeSolverDF();
    private final MazeView mazeView = new MazeView();
    private Maze maze = null;
    private int actionChoice;
    private int optionChoice = -1;

    public void setWayToExit(LinkedList<Point> wayToExit) {
        this.wayToExit = wayToExit;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getActionChoice() {
        return actionChoice;
    }

    public void setActionChoice(int actionChoice) {
        this.actionChoice = actionChoice;
    }

    public UserInputHandler getInputHandler() {
        return inputHandler;
    }

    public void setOptionChoice(int optionChoice) {
        this.optionChoice = optionChoice;
    }

    public void chooseMazeGeneration() {
        menu.askForHeightOfMaze();
        int verticalCellsAmount = inputHandler.makeIntChoice();

        menu.askForWidthOfMaze();
        int horizontalCellsAmount = inputHandler.makeIntChoice();

        Maze tmpMaze;

        switch (optionChoice) {
            case 1 -> {
                tmpMaze = mazeGeneratorRB.createMaze(horizontalCellsAmount, verticalCellsAmount, SEED_DEFAULT);
                maze = tmpMaze;
            }
            case 2 -> {
                tmpMaze = mazeGeneratorEA.createMaze(horizontalCellsAmount, verticalCellsAmount, SEED_DEFAULT);
                maze = tmpMaze;
            }
            default -> menu.printInvalidInput();
        }

        mazeView.printMaze(maze, wayToExit);
    }

    public void chooseMazeSearch() {
        switch (optionChoice) {
            case 1 -> {
                mazeSolverDF.findWay(maze, maze.getStartPoint(), maze.getEndPoint(), wayToExit);
                mazeView.printMaze(maze, wayToExit);
            }
            case 2 -> {
                mazeSolverBF.findWay(maze, maze.getStartPoint(), maze.getEndPoint(), wayToExit);
                mazeView.printMaze(maze, wayToExit);
            }
            default -> {
            }
        }
    }

    public void readFromFileChoice() {
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
}
