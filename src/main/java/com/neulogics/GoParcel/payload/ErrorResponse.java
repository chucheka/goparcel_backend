package com.neulogics.GoParcel.payload;

import java.util.List;

public class ErrorResponse {

	private int status;
	private String message;
	private long timeStamp;
	List<String> errors;
	
	
	public ErrorResponse() {
		super();
	}


	public ErrorResponse(int status, String message, long timeStamp, List<String> errors) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
		this.errors = errors;
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


	public long getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}


	public List<String> getErrors() {
		return errors;
	}


	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	
	
}
