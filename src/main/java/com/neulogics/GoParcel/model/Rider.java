package com.neulogics.GoParcel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;




@Entity
@Table(name="riders")
public class Rider extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@OneToMany(orphanRemoval = false,
            fetch = FetchType.LAZY,
            mappedBy = "rider")
	@JsonManagedReference
    private List<Parcel> parcels = new ArrayList<>();

	
	private boolean verified;


	
	public Rider() {
	
	}



	public Rider(List<Parcel> parcels, boolean verified) {
		super();
		this.parcels = parcels;
		this.verified = verified;
	}
	
	public void removeParcelOrder(Parcel parcelOrder) {
		parcels.remove(parcelOrder);
		parcelOrder.setRider(null);
	}

	

	public void addParcelOrder(Parcel parcelOrder) {
		this.parcels.add(parcelOrder);
		parcelOrder.setRider(this);
	}
	

	public List<Parcel> getParcels() {
		return parcels;
	}



	public void setParcels(List<Parcel> parcels) {
		this.parcels = parcels;
	}



	public boolean isVerified() {
		return verified;
	}



	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	
	
	
	
}
