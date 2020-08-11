package org.zt.model.response;


public class CarListResponse {

	private int carId;
    private int userId;
    private String carPlate;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
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

    public CarListResponse() {
    }

    public CarListResponse(int carId, int userId, String carPlate) {
        this.carId = carId;
        this.userId = userId;
        this.carPlate = carPlate;
    }
}
