package edu.project2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Maze {
    public static final char WALL = '█';
    public static final char ENTRANCE = '○';
    public static final char EXIT = '●';
    public static final char BEGINNING = '●';
    public static final char EMPTY_BLOCK = ' ';
    public static final char STEP = '!';

    private static final Random RANDOM = new Random();

    private Point startPoint;
    private Point endPoint;
    private int height;
    private int width;
    private int horizontalCellsAmount;
    private int verticalCellsAmount;

    private char[][] maze;

    private void setSize(int horizontalCellsAmount, int verticalCellsAmount) {
        this.horizontalCellsAmount = horizontalCellsAmount;
        this.verticalCellsAmount = verticalCellsAmount;
        width = horizontalCellsAmount * 2 + 1;
        height = verticalCellsAmount * 2 + 1;
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

    public Maze(
        int horizontalCellsAmount,
        int verticalCellsAmount
    ) {
        setSize(horizontalCellsAmount, verticalCellsAmount);
        maze = new char[height][width];
    }

    public Maze(File file) {

        readMazeFromFile(file);

    }

    private void readMazeFromFile(File file) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder mazeFromFile = new StringBuilder();
            String line;
            int mazeWidth = -1;
            int mazeHeight = 0;

            while ((line = bufferedReader.readLine()) != null) {
                if (mazeWidth == -1) {
                    mazeWidth = line.length();
                }
                mazeHeight++;

                mazeFromFile.append(line);
            }

            setWidthAndHeight(mazeWidth, mazeHeight);
            maze = new char[mazeHeight][mazeWidth];

            char[] mazeCopy = mazeFromFile.toString().toCharArray();

            for (int i = 0, y = 0, x = 0; i < mazeCopy.length; i++) {
                if (mazeCopy[i] == 'A') {
                    startPoint = new Point(x, y);
                    maze[y][x++] = EMPTY_BLOCK;
                } else if (mazeCopy[i] == 'B') {
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
        }
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

    public void setCellInMaze(Point point, char cell) {
        setCellInMaze(point.getX(), point.getY(), cell);
    }

    public void setCellInMaze(int x, int y, char cell) {
        maze[y][x] = cell;
    }

    public char getCellInMaze(Point point) {
        return getCellInMaze(point.getX(), point.getY());
    }

    public char getCellInMaze(int x, int y) {
        return maze[y][x];
    }

}
