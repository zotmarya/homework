package edu.hw8.task3;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MagicNumber")
public class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        List<String> info = new ArrayList<>(List.of(
            "a.v.petrov  0832c1202da8d382318e329a7c133ea0",
            "v.v.belov   81dc9bdb52d04dc20036dbd8313ed055",
            "a.s.ivanov  e2fc714c4727ee9395f324cd2e7f331f",
            "k.p.maslov  ee49fde835bb1b2238d1aa6883c2a32c"
        ));

        PasswordBreaker passwordBreaker = new PasswordBreaker(info);

        long startTimeSingle = System.nanoTime();
        passwordBreaker.singleThreadPasswordBreak();
        long endTimeSingle = System.nanoTime();

        passwordBreaker.setInfo(info);

        long startTimeMulti1 = System.nanoTime();
        passwordBreaker.multiThreadPasswordBreak(10);
        long endTimeMulti1 = System.nanoTime();

        long startTimeMulti2 = System.nanoTime();
        passwordBreaker.multiThreadPasswordBreak(100);
        long endTimeMulti2 = System.nanoTime();

        long startTimeMulti3 = System.nanoTime();
        passwordBreaker.multiThreadPasswordBreak(1000);
        long endTimeMulti3 = System.nanoTime();

        double singleTime = endTimeSingle - startTimeSingle;
        double multi10Time = endTimeMulti1 - startTimeMulti1;
        double multi100Time = endTimeMulti2 - startTimeMulti2;
        double multi1000Time = endTimeMulti3 - startTimeMulti3;

        double speedUp10 = singleTime / multi10Time;
        double speedUp100 = singleTime / multi100Time;
        double speedUp1000 = singleTime / multi1000Time;

        LOGGER.info("10 THREADS: " + speedUp10);
        LOGGER.info("100 THREADS: " + speedUp100);
        LOGGER.info("1000 THREADS: " + speedUp1000);
    }

    private Main() {
    }
}
