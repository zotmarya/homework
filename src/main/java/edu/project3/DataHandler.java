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

public class DataHandler {

    private static final String DIRECTORY = "src/main/resources/project3/";

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
        } catch (URISyntaxException | IOException | InterruptedException exception) {
            logs = null;
        }

        return logs;
    }

    public List<File> getFiles(String path) {
        File directory = new File(System.getProperty("user.dir") + "/src");
        FileSystem fileSystem = FileSystems.getDefault();

        PathMatcher pathMatcher = fileSystem.getPathMatcher("glob:" + path);

        FileVisitOption fileVisitOption = FileVisitOption.FOLLOW_LINKS;

        List<File> files = new ArrayList<>();

        try (Stream<Path> stream = Files.walk(directory.toPath(), fileVisitOption)) {
            stream.filter(pathMatcher::matches).forEach(file -> files.add(file.toFile()));
        } catch (
            IOException exception) {

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

            }
        }

        return logs;
    }

    public boolean createReportFile(LogAnalyzer logAnalyzer) {
        String extension = ".txt";
        String format = logAnalyzer.getFormat();

        if ("markdown".equals(format)) {
            extension = "mk";
        } else if ("adoc".equals(format)) {
            extension = format;
        }

        File reportFile = new File(DIRECTORY + "report." + extension);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportFile))) {
            reportFile.createNewFile();

            bufferedWriter.write("#### Общая информация\n");
            bufferedWriter.write("Метрика            Значение\n");
            for (Map.Entry<String, String> entry : logAnalyzer.getGeneralInfo().entrySet()) {
                bufferedWriter.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }

            bufferedWriter.write("\n");
            bufferedWriter.write("#### Запрашиваемые ресурсы\n");
            bufferedWriter.write("Ресурс            Количество\n");
            for (Map.Entry<String, Integer> entry : logAnalyzer.getRequestedResources().entrySet()) {
                bufferedWriter.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }

            bufferedWriter.write("\n");
            bufferedWriter.write("#### Траффик ресурсов\n");
            bufferedWriter.write("Ресурс             Трафик, мб");
            for (Map.Entry<String, Integer> entry : logAnalyzer.getResourceTraffic().entrySet()) {
                bufferedWriter.write(entry.getKey() + ": " + String.format("%.2f",(double)entry.getValue()/1024/1024) + "\n");
            }

            bufferedWriter.write("\n");
            bufferedWriter.write("#### Коды ответа\n");
            bufferedWriter.write("Код        Имя         Количество\n");
            for (Map.Entry<Integer, Object[]> entry : logAnalyzer.getResponseCodes().entrySet()) {
                bufferedWriter.write(entry.getKey() + ": " + entry.getValue()[0] + " " + entry.getValue()[1] + "\n");
            }

            bufferedWriter.write("\n");
            bufferedWriter.write("#### Топ частоты запросов с айпи\n");
            bufferedWriter.write("Место   IP             Количество\n");
            int place = 1;
            for (Map.Entry<String, Integer> entry : logAnalyzer.getIpSortedTop()) {
                bufferedWriter.write(place++ + "\t" + entry.getKey() + ":\t\t" + entry.getValue() + "\n");
            }

        } catch (IOException exception) {
            return false;
        }

        return true;
    }
}
