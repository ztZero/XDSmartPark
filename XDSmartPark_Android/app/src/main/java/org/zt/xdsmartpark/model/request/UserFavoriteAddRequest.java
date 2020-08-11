package org.zt.xdsmartpark.model.request;

import com.google.gson.annotations.SerializedName;

public class UserFavoriteAddRequest {
    @SerializedName("userId")
    public int userId;
    @SerializedName("parkId")
    public int parkId;

    public UserFavoriteAddRequest() {
    }

    public UserFavoriteAddRequest(int userId, int parkId) {
        this.userId = userId;
        this.parkId = parkId;
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
}
