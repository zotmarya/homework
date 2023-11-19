package edu.project3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class DataHandlerTest {
    private static final String PATH = "src/test/resources/project3/";
    private DataHandler dataHandler;

    @BeforeEach
    void setUp() {
        dataHandler = new DataHandler();
    }

    @Test
    void readLogsFromURL_WhenGivenURL_ReturnsListOfLogs() {
        String url =
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";

        List<String> logs = dataHandler.readLogsFromURL(url);

        assertThat(logs.size()).isGreaterThan(0);
    }

    @Test
    void readLogsFromURL_WhenGivenInvalidURL_ReturnsNull() {
        String url =
            "htw.githubusercontent.com/elastic/examples/master/Co=0Formats/nginx_logs/nginx_logs";

        List<String> logs = dataHandler.readLogsFromURL(url);

        assertThat(logs).isNull();
    }

    @Test
    void getLogsFromFiles_WhenGivenListOfFiles_ReturnsListOfLogs() {
        List<File> files = List.of(
            new File(PATH + "logs/logs1/2023-08-31.txt"),
            new File(PATH + "logs/logs2/2023-08-31.txt")
        );

        List<String> logs = dataHandler.getLogsFromFiles(files);

        assertThat(logs.size()).isEqualTo(23);
    }

    @Test
    void getFiles_WhenGivenPath_ReturnsListOfFiles() {
        String filePath = "logs/**/2023-08-31.txt";

        List<File> logs = dataHandler.getFiles(PATH + filePath);

        assertThat(logs.get(0).getName()).isEqualTo("2023-08-31.txt");
    }

}
