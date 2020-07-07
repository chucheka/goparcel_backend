package com.neulogics.GoParcel.payload;

import java.util.Arrays;
import java.util.List;

public class ErrorResponse {

	private int status;
	private String message;
	private List<String> errors;
	
	public ErrorResponse() {
		
	}
	public ErrorResponse(int status, String message, List<String> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
		}

	public ErrorResponse(int status, String message, String error) {
		super();
		this.status = status;
		this.message = message;
		errors = Arrays.asList(error);
		}

	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	

	public List<String> getErrors() {
		return errors;
	}


	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
}
