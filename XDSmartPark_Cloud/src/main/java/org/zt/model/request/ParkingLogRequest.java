package org.zt.model.request;



public class ParkingLogRequest{


    public int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ParkingLogRequest() {
    }

    public ParkingLogRequest(int userId) {
        this.userId = userId;
    }
}
