package com.libiyi.exa.common.connection;

public class ServerInstance {
    private String ip;
    private int port;

    public ServerInstance(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "ServerInstance{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
