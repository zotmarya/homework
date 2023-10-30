package edu.project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Maze {
    private final char WALL = '█';
    private final char STEP = '◦';
    private final char EMPTY_BLOCK = ' ';
    private static final Random RANDOM = new Random();

    private int[] startPoint = new int[2];
    private int[] endPoint = new int[2];

    private int height;
    private int width;
    private int horizontalCellsAmount;
    private int verticalCellsAmount;

    private char[][] maze;

    public Maze(int horizontalCellsAmount, int verticalCellsAmount) {

        setSize(horizontalCellsAmount, verticalCellsAmount);
        maze = new char[height][width];
        fillMaze();
        setStartAndEndPoints();
        createEntrancePoint();
        createExitPoint();
    }

    private void createEntrancePoint() {
        createBlankPoint(startPoint);
    }

    private void createExitPoint() {
        createBlankPoint(endPoint);
    }

    private void createBlankPoint(int[] startPoint) {
        if (startPoint[0] > 0 && startPoint[0] < width - 1) {
            if (startPoint[1] - 1 == 0) {
                maze[0][startPoint[0]] = STEP;
            } else {
                maze[height - 1][startPoint[0]] = STEP;
            }
        } else {
            if (startPoint[0] - 1 == 0) {
                maze[startPoint[1]][0] = STEP;
            } else {
                maze[startPoint[1]][width - 1] = STEP;
            }
        }
    }

    private static final ArrayList<Integer[]> POINTS = new ArrayList<>(
        List.of(new Integer[] {2, 0}, new Integer[] {-2, 0}, new Integer[] {0, 2}, new Integer[] {0, -2}));

    public void createMaze1() {
        createMazeRecursiveBacktracking(startPoint);
    }

    private void createMazeRecursiveBacktracking(int[] point) {
        int[] newPoint;
        Integer[] move;
        ArrayList<Integer[]> points = (ArrayList<Integer[]>) POINTS.clone();

        Collections.shuffle(points, RANDOM);

        if (point[0] == 35 && point[1] == 9) {
            System.out.println();
        }

        for (int i = 0, size = points.size(); i < size; i++) {
            newPoint = point.clone();

            move = points.get(i);

            newPoint[0] += move[0];
            newPoint[1] += move[1];

            if (newPoint[0] <= width - 1 && newPoint[0] > 0
                && newPoint[1] <= height - 1 && newPoint[1] > 0
                && maze[newPoint[1]][newPoint[0]] != EMPTY_BLOCK) {

                removeBlocks(newPoint[0] - move[0] / 2, newPoint[1] - move[1] / 2);
                removeBlocks(newPoint[0], newPoint[1]);
                createMazeRecursiveBacktracking(newPoint);
            }
        }
    }

    private void removeBlocks(int x, int y) {
        maze[y][x] = EMPTY_BLOCK;
    }

    private void setSize(int horizontalCellsAmount, int verticalCellsAmount) {
        this.horizontalCellsAmount = horizontalCellsAmount;
        this.verticalCellsAmount = verticalCellsAmount;
        width = horizontalCellsAmount * 2 + 1;
        height = verticalCellsAmount * 2 + 1;
    }

    private void setStartAndEndPoints() {
        startPoint = generatePoint();
        while (Arrays.equals(endPoint = generatePoint(), startPoint)) ;

        maze[startPoint[1]][startPoint[0]] = EMPTY_BLOCK;
    }

    private int[] generatePoint() {
        int x;
        x = RANDOM.nextInt(0, horizontalCellsAmount) * 2 + 1;

        int y;

        if (x == 0 || x == width - 1) {
            y = RANDOM.nextInt(0, verticalCellsAmount) * 2 + 1;
        } else {
            y = RANDOM.nextInt(2) * (verticalCellsAmount - 1) * 2 + 1;
        }

        return new int[] {x, y};
    }

    private void fillMaze() {
        for (int i = 0; i < height; i++) {
            Arrays.fill(maze[i], WALL);
        }
    }

    public char[][] getMaze() {
        return maze;
    }

    private void solveMazeDepthFirstSearch() {
        pathToExit = new LinkedList<>();
        pathToExit.add(startPoint);
        findDepthFirstWay(startPoint, endPoint, pathToExit);
        //fillPathToExit();
    }

    private boolean inMaze(int x, int y) {
        return x <= width - 1 && x > 0 && y <= height - 1 && y > 0;
    }

    private boolean findDepthFirstWay(int[] current, int[] target, LinkedList<int[]> way) {
        // Ставим след, чтобы не зациклится
        maze[current[1]][current[0]] = '!';

        if (current[0] == target[0] && current[1] == target[1]) {
            way.addFirst(current.clone());
            return true;
        }

        for (int i = 0, size = POINTS_FOR_SEARCH.size(); i < size; i++) {

            int[] next = current.clone();
            var move = POINTS_FOR_SEARCH.get(i);
            next[0] += move[0];
            next[1] += move[1];

            if (inMaze(next[0], next[1])
                && maze[next[1]][next[0]] == EMPTY_BLOCK) {
                if (findDepthFirstWay(next, target, way)) {
                    way.addFirst(current.clone());
                    return true;
                }
            }
        }

        maze[current[1]][current[0]] = EMPTY_BLOCK;
        return false;
    }

    private void fillPathToExit() {
        for (int i = 0, size = pathToExit.size(); i < size; i++) {
            int[] point = pathToExit.get(i);
            maze[point[1]][point[0]] = STEP;
        }
    }

    private LinkedList<int[]> pathToExit;
    private static final ArrayList<Integer[]> POINTS_FOR_SEARCH = new ArrayList<>(
        List.of(new Integer[] {1, 0}, new Integer[] {-1, 0}, new Integer[] {0, 1}, new Integer[] {0, -1}));

    public static void main(String[] args) {
        Maze mazeHandler = new Maze(50, 15);

        mazeHandler.createMaze1();
        mazeHandler.solveMazeDepthFirstSearch();

        for (char[] array : mazeHandler.getMaze()) {
            for (char symbol : array) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
