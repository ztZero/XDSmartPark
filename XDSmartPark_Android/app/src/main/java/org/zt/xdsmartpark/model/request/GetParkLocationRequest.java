package org.zt.xdsmartpark.model.request;

import com.google.gson.annotations.SerializedName;

public class GetParkLocationRequest {
    @SerializedName("parkId")
    private int parkId;

    public GetParkLocationRequest(int parkId) {
        this.parkId = parkId;
    }

    public GetParkLocationRequest() {
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }
}
