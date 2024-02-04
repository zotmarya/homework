package edu.hw6.task6;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class PortsScannerTest {
    @Test
    void scanPorts_ReturnsPortsInfoList() {
        PortsScanner portsScanner = new PortsScanner();

        List<PortInfo> ports = portsScanner.scanPorts(130, 140);

        assertThat(ports.size()).isGreaterThan(0);
    }
}
