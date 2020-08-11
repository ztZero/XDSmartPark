package org.zt.model.response;

public class GetParkLocationResponse {
	
	private int parkId;
    private double latitude;
    private double longitude;

    public GetParkLocationResponse() {
    }

    public GetParkLocationResponse(int parkId, double latitude, double longitude) {
        this.parkId = parkId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
