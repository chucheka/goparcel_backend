package com.neulogics.GoParcel.payload;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;




public class RiderDetailsRequest {

	@NotNull
	private MultipartFile photo;
	
	@NotNull
	private  MultipartFile idCardImage;
	
	@NotNull
	@Size(max = 150,message = "Address must be 150 characters long")
	private String street;
	
	@NotNull
	@Size(max = 40)
	private String city;
	
	@NotNull
	@Size(max = 40)
	private String state;

	public RiderDetailsRequest() {
	}

	public RiderDetailsRequest(MultipartFile photo,MultipartFile idCardImage,String street,String city, String state) {
		this.photo = photo;
		this.idCardImage = idCardImage;
		this.street = street;
		this.city = city;
		this.state = state;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	public MultipartFile getIdCardImage() {
		return idCardImage;
	}

	public void setIdCardImage(MultipartFile idCardImage) {
		this.idCardImage = idCardImage;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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
