package edu.project3;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ViewHandler {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int DIGITAL_STORAGE = 1024;

    public void printGeneralInfo(Map<String, String> generalInfo) {
        LOGGER.info("#### Общая информация");
        LOGGER.info("Метрика            Значение");

        for (Map.Entry<String, String> entry : generalInfo.entrySet()) {
            LOGGER.info(entry.getKey() + ": " + entry.getValue());
        }

        LOGGER.info("");
    }

    public void printRequestedResources(Map<String, Integer> requestedResources) {
        LOGGER.info("#### Запрашиваемые ресурсы");
        LOGGER.info("Ресурс            Количество");

        for (Map.Entry<String, Integer> entry : requestedResources.entrySet()) {
            LOGGER.info(entry.getKey() + ": " + entry.getValue());
        }

        LOGGER.info("");
    }

    public void printCodes(Map<Integer, Object[]> responseCodes) {
        LOGGER.info("#### Коды ответа");
        LOGGER.info("Код        Имя         Количество");

        for (Map.Entry<Integer, Object[]> entry : responseCodes.entrySet()) {
            LOGGER.info(entry.getKey() + ": " + entry.getValue()[0] + "\t" + entry.getValue()[1]);
        }

        LOGGER.info("");
    }

    public void printIps(List<Map.Entry<String, Integer>> ipSortedTop) {
        LOGGER.info("#### Топ частоты запросов с айпи");
        LOGGER.info("Место   IP             Количество");

        int place = 1;

        for (Map.Entry<String, Integer> entry : ipSortedTop) {
            LOGGER.info(place++ + "\t" + entry.getKey() + ":\t" + entry.getValue());
        }

        LOGGER.info("");
    }

    public void printResourceTraffic(Map<String, Integer> resourceTraffic) {
        LOGGER.info("#### Траффик ресурсов");
        LOGGER.info("Ресурс             Трафик, мб");

        for (Map.Entry<String, Integer> entry : resourceTraffic.entrySet()) {
            LOGGER.info(entry.getKey() + ": " + String.format(
                "%.6f", (double) entry.getValue() / DIGITAL_STORAGE / DIGITAL_STORAGE
            ));
        }

        LOGGER.info("");
    }
}
