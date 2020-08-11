package org.zt.model.request;

public class RecommendRequest {
	
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
