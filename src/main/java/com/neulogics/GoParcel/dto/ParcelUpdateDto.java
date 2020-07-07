package com.neulogics.GoParcel.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ParcelUpdateDto {

	@NotNull
	@Size(min=1,max=255,message="Value incorrect!")
	private String fieldName;

	public ParcelUpdateDto() {
		
	}
	public ParcelUpdateDto(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	
	
}
