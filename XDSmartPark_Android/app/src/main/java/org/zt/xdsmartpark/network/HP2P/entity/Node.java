package org.zt.xdsmartpark.network.HP2P.entity;

import android.net.IpPrefix;

import com.google.gson.annotations.SerializedName;

public class Node {
    @SerializedName("nodeId")
    private String nodeId;
    @SerializedName("ipAdress")
    private IPAddress ipAddress;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public IPAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IPAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Node() {
    }

    public Node(String nodeId, IPAddress ipAddress) {
        this.nodeId = nodeId;
        this.ipAddress = ipAddress;
    }
}
