package org.zt.xdsmartpark.model.request;

import com.google.gson.annotations.SerializedName;

public class CarListRequest {

    @SerializedName("userId")
    int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public CarListRequest(int userId) {
        this.userId = userId;
    }
    public CarListRequest() {
    }

}
