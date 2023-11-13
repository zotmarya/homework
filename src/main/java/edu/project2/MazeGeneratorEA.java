package edu.project2;

import java.util.HashMap;
import java.util.Map;

public class MazeGeneratorEA extends MazeGenerator {
    @Override
    public Maze createMaze(int horizontalCellsAmount, int verticalCellsAmount, int seedRandom) {
        if (seedRandom != -1) {
            RANDOM.setSeed(seedRandom);
        }

        Maze maze = createMazePreparation(horizontalCellsAmount, verticalCellsAmount, Maze.EMPTY_BLOCK);

        createMazeEllerAlgorithm(maze);

        createEdgePoints(maze);

        return maze;
    }

    private void createMazeEllerAlgorithm(Maze maze) {

        int[] mazeRow = new int[(maze.getWidth() - 1) / 2];
        int rowsAmount = (maze.getHeight() - 1) / 2;
        int setNumber = 1;

        for (int i = 0; i < rowsAmount; i++) {
            // step 1
            setNumber = setNumbersInRow(setNumber, mazeRow);

            int y = i * 2 + 1;

            // step 2 - right walls
            addRightWalls(y, mazeRow, maze);

            // step 3 - bottom walls
            addBottomWalls(y, mazeRow, maze);

            // step 4
            finishOrPrepareForNextRow(i, y, rowsAmount, mazeRow, maze);
        }
        completeMaze(maze);
    }

    private int setNumbersInRow(int setNumber, int[] mazeRow) {
        int tmpSetNumber = setNumber;

        for (int j = 0; j < mazeRow.length; j++) {
            if (mazeRow[j] == 0) {
                mazeRow[j] = tmpSetNumber++;
            }
        }

        return tmpSetNumber;
    }

    private void addRightWalls(int y, int[] mazeRow, Maze maze) {
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

    private void addBottomWalls(int y, int[] mazeRow, Maze maze) {
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

    private void finishOrPrepareForNextRow(int i, int y, int rowsAmount, int[] mazeRow, Maze maze) {
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
