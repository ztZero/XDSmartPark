package org.zt.entity;

public class ParkingLot {
	
	private int parkId;
    private String parkName;
    private String parkAddress;
    private double latitude;
    private double longitude;
    private double score;
    private double parkFee;
    private int parkSpace;
    private String ip;
    private int port;
    
    
	public int getParkSpace() {
		return parkSpace;
	}
	public void setParkSpace(int parkSpace) {
		this.parkSpace = parkSpace;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getParkId() {
		return parkId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public String getParkAddress() {
		return parkAddress;
	}
	public void setParkAddress(String parkAddress) {
		this.parkAddress = parkAddress;
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
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public double getParkFee() {
		return parkFee;
	}
	public void setParkFee(double parkFee) {
		this.parkFee = parkFee;
	}

}
