package org.zt.xdsmartpark.model.request;

import com.google.gson.annotations.SerializedName;

public class CarStatusRequest {

    @SerializedName("userId")
    int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public CarStatusRequest(int userId) {
        this.userId = userId;
    }
    public CarStatusRequest() {
    }
}
