package org.zt.model.request;

public class CarDeleteRequest {
	
    int userId;
    int carId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public CarDeleteRequest(int userId, int carId) {
        this.userId = userId;
        this.carId = carId;
    }
    public CarDeleteRequest() {
    }
}
