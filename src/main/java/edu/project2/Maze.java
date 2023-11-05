package edu.project2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final int DEFAULT_MAZE_SIZE = 3;
    public static final int MAX_MAZE_SIZE = 60;
    public static final char WALL = '█';
    public static final char ENTRANCE = '●';
    public static final char EXIT = '○';
    public static final char EMPTY_BLOCK = ' ';
    private Point startPoint;
    private Point endPoint;
    private int height;
    private int width;
    private int horizontalCellsAmount;
    private int verticalCellsAmount;
    private char[][] maze;

    private void setSize(int horizontalCellsAmount, int verticalCellsAmount) {
        int horizValue = horizontalCellsAmount;
        int vertValue = verticalCellsAmount;

        if (!isValidSize(horizontalCellsAmount, verticalCellsAmount)) {
            horizValue = DEFAULT_MAZE_SIZE;
            vertValue = DEFAULT_MAZE_SIZE;
        }

        this.horizontalCellsAmount = horizValue;
        this.verticalCellsAmount = vertValue;
        setWidthAndHeight(horizValue * 2 + 1, vertValue * 2 + 1);
    }

    private boolean isValidSize(int horizontalCellsAmount, int verticalCellsAmount) {
        return horizontalCellsAmount >= DEFAULT_MAZE_SIZE && verticalCellsAmount >= DEFAULT_MAZE_SIZE
            && horizontalCellsAmount <= MAX_MAZE_SIZE
            && verticalCellsAmount <= MAX_MAZE_SIZE;
    }

    public void setWidthAndHeight(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public void setStartAndEndPoints(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public char[][] getMaze() {
        return maze;
    }

    public boolean isInMaze(int x, int y) {
        return x <= width - 1 && x >= 0 && y <= height - 1 && y >= 0;
    }

    public boolean isInMaze(Point point) {
        return isInMaze(point.getX(), point.getY());
    }

    public Maze(int horizontalCellsAmount, int verticalCellsAmount) {
        setSize(horizontalCellsAmount, verticalCellsAmount);
        maze = new char[height][width];
    }

    private Maze() {
    }

    public static Maze getInstanceFromFile(File file) {
        Maze maze = new Maze();
        if (!file.exists() || !maze.readMazeFromFile(file)
            || !maze.isValidSize((maze.getWidth() - 1) / 2, (maze.getHeight() - 1) / 2)) {
            maze = null;
        }

        return maze;
    }

    private boolean readMazeFromFile(File file) {

        int lettersAmount = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder mazeFromFile = new StringBuilder();
            String line;
            int mazeWidth = -1;
            int mazeHeight = 0;

            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();

                if (mazeWidth == -1) {
                    mazeWidth = line.length();
                } else if (mazeWidth != line.length()) {
                    return false;
                }
                mazeHeight++;

                mazeFromFile.append(line);
            }

            setWidthAndHeight(mazeWidth, mazeHeight);
            maze = new char[mazeHeight][mazeWidth];

            char[] mazeCopy = mazeFromFile.toString().toCharArray();

            for (int i = 0, y = 0, x = 0; i < mazeCopy.length; i++) {
                if (!isSymbolValid(mazeCopy[i])) {
                    return false;
                }

                if (mazeCopy[i] == 'A') {
                    lettersAmount++;
                    startPoint = new Point(x, y);
                    maze[y][x++] = EMPTY_BLOCK;
                } else if (mazeCopy[i] == 'B') {
                    lettersAmount++;
                    endPoint = new Point(x, y);
                    maze[y][x++] = EMPTY_BLOCK;
                } else {
                    maze[y][x++] = mazeCopy[i];
                }

                if ((i + 1) % width == 0) {
                    y++;
                    x = 0;
                }
            }
        } catch (IOException exception) {
            LOGGER.info(exception);
            System.exit(-1);
        }

        return lettersAmount == 2;
    }

    private boolean isSymbolValid(char symbol) {
        return symbol == WALL || symbol == 'A' || symbol == 'B' || symbol == EMPTY_BLOCK;
    }

    public int getHorizontalCellsAmount() {
        return horizontalCellsAmount;
    }

    public int getVerticalCellsAmount() {
        return verticalCellsAmount;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public char getCellInMaze(Point point) {
        return getCellInMaze(point.getX(), point.getY());
    }

    public char getCellInMaze(int x, int y) {
        return maze[y][x];
    }

    public void setCellInMaze(Point point, char cell) {
        setCellInMaze(point.getX(), point.getY(), cell);
    }

    public void setCellInMaze(int x, int y, char cell) {
        maze[y][x] = cell;
    }

}
