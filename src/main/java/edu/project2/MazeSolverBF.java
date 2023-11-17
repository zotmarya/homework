package edu.project2;

import java.util.ArrayDeque;
import java.util.LinkedList;

public class MazeSolverBF extends MazeSolver {

    @Override
    public void findWay(Maze maze, Point currentPoint, Point targetPoint, LinkedList<Point> way) {
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

            if (point.equals(targetPoint)) {
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

        if (hasFoundWay) {
            extractWay(maze, targetPoint, way);
        }

        return hasFoundWay;
    }
}
