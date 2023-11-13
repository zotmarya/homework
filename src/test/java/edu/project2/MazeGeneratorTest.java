package edu.project2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MazeGeneratorTest {
    @Test
    void createMazeRecursiveBacktracking_WhenPassingInvalidArguments_CreatesDefaultSizeMaze() {
        MazeGenerator mazeGeneratorRB = new MazeGeneratorRB();
        Maze maze = mazeGeneratorRB.createMaze(-1, 10, 1);

        int mazeSize = maze.getVerticalCellsAmount();

        assertThat(mazeSize).isEqualTo(3);
    }

    @Test
    void createMazeRecursiveBacktracking_WhenPassingValidArguments_CreatesCorrectSizeMaze() {
        MazeGenerator mazeGeneratorEA = new MazeGeneratorEA();
        Maze maze = mazeGeneratorEA.createMaze(30, 30, 1);

        int mazeSize = maze.getVerticalCellsAmount();

        assertThat(mazeSize).isEqualTo(30);
    }

    @Test
    void createMazeEllerAlgorithm_WhenPassingInvalidArguments_CreatesDefaultSizeMaze() {
        MazeGenerator mazeGeneratorEA = new MazeGeneratorEA();
        Maze maze = mazeGeneratorEA.createMaze(-1, 10, 1);

        int mazeSize = maze.getVerticalCellsAmount();

        assertThat(mazeSize).isEqualTo(3);
    }

    @Test
    void createMazeEllerAlgorithm_WhenPassingValidArguments_CreatesCorrectSizeMaze() {
        MazeGenerator mazeGeneratorEA = new MazeGeneratorEA();
        Maze maze = mazeGeneratorEA.createMaze(20, 20, 1);

        int mazeSize = maze.getVerticalCellsAmount();

        assertThat(mazeSize).isEqualTo(20);
    }
}
