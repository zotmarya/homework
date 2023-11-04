package edu.project2;

import java.io.File;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGenerator();

        //Maze maze = mazeGenerator.createMazeRB(30, 10, 20);

        Maze maze = new Maze(new File("src\\main\\resources\\project2\\maze-3.txt"));

        MazeSolver mazeSolver = new MazeSolver();

        LinkedList<Point> wayToExit = new LinkedList<>();

        mazeSolver.findDFWay(maze, maze.getStartPoint(), maze.getEndPoint(), wayToExit);

//        mazeSolver.findBFWay(maze, maze.getStartPoint(), maze.getEndPoint(), wayToExit);

        MazeView mazeView = new MazeView();

        mazeView.printMaze(maze, wayToExit);

    }
}
