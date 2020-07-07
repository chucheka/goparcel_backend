package com.neulogics.GoParcel.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ParcelDto {
	@NotNull
	@Size(min=1,max=255,message="Destination address is too long")
	private String dest_address;
	@NotNull
	@Size(min=1,max=40,message="Destination city is too long")
	private String dest_city;
	@NotNull
	@Size(min=1,max=40,message="Destination state is too long")
	private String dest_state;
	@NotNull
	@Size(min=1,max=255,message="Pick up address is too long")
	private String pickup_address;
	@NotNull
	@Size(min=1,max=40,message="Pick up city is too long")
	private String pickup_city;
	@NotNull
	@Size(min=1,max=40,message="Pick up state is too long")
	private String pickup_state;
	@NotNull
	private Double price;
	
	public ParcelDto() {
		
	}
	public ParcelDto(String dest_address, String dest_city, String dest_state, String pickup_address,
			String pickup_city, String pickup_state, Double price) {
		this.dest_address = dest_address;
		this.dest_city = dest_city;
		this.dest_state = dest_state;
		this.pickup_address = pickup_address;
		this.pickup_city = pickup_city;
		this.pickup_state = pickup_state;
		this.price = price;
	}
	public String getDest_address() {
		return dest_address;
	}
	public void setDest_address(String dest_address) {
		this.dest_address = dest_address;
	}
	public String getDest_city() {
		return dest_city;
	}
	public void setDest_city(String dest_city) {
		this.dest_city = dest_city;
	}
	public String getDest_state() {
		return dest_state;
	}
	public void setDest_state(String dest_state) {
		this.dest_state = dest_state;
	}
	public String getPickup_address() {
		return pickup_address;
	}
	public void setPickup_address(String pickup_address) {
		this.pickup_address = pickup_address;
	}
	public String getPickup_city() {
		return pickup_city;
	}
	public void setPickup_city(String pickup_city) {
		this.pickup_city = pickup_city;
	}
	public String getPickup_state() {
		return pickup_state;
	}
	public void setPickup_state(String pickup_state) {
		this.pickup_state = pickup_state;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
