package edu.project3;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("MagicNumber")
public class LogAnalyzer {

    private List<String> logs;
    private HashMap<String, String> inputInfo;
    private HashMap<String, String> generalInfo;
    private HashMap<String, Integer> requestedResources;
    private HashMap<Integer, Object[]> responseCodes;
    private LocalDate dateStart = LocalDate.MIN;
    private LocalDate dateEnd = LocalDate.MAX;
    private long logsAmount = 0L;
    private long avgSize = 0L;
    private static final String START_DATE = "start_time";
    private static final String END_DATE = "end_time";
    private static final String SOURCE = "sources";
    private static final String REQUESTS_AMOUNT = "requests_amount";
    private static final String AVG_RESPONSE_SIZE = "avg_response_size";
    private static final String PATH = "--path";
    private static final String FROM = "--from";
    private static final String TO = "--to";
    private static final String FORMAT = "--format";
    private static final String NONE = "-";

    private static final Pattern LOG_PATTERN = Pattern
        .compile(
            "^(\\d+\\.\\d+\\.\\d+\\.\\d+) - - \\[(\\d+/[a-zA-Z]+/\\d+:\\d+:\\d+:\\d+ \\+\\d{4})] \"[a-zA-Z]+ (.+)"
                + " .+\" (\\d{3}) (\\d+) \"-\" \".+\"$");

    public LogAnalyzer(String[] info) {
        initializeInputInfoMap(info);
        responseCodes = new HashMap<>();
        requestedResources = new HashMap<>();
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
        initializeGeneralInfoMap();
        analyzeLogs();
    }

    private void analyzeLogs() {
        Iterator<String> iterator = logs.iterator();

        String start = inputInfo.get(FROM);
        if (!NONE.equals(start)) {
            dateStart = LocalDate.parse(start);
        }

        String end = inputInfo.get(TO);
        if (!NONE.equals(end)) {
            dateEnd = LocalDate.parse(end);
        }

        while (iterator.hasNext()) {
            String logLine = iterator.next();
            Matcher matcher = LOG_PATTERN.matcher(logLine);

            if (matcher.find()) {
                parseLog(matcher);
            }
        }

        avgSize /= logs.size();
        generalInfo.put(AVG_RESPONSE_SIZE, avgSize + "b");
        generalInfo.put(REQUESTS_AMOUNT, String.valueOf(logsAmount));
    }

    public void parseLog(Matcher matcher) {
        // Date
        String date = matcher.group(2);
        OffsetDateTime dateTime =
            OffsetDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z"));

        if (dateTime.isBefore(OffsetDateTime.of(dateStart, LocalTime.MIN, ZoneOffset.UTC))
            || dateTime.isAfter(OffsetDateTime.of(dateEnd, LocalTime.MIN, ZoneOffset.UTC))) {
            return;
        }

        logsAmount++;

        String ip = matcher.group(1);

        // Requested sources amount
        String source = matcher.group(3);
        if (requestedResources.containsKey(source)) {
            requestedResources.put(source, requestedResources.get(source) + 1);
        } else {
            requestedResources.put(source, 1);
        }

        int code = Integer.valueOf(matcher.group(4));
        if (responseCodes.containsKey(code)) {
            Object[] info = responseCodes.get(code);
            info[1] = (Integer) info[1] + 1;

        } else {
            Object[] info = new Object[2];
            info[1] = 1;

            switch (code) {
                case 200 -> {
                    info[0] = "OK";
                }
                case 304 -> {
                    info[0] = "Not Modified";
                }
                case 403 -> {
                    info[0] = "Forbidden";
                }
                case 404 -> {
                    info[0] = "Not Found";
                }
                case 500 -> {
                    info[0] = "Internal Server Error";
                }
                default -> {
                    info[0] = "Unknown Code";
                }
            }

            responseCodes.put(code, info);
        }

        int size = Integer.valueOf(matcher.group(5));

        avgSize += size;
    }

    public void setFileNames(List<File> files) {
        StringBuilder fileNames = new StringBuilder();

        for (File file : files) {
            fileNames.append("'" + file.getName() + "'");
            fileNames.append(" ");
        }

        generalInfo.put(SOURCE, fileNames.toString());
    }

    private void initializeGeneralInfoMap() {
        generalInfo = new HashMap<>();

        generalInfo.put(SOURCE, inputInfo.get(PATH));
        generalInfo.put(START_DATE, inputInfo.get(FROM));
        generalInfo.put(END_DATE, inputInfo.get(TO));
        generalInfo.put(REQUESTS_AMOUNT, NONE);
        generalInfo.put(AVG_RESPONSE_SIZE, NONE);
    }

    private void initializeInputInfoMap(String[] info) {
        inputInfo = new HashMap<>();

        inputInfo.put(PATH, NONE);
        inputInfo.put(FROM, NONE);
        inputInfo.put(TO, NONE);
        inputInfo.put(FORMAT, NONE);

        for (int i = 0; i < info.length; i++) {
            if (inputInfo.containsKey(info[i])) {
                inputInfo.put(info[i], info[i + 1]);
            }
        }
    }

    public String getFormat() {
        return inputInfo.get(FORMAT);
    }

    public HashMap<String, String> getGeneralInfo() {
        return generalInfo;
    }

    public HashMap<String, Integer> getRequestedResources() {
        return requestedResources;
    }

    public HashMap<Integer, Object[]> getResponseCodes() {
        return responseCodes;
    }
}
