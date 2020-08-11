package org.zt.model.response;

public class ParkingLogResponse {
    private int logId;
    private String enterTime;
    private String leaveTime;
    private String carPlate;
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
