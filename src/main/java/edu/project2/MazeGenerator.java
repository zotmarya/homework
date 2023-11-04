package edu.project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MazeGenerator {

    private static final Random RANDOM = new Random();

    private static final ArrayList<Point> POINTS = new ArrayList<>(
        List.of(new Point(2, 0), new Point(-2, 0), new Point(0, 2), new Point(0, -2)));

    public MazeGenerator() {
    }

    public Maze createMazeRB(int horizontalCellsAmount, int verticalCellsAmount, int seedRandom) {
        RANDOM.setSeed(seedRandom);

        Maze maze = createMaze(horizontalCellsAmount, verticalCellsAmount);

        createMazeRecursiveBacktracking(maze, maze.getStartPoint());

        createEdgePoints(maze);

        return maze;
    }

    private void createMazeRecursiveBacktracking(Maze maze, Point point) {
        Point newPoint;
        Point move;
        ArrayList<Point> points = (ArrayList<Point>) POINTS.clone();

        Collections.shuffle(points, RANDOM);

        for (int i = 0, size = points.size(); i < size; i++) {
            newPoint = point.getCopy();

            move = points.get(i);

            newPoint.add(move);

            if (maze.isInMaze(newPoint)
                && maze.getCellInMaze(newPoint) != Maze.EMPTY_BLOCK) {

                removeBlocks(maze, newPoint.getX() - move.getX() / 2, newPoint.getY() - move.getY() / 2);
                removeBlocks(maze, newPoint.getX(), newPoint.getY());
                createMazeRecursiveBacktracking(maze, newPoint);
            }
        }
    }

    private void removeBlocks(Maze maze, int x, int y) {
        maze.setCellInMaze(x, y, Maze.EMPTY_BLOCK);
    }

    private Maze createMaze(int horizontalCellsAmount, int verticalCellsAmount) {
        Maze maze = new Maze(horizontalCellsAmount, verticalCellsAmount);
        fillMaze(maze);

        Point startPoint = generatePoint(maze);
        Point endPoint;
        while ((endPoint = generatePoint(maze)) == startPoint) ;
        maze.setStartAndEndPoints(startPoint, endPoint);

        return maze;
    }

    private void fillMaze(Maze maze) {
        for (int i = 0; i < maze.getHeight(); i++) {
            Arrays.fill(maze.getMaze()[i], Maze.WALL);
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

    private void createEdgePoints(Maze maze) {
//        createBlankPoint(maze, maze.getStartPoint());
 //       createBlankPoint(maze, maze.getEndPoint());

        maze.setStartAndEndPoints(
            createBlankPoint(maze, maze.getStartPoint()),
            createBlankPoint(maze, maze.getEndPoint())
        );
    }

    private Point createBlankPoint(Maze maze, Point point) {
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
}
