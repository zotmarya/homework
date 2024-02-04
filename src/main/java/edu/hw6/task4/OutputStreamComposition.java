package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class OutputStreamComposition {

    private static final String PATH = "src/test/resources/hw6/";
    private static final String CHARSET = "UTF-8";

    public boolean writeIntoFile(String fileName, String text) {
        try (OutputStream outputStream = Files.newOutputStream(Path.of(PATH + fileName));
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new Adler32());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, CHARSET);
             PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {

            printWriter.write(text);

        } catch (IOException exception) {
            return false;
        }

        return true;
    }
}
