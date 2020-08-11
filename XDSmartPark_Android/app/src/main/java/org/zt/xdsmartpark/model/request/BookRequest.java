package org.zt.xdsmartpark.model.request;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class BookRequest {

    @SerializedName("userId")
    public int userId;
    @SerializedName("parkId")
    public int parkId;
    @SerializedName("orderTime")
    public Timestamp orderTime;

    public BookRequest() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }
}
