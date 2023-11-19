package edu.hw6.task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCloner {

    private static final String COPY = " — копия";

    public boolean cloneFile(Path path) {
        boolean isPresent = true;
        int index = 0;

        String[] fileNameExtension = path.getFileName().toString().split("\\.");
        fileNameExtension[1] = "." + fileNameExtension[1];

        File file;
        Path copyFile;

        while (isPresent) {
            if (index++ == 0) {
                copyFile = path.resolveSibling(fileNameExtension[0] + COPY + fileNameExtension[1]);
            } else {
                copyFile = path.resolveSibling(fileNameExtension[0] + COPY + " (" + index + ")" + fileNameExtension[1]);
            }

            file = copyFile.toFile();

            if (!file.exists()) {
                isPresent = false;
                try {
                    Files.copy(path, copyFile);
                } catch (IOException exception) {
                    return false;
                }
            }
        }

        return true;
    }

}
