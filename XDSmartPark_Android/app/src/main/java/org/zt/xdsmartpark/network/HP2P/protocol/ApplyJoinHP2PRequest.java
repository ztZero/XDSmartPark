package org.zt.xdsmartpark.network.HP2P.protocol;

import com.google.gson.annotations.SerializedName;

import org.zt.xdsmartpark.network.HP2P.entity.IPAddress;

public class ApplyJoinHP2PRequest {
    @SerializedName("nodeId")
    private int nodeId;
    @SerializedName("ipAddress")
    private IPAddress ipAddress;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("latitude")
    private double latitude;

    public ApplyJoinHP2PRequest(int nodeId, IPAddress ipAddress, double longitude, double latitude) {
        this.nodeId = nodeId;
        this.ipAddress=ipAddress;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public IPAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IPAddress ipAddress) {
        this.ipAddress = ipAddress;
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

    public ApplyJoinHP2PRequest() {
    }
}
