package edu.project2;

import java.util.List;

public class MazeView {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";

    @SuppressWarnings("RegexpSinglelineJava")
    public void printMaze(Maze maze, List<Point> way) {
        char[][] mazeArray = maze.getMaze().clone();

        identifyCharactersToPrint(mazeArray, way);

        System.out.println();

        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[0].length; j++) {
                String color = mazeArray[i][j] == Maze.WALL ? ANSI_PURPLE : ANSI_GREEN;
                System.out.print(color + mazeArray[i][j] + ANSI_RESET);
            }

            System.out.println();
        }
    }

    private void identifyCharactersToPrint(char[][] mazeArray, List<Point> way) {
        for (int i = 0, size = way.size(); i < size; i++) {
            Point point = way.get(i);
            if (i == 0) {
                mazeArray[point.getY()][point.getX()] = Maze.ENTRANCE;
            } else if (i == size - 1) {
                mazeArray[point.getY()][point.getX()] = Maze.EXIT;
            } else {
                WayCharacterIdentifier characterIdentifier = new WayCharacterIdentifier(point);
                characterIdentifier.setNeighbourPoint(way.get(i - 1));
                characterIdentifier.setNeighbourPoint(way.get(i + 1));
                mazeArray[point.getY()][point.getX()] = characterIdentifier.getCharacter();
            }
        }
    }

}
