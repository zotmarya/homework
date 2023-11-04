package edu.project2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MazeSolver {
    private static final ArrayList<Point> POINTS_FOR_SEARCH = new ArrayList<>(
        List.of(new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1)));

    private int[][] systemMaze;

    public void findDFWay(Maze maze, Point currentPoint, Point targetPoint, LinkedList<Point> way) {
        prepareMaze(maze);
        findDepthFirstWay(maze, currentPoint, targetPoint, way);

    }

    public void prepareMaze(Maze maze) {
        systemMaze = new int[maze.getHeight()][maze.getWidth()];
    }

    private void setSystemMazeValue(Point point, int value) {
        systemMaze[point.getY()][point.getX()] = value;
    }

    private int getSystemMazeValue(Point point) {
        return systemMaze[point.getY()][point.getX()];
    }

    private boolean findDepthFirstWay(Maze maze, Point currentPoint, Point targetPoint, LinkedList<Point> way) {
        // Ставим след, чтобы не зациклится
        setSystemMazeValue(currentPoint, 1);

        if (currentPoint.equals(targetPoint)) {
            way.addFirst(currentPoint.getCopy());
            return true;
        }

        for (int i = 0, size = POINTS_FOR_SEARCH.size(); i < size; i++) {

            Point nextPoint = currentPoint.getCopy();
            nextPoint.add(POINTS_FOR_SEARCH.get(i));

            if (maze.isInMaze(nextPoint)
                && getSystemMazeValue(nextPoint) == 0
                && maze.getCellInMaze(nextPoint) == Maze.EMPTY_BLOCK) {
                if (findDepthFirstWay(maze, nextPoint, targetPoint, way)) {
                    way.addFirst(currentPoint.getCopy());
                    return true;
                }
            }
        }

        setSystemMazeValue(currentPoint, 0);
        return false;
    }

    public void findBFWay(Maze maze, Point currentPoint, Point targetPoint, LinkedList<Point> way) {
        prepareMaze(maze);
        findBreadthFirstWay(maze, currentPoint, targetPoint, way);

    }

    private boolean findBreadthFirstWay(Maze maze, Point currentPoint, Point targetPoint, LinkedList<Point> way) {
        ArrayDeque<Point> points = new ArrayDeque<>();
        points.add(currentPoint);

        setSystemMazeValue(currentPoint, 1);

        boolean hasFoundWay = false;

        while (!points.isEmpty()) {
            Point point = points.pollFirst();

            if(point.equals(targetPoint)) {
                hasFoundWay = true;
                break;
            }

            int stepNumber = getSystemMazeValue(point) + 1;

            for (int i = 0, size = POINTS_FOR_SEARCH.size(); i < size; i++) {
                Point nextPoint = point.getCopy();
                nextPoint.add(POINTS_FOR_SEARCH.get(i));

                if (maze.isInMaze(nextPoint)
                    && getSystemMazeValue(nextPoint) == 0
                    && maze.getCellInMaze(nextPoint) == Maze.EMPTY_BLOCK) {
                    points.add(nextPoint);
                    setSystemMazeValue(nextPoint, stepNumber);
                }
            }
        }

        if(hasFoundWay) {
            extractWay(maze, targetPoint, way);
        }

        return hasFoundWay;
    }

    private void extractWay(Maze maze, Point point, List<Point> way) {

        way.addFirst(point);
        int pointMazeValue = getSystemMazeValue(point);
        if (pointMazeValue == 1) return;

        for (int i = 0, size = POINTS_FOR_SEARCH.size(); i < size; i++) {
            Point nextPoint = point.getCopy();
            nextPoint.add(POINTS_FOR_SEARCH.get(i));
            if (maze.isInMaze(nextPoint) && pointMazeValue - getSystemMazeValue(nextPoint) == 1) {
                extractWay(maze, nextPoint, way);
                return;
            }
        }
    }

}
