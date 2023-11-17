package edu.project2;

import java.io.File;
import java.util.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MazeSolverTest {
    private LinkedList<Point> wayToExit;

    @BeforeEach
    void setUp() {
        wayToExit = new LinkedList<>();
    }

    @Test
    void findBreadthFirstWay_WhenGivenSolvableMaze_SolvesMaze() {
        MazeSolver mazeSolverBF = new MazeSolverBF();
        Maze maze = Maze.getInstanceFromFile(new File("src/test/resources/project2/maze-2.txt"));

        mazeSolverBF.findWay(maze, maze.getStartPoint(), maze.getEndPoint(), wayToExit);

        assertThat(wayToExit).isNotEmpty();
    }

    @Test
    void findBreadthFirstWay_WhenGivenUnsolvableMaze_DoesNotSolveMaze() {
        MazeSolver mazeSolverBF = new MazeSolverBF();
        Maze maze = Maze.getInstanceFromFile(new File("src/test/resources/project2/maze-3.txt"));

        mazeSolverBF.findWay(maze, maze.getStartPoint(), maze.getEndPoint(), wayToExit);

        assertThat(wayToExit).isEmpty();
    }

    @Test
    void findDepthFirstWay_WhenGivenSolvableMaze_SolvesMaze() {
        MazeSolver mazeSolverDF = new MazeSolverDF();
        Maze maze = Maze.getInstanceFromFile(new File("src/test/resources/project2/maze-2.txt"));

        mazeSolverDF.findWay(maze, maze.getStartPoint(), maze.getEndPoint(), wayToExit);

        assertThat(wayToExit).isNotEmpty();
    }

    @Test
    void findDepthFirstWay_WhenGivenUnsolvableMaze_DoesNotSolveMaze() {
        MazeSolver mazeSolverDF = new MazeSolverDF();
        Maze maze = Maze.getInstanceFromFile(new File("src/test/resources/project2/maze-3.txt"));

        mazeSolverDF.findWay(maze, maze.getStartPoint(), maze.getEndPoint(), wayToExit);

        assertThat(wayToExit).isEmpty();
    }
}
