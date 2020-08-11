package org.zt.model.request;


public class GetParkLocationRequest {
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
