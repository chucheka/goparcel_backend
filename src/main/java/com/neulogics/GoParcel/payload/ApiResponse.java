package com.neulogics.GoParcel.payload;

public class ApiResponse {

	private int status;
    private String message;
    private Object payload;
    
	public ApiResponse(int status, String message, Object payload) {
		super();
		this.status = status;
		this.message = message;
		this.payload = payload;
	}

	public ApiResponse() {
		
	}

	public ApiResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int i) {
		this.status = i;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

    
    
}
