package org.zt.model.request;

public class UserFavoriteListRequest {
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
