package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PortsScanner {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int PORTS_AMOUNT = 49151;
    private static final String TCP = "TCP";
    private static final String UDP = "UDP";
    private static final String TCP_UDP = "TCP,UDP";
    private static final String AVAILABLE = "Available";

    private static final HashMap<Integer, String> PORT_SERVICE_MAP =
        new HashMap<>(Map.of(
            135, "EPMAP",
            137, "NetBIOS Name Service",
            138, "NetBIOS Datagram Service",
            139, "NetBIOS Session Service"
        ));

    public List<PortInfo> scanPorts(int lowerBound, int upperBound) {
        List<PortInfo> ports = new ArrayList<>();
        boolean isTCPAvailable;
        boolean isUDPAvailable;

        for (int port = 0; port < PORTS_AMOUNT; port++) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                isTCPAvailable = true;
            } catch (IOException exception) {
                isTCPAvailable = false;
            }

            try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
                isUDPAvailable = true;
            } catch (SocketException exception) {
                isUDPAvailable = false;
            }

            if (isTCPAvailable && isUDPAvailable) {
                ports.add(new PortInfo(TCP_UDP, port, AVAILABLE));
            } else if (!isTCPAvailable && !isUDPAvailable) {
                ports.add(new PortInfo(TCP_UDP, port, PORT_SERVICE_MAP.get(port)));
            } else if (isTCPAvailable) {
                ports.add(new PortInfo(TCP, port, AVAILABLE));
                ports.add(new PortInfo(UDP, port, PORT_SERVICE_MAP.get(port)));
            } else {
                ports.add(new PortInfo(UDP, port, AVAILABLE));
                ports.add(new PortInfo(TCP, port, PORT_SERVICE_MAP.get(port)));
            }
        }

        for (int i = lowerBound; i < ports.size() && ports.get(i).getPort() <= upperBound; i++) {
            if (ports.get(i).getPort() >= lowerBound) {
                LOGGER.info(ports.get(i));
            }
        }

        return ports;
    }
}
