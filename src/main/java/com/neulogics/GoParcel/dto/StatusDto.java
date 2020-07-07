package com.neulogics.GoParcel.dto;

import javax.validation.constraints.NotNull;

import com.neulogics.GoParcel.model.Status;


public class StatusDto {

	@NotNull
	private Status status;

	
	public StatusDto() {
	}

	public StatusDto( Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
