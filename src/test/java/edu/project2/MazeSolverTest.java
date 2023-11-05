package edu.project2;

import java.io.File;
import java.util.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MazeSolverTest {

    private MazeSolver mazeSolver;
    private LinkedList<Point> wayToExit;

    @BeforeEach
    void setUp() {
        mazeSolver = new MazeSolver();
        wayToExit = new LinkedList<>();
    }

    @Test
    void findBreadthFirstWay_WhenGivenSolvableMaze_SolvesMaze() {
        Maze maze = Maze.getInstanceFromFile(new File(".\\src\\main\\resources\\project2\\maze-2.txt"));

        mazeSolver.findBFWay(maze, maze.getStartPoint(), maze.getEndPoint(), wayToExit);

        assertThat(wayToExit).isNotEmpty();
    }

    @Test
    void findBreadthFirstWay_WhenGivenUnsolvableMaze_DoesNotSolveMaze() {
        Maze maze = Maze.getInstanceFromFile(new File("src\\main\\resources\\project2\\maze-3.txt"));

        mazeSolver.findBFWay(maze, maze.getStartPoint(), maze.getEndPoint(), wayToExit);

        assertThat(wayToExit).isEmpty();
    }

    @Test
    void findDepthFirstWay_WhenGivenSolvableMaze_SolvesMaze() {
        Maze maze = Maze.getInstanceFromFile(new File("src\\main\\resources\\project2\\maze-2.txt"));

        mazeSolver.findDFWay(maze, maze.getStartPoint(), maze.getEndPoint(), wayToExit);

        assertThat(wayToExit).isNotEmpty();
    }

    @Test
    void findDepthFirstWay_WhenGivenUnsolvableMaze_DoesNotSolveMaze() {
        Maze maze = Maze.getInstanceFromFile(new File("src\\main\\resources\\project2\\maze-3.txt"));

        mazeSolver.findDFWay(maze, maze.getStartPoint(), maze.getEndPoint(), wayToExit);

        assertThat(wayToExit).isEmpty();
    }
}
