package com.myroom.user.model;

public class UserReservation {

	private int id;
	private int realEstateId;
	private String jibunAddress;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getRealEstateId() {
		return realEstateId;
	}
	public void setRealEstateId(int realEstateId) {
		this.realEstateId = realEstateId;
	}
	public String getJibunAddress() {
		return jibunAddress;
	}
	public void setJibunAddress(String jibunAddress) {
		this.jibunAddress = jibunAddress;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
