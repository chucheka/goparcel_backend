package com.neulogics.GoParcel.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Address {

	@Size(max = 100)
	private String address;	
	
	@Size(max = 100)
	private String city;
	
	@Size(max = 100)
	private String state;
	

	public Address() {
		
	}

	

	public Address(String address, String city, String state) {
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
