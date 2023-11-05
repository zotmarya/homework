package edu.project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MazeGenerator {

    private static final Random RANDOM = new Random();

    private static final ArrayList<Point> POINTS = new ArrayList<>(
        List.of(new Point(2, 0), new Point(-2, 0), new Point(0, 2), new Point(0, -2)));

    public Maze createMazeRB(int horizontalCellsAmount, int verticalCellsAmount, int seedRandom) {
        RANDOM.setSeed(seedRandom);

        Maze maze = createMaze(horizontalCellsAmount, verticalCellsAmount, Maze.WALL);

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

    private void createEdgePoints(Maze maze) {
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

    private Maze createMaze(int horizontalCellsAmount, int verticalCellsAmount, char symbol) {
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

    public Maze createMazeEA(int horizontalCellsAmount, int verticalCellsAmount, int seedRandom) {
        RANDOM.setSeed(seedRandom);

        Maze maze = createMaze(horizontalCellsAmount, verticalCellsAmount, Maze.EMPTY_BLOCK);

        createMazeEllerAlgorithm(maze);

        createEdgePoints(maze);

        return maze;
    }

    public void createMazeEllerAlgorithm(Maze maze) {

        int[] mazeRow = new int[(maze.getWidth() - 1) / 2];
        int rowsAmount = (maze.getHeight() - 1) / 2;
        int setNumber = 1;

        for (int i = 0; i < rowsAmount; i++) {
            // first step
//            for (int j = 0; j < mazeRow.length; j++) {
//                if (mazeRow[j] == 0) {
//                    mazeRow[j] = setNumber++;
//                }
//            }

            setNumber = step1(i, setNumber, mazeRow);

            int y = i * 2 + 1;

            // second step - right walls
//            for (int j = 0; j < mazeRow.length - 1; j++) {
//                if ((mazeRow[j + 1] == mazeRow[j]) || RANDOM.nextBoolean()) {
//                    maze.setCellInMaze(j * 2 + 2, y, Maze.WALL);
//                } else {
//                    // combine sets
//                    int changeSet = mazeRow[j + 1];
//
//                    for (int n = 0; n < mazeRow.length; n++) {
//                        if (mazeRow[n] == changeSet) {
//                            mazeRow[n] = mazeRow[j];
//                        }
//                    }
//                }
//            }
            step2(y, mazeRow, maze);

            // step 3 bottom walls
//            Map<Integer, Integer> map = new HashMap<>();
//            for (int j = 0; j < mazeRow.length; j++) {
//                if (map.containsKey(mazeRow[j])) {
//                    map.put(mazeRow[j], map.get(mazeRow[j]) + 1);
//                } else {
//                    map.put(mazeRow[j], 1);
//                }
//            }
//
//            for (int j = 0; j < mazeRow.length - 1; j++) {
//                if (map.get(mazeRow[j]) > 1) {
//                    if (RANDOM.nextBoolean()) {
//                        maze.setCellInMaze(j * 2 + 1, y + 1, Maze.WALL);
//                        map.put(mazeRow[j], map.get(mazeRow[j]) - 1);
//                    }
//                }
//            }

            step3(y, mazeRow, maze);

            // step 4
//            if (i == rowsAmount - 1) {
//                for (int j = 0; j < mazeRow.length - 1; j++) {
//                    if (mazeRow[j + 1] != mazeRow[j]) {
//                        maze.setCellInMaze(j * 2 + 2, y, Maze.EMPTY_BLOCK);
//
//                        // full merging of two sets
//                        int changeSet = mazeRow[j + 1];
//                        for (int n = 0; n < mazeRow.length; n++) {
//                            if (mazeRow[n] == changeSet) {
//                                mazeRow[n] = mazeRow[j];
//                            }
//                        }
//                    }
//                }
//            } else {
//                for (int j = 0; j < mazeRow.length; j++) {
//                    if (maze.getCellInMaze(j * 2 + 1, y + 1) == Maze.WALL) {
//                        mazeRow[j] = 0;
//                    }
//                }
//            }

            step4(i, y, rowsAmount, mazeRow, maze);
        }
        completeMaze(maze);
    }

    private int step1(int i, int setNumber, int[] mazeRow) {
        int tmpSetNumber = setNumber;

        for (int j = 0; j < mazeRow.length; j++) {
            if (mazeRow[j] == 0) {
                mazeRow[j] = tmpSetNumber++;
            }
        }


        return tmpSetNumber;
    }

    private void step2(int y, int[] mazeRow, Maze maze) {
        for (int j = 0; j < mazeRow.length - 1; j++) {
            if ((mazeRow[j + 1] == mazeRow[j]) || RANDOM.nextBoolean()) {
                maze.setCellInMaze(j * 2 + 2, y, Maze.WALL);
            } else {
                // combine sets
                int changeSet = mazeRow[j + 1];

                for (int n = 0; n < mazeRow.length; n++) {
                    if (mazeRow[n] == changeSet) {
                        mazeRow[n] = mazeRow[j];
                    }
                }
            }
        }
    }

    private void step3(int y, int[] mazeRow, Maze maze) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < mazeRow.length; j++) {
            if (map.containsKey(mazeRow[j])) {
                map.put(mazeRow[j], map.get(mazeRow[j]) + 1);
            } else {
                map.put(mazeRow[j], 1);
            }
        }

        for (int j = 0; j < mazeRow.length - 1; j++) {
            if (map.get(mazeRow[j]) > 1) {
                if (RANDOM.nextBoolean()) {
                    maze.setCellInMaze(j * 2 + 1, y + 1, Maze.WALL);
                    map.put(mazeRow[j], map.get(mazeRow[j]) - 1);
                }
            }
        }
    }

    private void step4(int i, int y, int rowsAmount, int[] mazeRow, Maze maze) {
        if (i == rowsAmount - 1) {
            for (int j = 0; j < mazeRow.length - 1; j++) {
                if (mazeRow[j + 1] != mazeRow[j]) {
                    maze.setCellInMaze(j * 2 + 2, y, Maze.EMPTY_BLOCK);

                    // full merging of two sets
                    int changeSet = mazeRow[j + 1];
                    for (int n = 0; n < mazeRow.length; n++) {
                        if (mazeRow[n] == changeSet) {
                            mazeRow[n] = mazeRow[j];
                        }
                    }
                }
            }
        } else {
            for (int j = 0; j < mazeRow.length; j++) {
                if (maze.getCellInMaze(j * 2 + 1, y + 1) == Maze.WALL) {
                    mazeRow[j] = 0;
                }
            }
        }
    }


    private void completeMaze(Maze maze) {
        int height = maze.getHeight();
        int width = maze.getWidth();

        for (int x = 0; x < width; x++) {
            maze.setCellInMaze(x, 0, Maze.WALL);
            maze.setCellInMaze(x, height - 1, Maze.WALL);
        }

        for (int y = 1; y < height - 1; y++) {
            maze.setCellInMaze(0, y, Maze.WALL);
            maze.setCellInMaze(width - 1, y, Maze.WALL);
        }

        for (int y = 2; y < height - 1; y += 2) {
            for (int x = 2; x < width - 1; x += 2) {
                maze.setCellInMaze(x, y, Maze.WALL);
            }
        }
    }
}
