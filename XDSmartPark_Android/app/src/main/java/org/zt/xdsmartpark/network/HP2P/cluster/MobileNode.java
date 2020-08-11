package org.zt.xdsmartpark.network.HP2P.cluster;

import org.zt.xdsmartpark.network.HP2P.entity.IPAddress;
import org.zt.xdsmartpark.network.HP2P.entity.Node;

import java.util.List;

public class MobileNode{

    private int nodeId;
    private boolean isOnline;
    private IPAddress ipAddress;
    private List<Node> superNodeList;
    private List<Node> neighborNodeList;
    private double longitude;
    private double latitude;

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public IPAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IPAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public List<Node> getSuperNodeList() {
        return superNodeList;
    }

    public void setSuperNodeList(List<Node> superNodeList) {
        this.superNodeList = superNodeList;
    }

    public List<Node> getNeighborNodeList() {
        return neighborNodeList;
    }

    public void setNeighborNodeList(List<Node> neighborNodeList) {
        this.neighborNodeList = neighborNodeList;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "MobileNode{" +
                "nodeId=" + nodeId +
                ", isOnline=" + isOnline +
                ", ipAddress=" + ipAddress +
                ", superNodeList=" + superNodeList +
                ", neighborNodeList=" + neighborNodeList +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
