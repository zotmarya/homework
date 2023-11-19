package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import static edu.hw6.task3.AbstractFilter.globMatches;
import static edu.hw6.task3.AbstractFilter.largerThan;
import static edu.hw6.task3.AbstractFilter.magicNumber;
import static edu.hw6.task3.AbstractFilter.READABLE;
import static edu.hw6.task3.AbstractFilter.regexContains;
import static org.assertj.core.api.Assertions.assertThat;

public class AbstractFilterTest {
    private static final Path PATH = Path.of("src/test/resources/hw6/");

    @Test
    void abstractFilter_WhenGivenFilter_ReturnsListOfPath() {
        DirectoryStream.Filter<Path> filter = AbstractFilter.REGULAR_FILE
            .and(READABLE)
            .and(largerThan(10))
            .and(magicNumber(Arrays.asList((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G')))
            .and(globMatches(".+\\.png"))
            .and(regexContains(".*[-].*"));

        Path foundPath = null;

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(PATH, filter)) {
            Iterator<Path> iterator = entries.iterator();

            if (iterator.hasNext()) {
                foundPath = iterator.next();
            }
        } catch (IOException exception) {
            foundPath = null;
        }

        assertThat(foundPath.getFileName().toString()).isEqualTo("cat-2.png");
    }
}
