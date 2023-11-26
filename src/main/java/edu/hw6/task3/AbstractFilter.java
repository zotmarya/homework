package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    AbstractFilter REGULAR_FILE = Files::isRegularFile;
    AbstractFilter READABLE = Files::isReadable;

    default AbstractFilter and(AbstractFilter other) {
        return path -> this.accept(path) && other.accept(path);
    }

    static AbstractFilter largerThan(long size) {
        return path -> Files.size(path) > size;
    }

    static AbstractFilter magicNumber(List<Byte> magicBytes) {
        return path -> {
            byte[] fileBytes = Files.readAllBytes(path);
            if (fileBytes.length < magicBytes.size()) {
                return false;
            }
            for (int i = 0; i < magicBytes.size(); i++) {
                if (magicBytes.get(i) != fileBytes[i]) {
                    return false;
                }
            }
            return true;
        };
    }

    static AbstractFilter globMatches(String glob) {
        return path -> path.getFileName().toString().matches(glob);
    }

    static AbstractFilter regexContains(String regex) {
        return path -> path.getFileName().toString().matches(regex);
    }
}
