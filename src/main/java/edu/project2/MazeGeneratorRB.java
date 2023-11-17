package edu.project2;

import java.util.ArrayList;
import java.util.Collections;

public class MazeGeneratorRB extends MazeGenerator {

    @Override
    public Maze createMaze(int horizontalCellsAmount, int verticalCellsAmount, int seedRandom) {
        if (seedRandom != -1) {
            RANDOM.setSeed(seedRandom);
        }

        Maze maze = createMazePreparation(horizontalCellsAmount, verticalCellsAmount, Maze.WALL);

        createMazeRecursiveBacktracking(maze, maze.getStartPoint());

        createEdgePoints(maze);

        return maze;
    }

    private void createMazeRecursiveBacktracking(Maze maze, Point point) {
        Point newPoint;
        Point move;
        ArrayList<Point> points = new ArrayList<>(POINTS);

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
}
