package org.zt.xdsmartpark.model.response;

import com.google.gson.annotations.SerializedName;

public class UserFavoriteResponse {
    @SerializedName("favoriteId")
    private int favoriteId;
    @SerializedName("parkName")
    private String parkName;
    @SerializedName("parkId")
    private int parkId;
    @SerializedName("parkFee")
    private double parkFee;

    public UserFavoriteResponse() {
    }

    public UserFavoriteResponse(int favoriteId, String parkName, int parkId, double parkFee) {
        this.favoriteId = favoriteId;
        this.parkName = parkName;
        this.parkId = parkId;
        this.parkFee = parkFee;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    public double getParkFee() {
        return parkFee;
    }

    public void setParkFee(double parkFee) {
        this.parkFee = parkFee;
    }
}
