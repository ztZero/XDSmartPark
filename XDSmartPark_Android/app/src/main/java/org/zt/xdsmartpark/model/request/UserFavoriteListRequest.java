package org.zt.xdsmartpark.model.request;

import com.google.gson.annotations.SerializedName;

public class UserFavoriteListRequest {
    @SerializedName("userId")
    private int userId;

    public UserFavoriteListRequest(int userId) {
        this.userId = userId;
    }

    public UserFavoriteListRequest() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
