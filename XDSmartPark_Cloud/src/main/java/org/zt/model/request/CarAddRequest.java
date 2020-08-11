package org.zt.model.request;


public class CarAddRequest{

    public int userId;

    public String carPlate;

    public CarAddRequest(int userId, String carPlate) {
        this.userId = userId;
        this.carPlate = carPlate;
    }

    public CarAddRequest() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }
}
