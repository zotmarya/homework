package edu.hw6.task4;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OutputStreamCompositionTest {
    @Test
    void writeIntoFile_WhenGivenTextAndFile_CreatesFileAndReturnsTrue() {
        OutputStreamComposition composition = new OutputStreamComposition();
        String fileName = "text.txt";
        String text = "Programming is learned by writing programs. ― Brian Kernighan";

        boolean isWritten = composition.writeIntoFile(fileName, text);

        assertThat(isWritten).isTrue();
    }
}
