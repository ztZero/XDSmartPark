package org.zt.xdsmartpark.model.request;


import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class CarAddRequest{

    @SerializedName("userId")
    public int userId;

    @SerializedName("carPlate")
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
