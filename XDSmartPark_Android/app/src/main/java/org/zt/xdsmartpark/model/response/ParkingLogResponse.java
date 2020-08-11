package org.zt.xdsmartpark.model.response;

import com.google.gson.annotations.SerializedName;

public class ParkingLogResponse {
    @SerializedName("logId")
    private int logId;
    @SerializedName("enterTime")
    private String enterTime;
    @SerializedName("leaveTime")
    private String leaveTime;
    @SerializedName("carPlate")
    private String carPlate;
    @SerializedName("parkName")
    private String parkName;

    public ParkingLogResponse() {
    }

    public ParkingLogResponse(int logId, String enterTime, String leaveTime, String carPlate, String parkName) {
        this.logId = logId;
        this.enterTime = enterTime;
        this.leaveTime = leaveTime;
        this.carPlate = carPlate;
        this.parkName = parkName;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }
}
