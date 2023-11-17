package edu.project2;

import java.util.LinkedList;

public class MazeSolverDF extends MazeSolver {

    @Override
    public void findWay(Maze maze, Point currentPoint, Point targetPoint, LinkedList<Point> way) {
        prepareMaze(maze);
        findDepthFirstWay(maze, currentPoint, targetPoint, way);
    }

    private boolean findDepthFirstWay(Maze maze, Point currentPoint, Point targetPoint, LinkedList<Point> way) {
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
}
