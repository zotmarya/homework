package edu.hw2.task4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import static org.assertj.core.api.Assertions.assertThat;

public class CallingInfoTest {
    @Test
    void callingInfo_WhenCalled_ReturnsClassAndMethodName() {
        CallingInfo callingInfo = CallingInfo.callingInfo();

        assertThat(callingInfo.className() + " " + callingInfo.methodName()).isEqualTo("com.intellij.rt.junit.JUnitStarter" + " " + "main");
    }

}
