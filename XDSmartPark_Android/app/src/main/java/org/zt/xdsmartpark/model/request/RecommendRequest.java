package org.zt.xdsmartpark.model.request;

import com.google.gson.annotations.SerializedName;

public class RecommendRequest {
    @SerializedName("userId")
    private int userId;

    public RecommendRequest() {
    }

    public RecommendRequest(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
