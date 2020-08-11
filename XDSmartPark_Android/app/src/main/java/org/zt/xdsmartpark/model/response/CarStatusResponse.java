package org.zt.xdsmartpark.model.response;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class CarStatusResponse {
    @SerializedName("logId")
    private int logId;
    @SerializedName("parkName")
    private String parkName;
    @SerializedName("carPlate")
    private String carPlate;
    @SerializedName("enterTime")
    private Timestamp enterTime;
    @SerializedName("parkFee")
    private double parkFee;

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Timestamp getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Timestamp enterTime) {
        this.enterTime = enterTime;
    }

    public double getParkFee() {
        return parkFee;
    }

    public void setParkFee(double parkFee) {
        this.parkFee = parkFee;
    }

    public CarStatusResponse(int logId, String parkName, String carPlate, Timestamp enterTime, double parkFee) {
        this.logId = logId;
        this.parkName = parkName;
        this.carPlate = carPlate;
        this.enterTime = enterTime;
        this.parkFee = parkFee;
    }

    public CarStatusResponse() {
    }
}