package edu.hw7.task1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CounterTest {
    @Test
    void incrementCounter_WhenRunningThreads_IncrementsThreads() {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();

        counter1.getThread().start();
        counter2.getThread().start();

        try {
            counter1.getThread().join();
            counter2.getThread().join();
        } catch (InterruptedException exception) {
        }

        int resultCounterValue = Counter.COUNTER.get();

        assertThat(resultCounterValue).isEqualTo(2);
    }
}
