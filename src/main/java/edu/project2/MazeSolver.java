package edu.project2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class MazeSolver {
    protected static final ArrayList<Point> POINTS_FOR_SEARCH = new ArrayList<>(
        List.of(new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1)));

    private int[][] systemMaze;

    public void prepareMaze(Maze maze) {
        systemMaze = new int[maze.getHeight()][maze.getWidth()];
    }

    protected void setSystemMazeValue(Point point, int value) {
        systemMaze[point.getY()][point.getX()] = value;
    }

    protected int getSystemMazeValue(Point point) {
        return systemMaze[point.getY()][point.getX()];
    }

    @SuppressWarnings("ReturnCount")
    protected void extractWay(Maze maze, Point point, List<Point> way) {

        way.addFirst(point);
        int pointMazeValue = getSystemMazeValue(point);
        if (pointMazeValue == 1) {
            return;
        }

        for (int i = 0, size = POINTS_FOR_SEARCH.size(); i < size; i++) {
            Point nextPoint = point.getCopy();
            nextPoint.add(POINTS_FOR_SEARCH.get(i));
            if (maze.isInMaze(nextPoint) && pointMazeValue - getSystemMazeValue(nextPoint) == 1) {
                extractWay(maze, nextPoint, way);
                return;
            }
        }
    }

    public abstract void findWay(Maze maze, Point currentPoint, Point targetPoint, LinkedList<Point> way);
}
