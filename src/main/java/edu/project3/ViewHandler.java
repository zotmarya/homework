package edu.project3;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ViewHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    public void printGeneralInfo(Map<String, String> generalInfo) {
        LOGGER.info("Метрика            Значение");

        for (Map.Entry<String, String> entry : generalInfo.entrySet()) {
            LOGGER.info(entry.getKey() + ": " + entry.getValue());
        }

        LOGGER.info("");
    }

    public void printRequestedResources(Map<String, Integer> requestedResources) {
        LOGGER.info("Ресурс            Количество");

        for (Map.Entry<String, Integer> entry : requestedResources.entrySet()) {
            LOGGER.info(entry.getKey() + ": " + entry.getValue());
        }

        LOGGER.info("");
    }

    public void printCodes(Map<Integer, Object[]> responseCodes) {
        LOGGER.info("Код        Имя         Количество");

        for (Map.Entry<Integer, Object[]> entry : responseCodes.entrySet()) {
            LOGGER.info(entry.getKey() + ": " + entry.getValue()[0] + " " + entry.getValue()[1]);
        }

        LOGGER.info("");
    }
}
