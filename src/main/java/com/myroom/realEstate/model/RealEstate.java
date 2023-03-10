package com.myroom.realEstate.model;

import java.util.Date;

public class RealEstate {

	private int id;
	private int userId;
	private String sales_type;
	private String service_type;
	private String sales_price;
	private int deposit;
	private int monthRentPrice;
	private double area;
	private String address;
	private String jibunAddress;
	private String local1;
	private String local2;
	private String local3;
	private String local4;
	private String title;
	private String residence_type;
	private String description;
	private int realtor_id;
	private String realtor_comment;
	private Date createdAt;
	private Date updatedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSales_type() {
		return sales_type;
	}

	public void setSales_type(String sales_type) {
		this.sales_type = sales_type;
	}

	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

	public String getSales_price() {
		return sales_price;
	}

	public void setSales_price(String sales_price) {
		this.sales_price = sales_price;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public int getMonthRentPrice() {
		return monthRentPrice;
	}

	public void setMonthRentPrice(int monthRentPrice) {
		this.monthRentPrice = monthRentPrice;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJibunAddress() {
		return jibunAddress;
	}

	public void setJibunAddress(String jibunAddress) {
		this.jibunAddress = jibunAddress;
	}

	public String getLocal1() {
		return local1;
	}

	public void setLocal1(String local1) {
		this.local1 = local1;
	}

	public String getLocal2() {
		return local2;
	}

	public void setLocal2(String local2) {
		this.local2 = local2;
	}

	public String getLocal3() {
		return local3;
	}

	public void setLocal3(String local3) {
		this.local3 = local3;
	}

	public String getLocal4() {
		return local4;
	}

	public void setLocal4(String local4) {
		this.local4 = local4;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getResidence_type() {
		return residence_type;
	}

	public void setResidence_type(String residence_type) {
		this.residence_type = residence_type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRealtor_id() {
		return realtor_id;
	}

	public void setRealtor_id(int realtor_id) {
		this.realtor_id = realtor_id;
	}

	public String getRealtor_comment() {
		return realtor_comment;
	}

	public void setRealtor_comment(String realtor_comment) {
		this.realtor_comment = realtor_comment;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
