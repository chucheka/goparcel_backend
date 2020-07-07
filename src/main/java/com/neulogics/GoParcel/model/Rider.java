//package com.neulogics.GoParcel.model;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//
//import javax.persistence.CollectionTable;
//import javax.persistence.Column;
//import javax.persistence.ElementCollection;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//
//
//
//@Entity
//@Table(	name = "riders",
//uniqueConstraints = { 
//	@UniqueConstraint(columnNames = "username"),
//	@UniqueConstraint(columnNames = "email") 
//})
//public class Rider {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	 @GeneratedValue(generator = "user_generator")
//    @SequenceGenerator(
//   		name = "user_generator",
//   		sequenceName = "user_sequence",
//   		initialValue = 1000
//   		)
//	private Long zzzzId;
//
//	@NotBlank
//	@Size(max = 20)
//	private String username;
//
//	@NotBlank
//	@Size(max = 50)
//	@Email
//	private String email;
//
//	@NotBlank
//	@Size(max = 120)
//	private String password;
//
//	@ElementCollection
//	@CollectionTable(name = "user_phone_numbers",joinColumns = @JoinColumn(name = "user_id"))
//	@Column(name = "phone_number",nullable = false)
//	private Set<String> phoneNumbers = new HashSet<>();
//	
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(	name = "user_roles", 
//				joinColumns = @JoinColumn(name = "user_id"), 
//				inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<Role> roles  = new HashSet<>();
//
//	@OneToMany(orphanRemoval = false,
//            fetch = FetchType.LAZY,
//            mappedBy = "rider")
//	@JsonManagedReference
//    private List<Parcel> parcels = new ArrayList<>();
//
//	
//	private boolean verified;
//
//
//	
//	public Rider() {
//	
//	}
//
//
//
//	
//	public Rider(Long userId,String username,String email,String password, Set<String> phoneNumbers, Set<Role> roles, List<Parcel> parcels,
//			boolean verified) {
//		super();
//		this.userId = userId;
//		this.username = username;
//		this.email = email;
//		this.password = password;
//		this.phoneNumbers = phoneNumbers;
//		this.roles = roles;
//		this.parcels = parcels;
//		this.verified = verified;
//	}
//
//
//
//
//	public void removeParcelOrder(Parcel parcelOrder) {
//		parcels.remove(parcelOrder);
//		parcelOrder.setRider(null);
//	}
//
//	
//
//	public void addParcelOrder(Parcel parcelOrder) {
//		this.parcels.add(parcelOrder);
//		parcelOrder.setRider(this);
//	}
//	
//	
//
//	public Long getUserId() {
//		return userId;
//	}
//
//
//
//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}
//
//
//
//	public String getUsername() {
//		return username;
//	}
//
//
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//
//
//	public String getEmail() {
//		return email;
//	}
//
//
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//
//
//	public String getPassword() {
//		return password;
//	}
//
//
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//
//
//	public Set<String> getPhoneNumbers() {
//		return phoneNumbers;
//	}
//
//
//
//	public void setPhoneNumbers(Set<String> phoneNumbers) {
//		this.phoneNumbers = phoneNumbers;
//	}
//
//
//
//	public Set<Role> getRoles() {
//		return roles;
//	}
//
//
//
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}
//
//
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//
//
//	public List<Parcel> getParcels() {
//		return parcels;
//	}
//
//
//
//	public void setParcels(List<Parcel> parcels) {
//		this.parcels = parcels;
//	}
//
//
//
//	public boolean isVerified() {
//		return verified;
//	}
//
//
//
//	public void setVerified(boolean verified) {
//		this.verified = verified;
//	}
//	
//	
//	
//	
//	
//}
