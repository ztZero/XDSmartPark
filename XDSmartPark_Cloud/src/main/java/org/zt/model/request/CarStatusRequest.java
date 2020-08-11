package org.zt.model.request;


public class CarStatusRequest {

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
