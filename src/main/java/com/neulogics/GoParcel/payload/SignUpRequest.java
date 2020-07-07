package com.neulogics.GoParcel.payload;

import java.util.Set;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.neulogics.GoParcel.interfaces.PasswordMatches;



@PasswordMatches(message="Password fields must match")
public class SignUpRequest {

    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @NotBlank
    @Size(max = 40,message="Email is too long")
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
    private String confirmPassword;
 
	@NotBlank
	@Size(min=11,max=14,message="Phone number must not be more then 14 digits")
	private String phoneNumber1;
	
	
	@Size(min=11,max=14,message="Phone number must not be more then 14 digits")
	private String phoneNumber2;
	
    private Set<String> roles;

    public SignUpRequest() {
		 
	 }
    
	public SignUpRequest(String username,String email,String password, String confirmPassword,String phoneNumber1,
			String phoneNumber2,String address,String city,String state,boolean verified,String image, Set<String> roles) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.phoneNumber1 = phoneNumber1;
		this.phoneNumber2 = phoneNumber2;
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}
	

	public Set<String> getRoles() {
		return roles;
	}

	public void setRole(Set<String> roles) {
		this.roles = roles;
	}
    
	
   	
}