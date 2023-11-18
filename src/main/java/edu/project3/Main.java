package edu.project3;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ViewHandler viewHandler = new ViewHandler();
        DataHandler handler = new DataHandler();
        String filePath = handler.getFilePath(args);
        LogAnalyzer logAnalyzer = new LogAnalyzer(args);

        if (handler.isFile(filePath)) {
            List<File> files = handler.getFiles(filePath);
            logAnalyzer.setLogs(handler.getLogsFromFiles(files));
            logAnalyzer.setFileNames(files);
        } else {
            logAnalyzer.setLogs(handler.readLogsFromURL(filePath));
        }

        viewHandler.printGeneralInfo(logAnalyzer.getGeneralInfo());
        viewHandler.printRequestedResources(logAnalyzer.getRequestedResources());
        viewHandler.printCodes(logAnalyzer.getResponseCodes());

        handler.createReportFile(
            logAnalyzer.getGeneralInfo(),
            logAnalyzer.getRequestedResources(),
            logAnalyzer.getResponseCodes(),
            logAnalyzer.getFormat()
        );
    }

    private Main() {
    }
}
