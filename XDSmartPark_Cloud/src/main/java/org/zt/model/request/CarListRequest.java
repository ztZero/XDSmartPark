package org.zt.model.request;

public class CarListRequest {
	
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
