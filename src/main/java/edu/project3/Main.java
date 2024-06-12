package edu.project3;

import java.io.File;
import java.util.List;

public class Main {
    private static final String DIRECTORY = "src/main/resources/project3/";

    public static void main(String[] args) {
        ViewHandler viewHandler = new ViewHandler();
        DataHandler handler = new DataHandler();
        String filePath = handler.getFilePath(args);
        LogAnalyzer logAnalyzer = new LogAnalyzer(args);

        if (handler.isFile(filePath)) {
            List<File> files = handler.getFiles(DIRECTORY, filePath);
            logAnalyzer.setLogs(handler.getLogsFromFiles(files));
            logAnalyzer.setFileNames(files);
        } else {
            logAnalyzer.setLogs(handler.readLogsFromURL(filePath));
        }

        viewHandler.printGeneralInfo(logAnalyzer.getGeneralInfo());
        viewHandler.printRequestedResources(logAnalyzer.getRequestedResources());
        viewHandler.printResourceTraffic(logAnalyzer.getResourceTraffic());
        viewHandler.printCodes(logAnalyzer.getResponseCodes());
        viewHandler.printIps(logAnalyzer.getIpSortedTop());

        handler.createReportFile(logAnalyzer);
    }

    private Main() {
    }
}
