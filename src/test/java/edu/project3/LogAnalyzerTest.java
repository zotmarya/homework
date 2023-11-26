package edu.project3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class LogAnalyzerTest {
    private static final String PATH = "src/test/resources/project3/";
    private DataHandler dataHandler;
    private LogAnalyzer logAnalyzer;

    @BeforeEach
    void setUp() {
        dataHandler = new DataHandler();
        List<String> logs = dataHandler.getLogsFromFiles(List.of(new File(PATH + "logs/logs1/2023-08-31.txt")));

        logAnalyzer = new LogAnalyzer(new String[] {"--path", "logs/**/2023-08-31.txt"});
        logAnalyzer.setLogs(logs);
    }

    @Test
    void getResourceTraffic_WhenAnalyzingData_ReturnsAnalyzedTraffic() {
        Map<String, Integer> traffic = logAnalyzer.getResourceTraffic();

        assertThat(traffic.size()).isEqualTo(1);
    }

    @Test
    void getIpSortedTop_WhenAnalyzingData_ReturnsAnalyzedIpSortedTop() {
        List<Map.Entry<String, Integer>> ipSortedTop = logAnalyzer.getIpSortedTop();

        assertThat(ipSortedTop.size()).isEqualTo(3);
    }

    @Test
    void getResponseCodes_WhenAnalyzingData_ReturnsAnalyzedResponseCodes() {
        Map<Integer, Object[]> responseCodes = logAnalyzer.getResponseCodes();

        assertThat(responseCodes.size()).isEqualTo(2);
    }

    @Test
    void getRequestedResources_WhenAnalyzingData_ReturnsAnalyzedResponseCodes() {
        Map<String, Integer> requestedResources = logAnalyzer.getRequestedResources();

        assertThat(requestedResources.size()).isEqualTo(1);
    }
}
