package org.zt.model.request;

import org.zt.HP2P.IPAddress;

public class ApplyJoinHP2PRequest {
	
	private int nodeId;
    private IPAddress ipAddress;
    private double longitude;
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
