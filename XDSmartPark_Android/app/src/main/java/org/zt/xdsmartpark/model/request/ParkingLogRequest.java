package org.zt.xdsmartpark.model.request;


import com.google.gson.annotations.SerializedName;

public class ParkingLogRequest{


    @SerializedName("userId")
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
