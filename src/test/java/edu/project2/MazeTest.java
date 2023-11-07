package edu.project2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.File;
import static org.assertj.core.api.Assertions.assertThat;

public class MazeTest {

    @ParameterizedTest
    @ValueSource(strings = {"maze-invalid-size.txt", "maze-invalid-format.txt"})
    void getInstanceFromFile_WhenPassingFileWithInvalidMaze_ReturnsNull(String fileName) {
        File fileMaze = new File("src/main/resources/project2/" + fileName);

        Maze maze = Maze.getInstanceFromFile(fileMaze);

        assertThat(maze).isNull();

    }

    @Test
    void getInstanceFromFile_WhenPassingFileWithValidMaze_ReturnsMazeObject() {
        File fileMaze = new File("src/main/resources/project2/maze-1.txt");

        Maze maze = Maze.getInstanceFromFile(fileMaze);

        assertThat(maze).isNotNull();
    }

    @Test
    void getInstanceFromFile_WhenPassingNonExistentFile_ReturnsNull() {
        File fileMaze = new File("src/main/resources/project2/maz.txt");

        Maze maze = Maze.getInstanceFromFile(fileMaze);

        assertThat(maze).isNull();
    }
}
