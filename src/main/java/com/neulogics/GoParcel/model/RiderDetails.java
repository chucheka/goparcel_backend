package com.neulogics.GoParcel.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.neulogics.GoParcel.audit.UserDateAudit;
import com.neulogics.GoParcel.model.Address;
import com.neulogics.GoParcel.model.User;

@Entity
public class RiderDetails extends UserDateAudit{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long detailId;
	
	@OneToOne
	@JoinColumn(name = "rider_id")
	private User user;
	

	private String photoUrl;
	
	private  String idCardImageUrl;
	
	@NotNull
	@Size(max = 150)
	private String street;
	
	@NotNull
	@Size(max = 40)
	private String city;
	
	@NotNull
	@Size(max = 40)
	private String state;
	
	
	private boolean verified;


	public RiderDetails() {
	}


	public RiderDetails(Long detailId, User user, String photoUrl, String idCardImageUrl,String street,String city,String state, boolean verified) {
		this.detailId = detailId;
		this.user = user;
		this.photoUrl = photoUrl;
		this.idCardImageUrl = idCardImageUrl;
		this.street = street;
		this.city = city;
		this.state = state;
		this.verified = verified;
	}


	public Long getDetailId() {
		return detailId;
	}


	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getPhotoUrl() {
		return photoUrl;
	}


	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}


	public String getIdCardImageUrl() {
		return idCardImageUrl;
	}


	public void setIdCardImageUrl(String idCardImageUrl) {
		this.idCardImageUrl = idCardImageUrl;
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


	public boolean isVerified() {
		return verified;
	}


	public void setVerified(boolean verified) {
		this.verified = verified;
	}


	@Override
	public String toString() {
		return "RiderDetails [detailId=" + detailId + ", user=" + user + ", photoUrl=" + photoUrl + ", idCardImageUrl="
				+ idCardImageUrl + ", street=" + street + ", city=" + city + ", state=" + state + ", verified="
				+ verified + "]";
	}

		
	
		
}
