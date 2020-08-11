package org.zt.model.request;

public class UserFavoriteDeleteRequest {
    public int favoriteId;
    public int userId;

    public UserFavoriteDeleteRequest(int favoriteId, int userId) {
        this.favoriteId = favoriteId;
        this.userId = userId;
    }

    public UserFavoriteDeleteRequest() {
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
