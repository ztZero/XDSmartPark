package org.zt.xdsmartpark.network.HP2P.entity;

import java.util.Objects;

public class IPAddress {
    private String ip;
    private int port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public IPAddress(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public IPAddress() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IPAddress ipAddress = (IPAddress) o;
        return port == ipAddress.port &&
                Objects.equals(ip, ipAddress.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, port);
    }

    @Override
    public String toString() {
        return "IPAddress{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
