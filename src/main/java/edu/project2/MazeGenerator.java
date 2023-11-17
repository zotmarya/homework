package edu.project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class MazeGenerator {

    protected static final Random RANDOM = new Random();

    protected static final ArrayList<Point> POINTS = new ArrayList<>(
        List.of(new Point(2, 0), new Point(-2, 0), new Point(0, 2), new Point(0, -2)));

    protected void removeBlocks(Maze maze, int x, int y) {
        maze.setCellInMaze(x, y, Maze.EMPTY_BLOCK);
    }

    private void fillMaze(Maze maze, char symbol) {
        for (int i = 0; i < maze.getHeight(); i++) {
            Arrays.fill(maze.getMaze()[i], symbol);
        }
    }

    private Point generatePoint(Maze maze) {
        int x;
        x = RANDOM.nextInt(0, maze.getHorizontalCellsAmount()) * 2 + 1;

        int y;

        if (x == 0 || x == maze.getWidth() - 1) {
            y = RANDOM.nextInt(0, maze.getVerticalCellsAmount()) * 2 + 1;
        } else {
            y = RANDOM.nextInt(2) * (maze.getVerticalCellsAmount() - 1) * 2 + 1;
        }

        return new Point(x, y);
    }

    protected void createEdgePoints(Maze maze) {
        maze.setStartAndEndPoints(
            createBlankPoint(maze, maze.getStartPoint()),
            createBlankPoint(maze, maze.getEndPoint())
        );
    }

    protected Point createBlankPoint(Maze maze, Point point) {
        Point edgePoint;

        if (point.getX() > 0 && point.getX() < maze.getWidth() - 1) {
            if (point.getY() - 1 == 0) {
                edgePoint = new Point(point.getX(), 0);

                maze.setCellInMaze(edgePoint, Maze.EMPTY_BLOCK);
            } else {
                edgePoint = new Point(point.getX(), maze.getHeight() - 1);

                maze.setCellInMaze(edgePoint, Maze.EMPTY_BLOCK);
            }
        } else {
            if (point.getX() - 1 == 0) {
                edgePoint = new Point(0, point.getY());

                maze.setCellInMaze(edgePoint, Maze.EMPTY_BLOCK);
            } else {
                edgePoint = new Point(maze.getWidth() - 1, point.getY());

                maze.setCellInMaze(edgePoint, Maze.EMPTY_BLOCK);
            }
        }

        return edgePoint;
    }

    protected Maze createMazePreparation(int horizontalCellsAmount, int verticalCellsAmount, char symbol) {
        Maze maze = new Maze(horizontalCellsAmount, verticalCellsAmount);

        fillMaze(maze, symbol);

        Point startPoint = generatePoint(maze);
        Point endPoint;

        do {
            endPoint = generatePoint(maze);
        } while (endPoint == startPoint);

        maze.setStartAndEndPoints(startPoint, endPoint);

        return maze;
    }

    public abstract Maze createMaze(int horizontalCellsAmount, int verticalCellsAmount, int seedRandom);
}
