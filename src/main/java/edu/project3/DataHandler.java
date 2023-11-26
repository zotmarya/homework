package edu.project3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataHandler {

    private static final String DIRECTORY = "src/main/resources/project3/";
    private static final String KEY_DIRECTORY = "user.dir";
    private static final String DEFAULT_EXTENSION = ".txt";
    private static final String REPORT_NAME = "report";
    private static final String GLOB = "glob:";
    private static final String MARKDOWN = "markdown";
    private static final String ADOC = "adoc";
    private static final String PATH_TO_RESOURCES = System.getProperty(KEY_DIRECTORY) + "/" + DIRECTORY;
    private static final int DIGITAL_STORAGE = 1024;
    private static final Logger LOGGER = LogManager.getLogger();

    public boolean isFile(String requestedSource) {
        if (requestedSource.contains("://")) {
            return false;
        }

        return true;
    }

    public String getFilePath(String[] args) {
        String path = null;

        for (int i = 0; i < args.length; i++) {
            if ("--path".equals(args[i])) {
                path = args[i + 1];
            }
        }

        return path;
    }

    public List<String> readLogsFromURL(String url) {
        List<String> logs = new ArrayList<>();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

            HttpResponse<InputStream> httpResponse =
                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofInputStream());
            InputStream inputStream = httpResponse.body();
            int symbol;
            StringBuilder stringBuilder = new StringBuilder();

            while ((symbol = inputStream.read()) != -1) {
                if ((char) symbol == '\n') {
                    logs.add(stringBuilder.toString());
                    stringBuilder.setLength(0);
                } else {
                    stringBuilder.append((char) symbol);
                }
            }
        } catch (URISyntaxException | IOException | InterruptedException | IllegalArgumentException exception) {
            logs = null;
        }

        return logs;
    }

    public List<File> getFiles(String path) {
        File directory = new File(PATH_TO_RESOURCES);

        FileSystem fileSystem = FileSystems.getDefault();

        PathMatcher pathMatcher = fileSystem.getPathMatcher(GLOB + "**/" + path);

        FileVisitOption fileVisitOption = FileVisitOption.FOLLOW_LINKS;

        List<File> files = new ArrayList<>();

        try (Stream<Path> stream = Files.walk(directory.toPath(), fileVisitOption)) {
            stream.filter(pathMatcher::matches).forEach(file -> files.add(file.toFile()));
        } catch (IOException exception) {
            LOGGER.info(exception);
        }

        return files;
    }

    public List<String> getLogsFromFiles(List<File> files) {
        List<String> logs = new ArrayList<>();

        Iterator<File> iterator = files.iterator();

        while (iterator.hasNext()) {
            File file = iterator.next();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String log;
                while ((log = reader.readLine()) != null) {
                    logs.add(log);
                }
            } catch (IOException exception) {
                LOGGER.info(exception);
            }
        }

        return logs;
    }

    public boolean createReportFile(LogAnalyzer logAnalyzer) {
        String extension = DEFAULT_EXTENSION;
        String format = logAnalyzer.getFormat();

        if (MARKDOWN.equals(format)) {
            extension = MARKDOWN;
        } else if (ADOC.equals(format)) {
            extension = ADOC;
        }

        File reportFile = new File(DIRECTORY + REPORT_NAME + "." + extension);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportFile))) {
            reportFile.createNewFile();

            writeGeneralInfo(bufferedWriter, logAnalyzer);
            writeRequestedResources(bufferedWriter, logAnalyzer);
            writeTraffic(bufferedWriter, logAnalyzer);
            writeResponseCodes(bufferedWriter, logAnalyzer);
            writeTopIP(bufferedWriter, logAnalyzer);

        } catch (IOException exception) {
            return false;
        }

        return true;
    }

    private void writeGeneralInfo(BufferedWriter bufferedWriter, LogAnalyzer logAnalyzer) throws IOException {
        bufferedWriter.write("#### Общая информация\n");
        bufferedWriter.write("Метрика            Значение\n");
        for (Map.Entry<String, String> entry : logAnalyzer.getGeneralInfo().entrySet()) {
            bufferedWriter.write(entry.getKey() + ": " + entry.getValue() + "\n");
        }
    }

    private void writeRequestedResources(BufferedWriter bufferedWriter, LogAnalyzer logAnalyzer) throws IOException {
        bufferedWriter.write("\n#### Запрашиваемые ресурсы\n");
        bufferedWriter.write("Ресурс            Количество\n");
        for (Map.Entry<String, Integer> entry : logAnalyzer.getRequestedResources().entrySet()) {
            bufferedWriter.write(entry.getKey() + ": " + entry.getValue() + "\n");
        }
    }

    private void writeTraffic(BufferedWriter bufferedWriter, LogAnalyzer logAnalyzer) throws IOException {
        bufferedWriter.write("\n#### Траффик ресурсов\n");
        bufferedWriter.write("Ресурс             Трафик, мб\n");
        for (Map.Entry<String, Integer> entry : logAnalyzer.getResourceTraffic().entrySet()) {
            bufferedWriter.write(entry.getKey() + ": "
                + String.format("%.6f", (double) entry.getValue() / DIGITAL_STORAGE / DIGITAL_STORAGE) + "\n");
        }
    }

    private void writeResponseCodes(BufferedWriter bufferedWriter, LogAnalyzer logAnalyzer) throws IOException {
        bufferedWriter.write("\n#### Коды ответа\n");
        bufferedWriter.write("Код        Имя         Количество\n");
        for (Map.Entry<Integer, Object[]> entry : logAnalyzer.getResponseCodes().entrySet()) {
            bufferedWriter.write(entry.getKey() + ": " + entry.getValue()[0] + " " + entry.getValue()[1] + "\n");
        }
    }

    private void writeTopIP(BufferedWriter bufferedWriter, LogAnalyzer logAnalyzer) throws IOException {
        bufferedWriter.write("\n#### Топ частоты запросов с айпи\n");
        bufferedWriter.write("Место   IP             Количество\n");
        int place = 1;
        for (Map.Entry<String, Integer> entry : logAnalyzer.getIpSortedTop()) {
            bufferedWriter.write(place++ + "\t" + entry.getKey() + ":\t\t" + entry.getValue() + "\n");
        }
    }
}
