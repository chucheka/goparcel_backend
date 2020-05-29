package com.neulogics.GoParcel.controllers;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Positive;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neulogics.GoParcel.exception.ActionNotAllowedException;
import com.neulogics.GoParcel.exception.AppException;
import com.neulogics.GoParcel.exception.BadRequestException;
import com.neulogics.GoParcel.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class ParcelController {

	@GetMapping("/parcels/{parcelId}")
	public String getAllParcelOrders(@Positive @PathVariable Long parcelId) {
		throw new ConstraintViolationException("Bad Request", null);
	}
}
