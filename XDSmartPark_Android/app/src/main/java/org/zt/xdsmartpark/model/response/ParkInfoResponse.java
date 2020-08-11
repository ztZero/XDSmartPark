package org.zt.xdsmartpark.model.response;

import com.google.gson.annotations.SerializedName;

import org.zt.xdsmartpark.network.HP2P.entity.IPAddress;

public class ParkInfoResponse {
    @SerializedName("parkId")
    private int parkId;
    @SerializedName("parkName")
    private String parkName;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("score")
    private double score;
    @SerializedName("parkFee")
    private double parkFee;
    @SerializedName("parkAddress")
    private String parkAddress;
    @SerializedName("parkSpace")
    private int parkSpace;
    @SerializedName("ipAddress")
    private IPAddress ipAddress;

    public int getParkSpace() {
        return parkSpace;
    }

    public void setParkSpace(int parkSpace) {
        this.parkSpace = parkSpace;
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getParkFee() {
        return parkFee;
    }

    public void setParkFee(double parkFee) {
        this.parkFee = parkFee;
    }

    public String getParkAddress() {
        return parkAddress;
    }

    public void setParkAddress(String parkAddress) {
        this.parkAddress = parkAddress;
    }

    public IPAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IPAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public ParkInfoResponse(int parkId, String parkName, double latitude, double longitude, double score, double parkFee, String parkAddress, int parkSpace, IPAddress ipAddress) {
        this.parkId = parkId;
        this.parkName = parkName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.score = score;
        this.parkFee = parkFee;
        this.parkAddress = parkAddress;
        this.parkSpace=parkSpace;
        this.ipAddress=ipAddress;
    }

    public ParkInfoResponse() {
    }
}
