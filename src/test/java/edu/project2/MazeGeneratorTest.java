package edu.project2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MazeGeneratorTest {

    private MazeGenerator mazeGenerator;

    @BeforeEach
    void setUp() {
        mazeGenerator = new MazeGenerator();
    }

    @Test
    void createMazeRecursiveBacktracking_WhenPassingInvalidArguments_CreatesDefaultSizeMaze() {
        Maze maze = mazeGenerator.createMazeRB(-1, 10, 1);

        int mazeSize = maze.getVerticalCellsAmount();

        assertThat(mazeSize).isEqualTo(3);
    }

    @Test
    void createMazeRecursiveBacktracking_WhenPassingValidArguments_CreatesCorrectSizeMaze() {
        Maze maze = mazeGenerator.createMazeEA(30, 30, 1);

        int mazeSize = maze.getVerticalCellsAmount();

        assertThat(mazeSize).isEqualTo(30);
    }

    @Test
    void createMazeEllerAlgorithm_WhenPassingInvalidArguments_CreatesDefaultSizeMaze() {
        Maze maze = mazeGenerator.createMazeEA(-1, 10, 1);

        int mazeSize = maze.getVerticalCellsAmount();

        assertThat(mazeSize).isEqualTo(3);
    }

    @Test
    void createMazeEllerAlgorithm_WhenPassingValidArguments_CreatesCorrectSizeMaze() {
        Maze maze = mazeGenerator.createMazeEA(20, 20, 1);

        int mazeSize = maze.getVerticalCellsAmount();

        assertThat(mazeSize).isEqualTo(20);
    }
}
