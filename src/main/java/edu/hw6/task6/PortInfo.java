package edu.hw6.task6;

public class PortInfo {
    private String protocol;
    private int port;
    private String service;

    public PortInfo(String protocol, int port, String service) {
        this.protocol = protocol;
        this.port = port;
        this.service = service;
    }

    @Override public String toString() {
        return "PortInfo{protocol='" + protocol + "\', port=" + port + ", service='" + service + "\'}";
    }

    public int getPort() {
        return port;
    }
}
