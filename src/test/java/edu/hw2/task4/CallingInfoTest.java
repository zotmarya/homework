package edu.hw2.task4;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CallingInfoTest {
    @Test
    void callingInfo_WhenCalled_ReturnsClassAndMethodName() {
        CallingInfo callingInfo = CallingInfo.callingInfo();

        assertThat(callingInfo.className() + " " + callingInfo.methodName()).isEqualTo(
            "edu.hw2.task4.CallingInfoTest callingInfo_WhenCalled_ReturnsClassAndMethodName");
    }

}
