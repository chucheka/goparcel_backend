package com.neulogics.GoParcel.model;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="parcels")
public class Parcel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(generator = "parcel_generator")
     @SequenceGenerator(
    		name = "user_generator",
    		sequenceName = "parcel_sequence",
    		initialValue = 1000
    		)
	private Long parcelId;
	
	@NotBlank
	@Size(max=255)
	private String pickupLocation;
	
	@NotBlank
	@Size(max=255)
	private String destination;
	
	private Double price;
	
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	private String presentLocation;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rider_id")
    @JsonBackReference
    private Rider rider;
    
    public Parcel() {
    	
    }
    
    
	public Parcel(String pickupLocation,String destination, Double price, Status status, String presentLocation,User user, Rider rider) {
		
		this.pickupLocation = pickupLocation;
		this.destination = destination;
		this.price = price;
		this.status = status;
		this.presentLocation = presentLocation;
		this.user = user;
		this.rider = rider;
	}

	public Long getParcelId() {
		return parcelId;
	}

	public void setParcelId(Long parcelId) {
		this.parcelId = parcelId;
	}

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getPresentLocation() {
		return presentLocation;
	}

	public void setPresentLocation(String presentLocation) {
		this.presentLocation = presentLocation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Rider getRider() {
		return rider;
	}


	public void setRider(Rider rider) {
		this.rider = rider;
	}


	

	

//	@Override
//	public String toString() {
//		return "Parcel [parcelId=" + parcelId + ", pickupLocation=" + pickupLocation + ", destination=" + destination
//				+ ", price=" + price + ", status=" + status + ", presentLocation=" + presentLocation + ", user=" + user
//				+ "]";
//	}
	
	
}
