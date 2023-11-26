package edu.hw6.task2;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FileClonerTest {

    @Test
    void cloneFile_WhenGivenFilePath_CreatesCopyReturnsTrue() {
        FileCloner fileCloner = new FileCloner();
        Path pathFile = Path.of("src/test/resources/hw6/file.txt");

        boolean isCreated = fileCloner.cloneFile(pathFile);

        assertThat(isCreated).isTrue();
    }

    @Test
    void cloneFile_WhenGivenInvalidFilePath_ReturnsFalse() {
        FileCloner fileCloner = new FileCloner();
        Path pathFile = Path.of("src/test/resources/hw/cats.txt");

        boolean isCreated = fileCloner.cloneFile(pathFile);

        assertThat(isCreated).isFalse();
    }
}
