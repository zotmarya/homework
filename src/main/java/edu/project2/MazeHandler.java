package edu.project2;

import java.io.File;
import java.util.LinkedList;

public class MazeHandler {
    private static final int SEED_DEFAULT = -1;
    private static final String PATH_TO_FILE = "src/main/resources/project2/";
    private LinkedList<Point> wayToExit;
    private Menu menu = new Menu();
    private UserInputHandler inputHandler = new UserInputHandler();
    private MazeGenerator mazeGeneratorRB = new MazeGeneratorRB();
    private MazeGenerator mazeGeneratorEA = new MazeGeneratorEA();
    private MazeSolver mazeSolverBF = new MazeSolverBF();
    private MazeSolver mazeSolverDF = new MazeSolverDF();
    private MazeView mazeView = new MazeView();
    private Maze maze = null;
    private int actionChoice;
    private int optionChoice = -1;
    private int horizontalCellsAmount;
    private int verticalCellsAmount;

    public LinkedList<Point> getWayToExit() {
        return wayToExit;
    }

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

    public int getOptionChoice() {
        return optionChoice;
    }

    public void setOptionChoice(int optionChoice) {
        this.optionChoice = optionChoice;
    }

    public void chooseMazeGeneration() {
        menu.askForHeightOfMaze();
        verticalCellsAmount = inputHandler.makeIntChoice();

        menu.askForWidthOfMaze();
        horizontalCellsAmount = inputHandler.makeIntChoice();

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
