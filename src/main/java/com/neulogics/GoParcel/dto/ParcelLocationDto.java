package com.neulogics.GoParcel.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ParcelLocationDto {

	@NotNull
	@Size(min=1,max=255,message="Address field must be between 1-255 characters")
	private String address;
	@NotNull
	@Size(min=1,max=40,message="City field must be between 1-40 characters")
	private String city;
	@NotNull
	@Size(min=1,max=40,message="State field must be between 1-40 characters")
	private String state;
	public ParcelLocationDto() {
	
	}
	public ParcelLocationDto( String address,String city,String state) {
		this.address = address;
		this.city = city;
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
