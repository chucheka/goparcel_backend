package com.neulogics.GoParcel.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.neulogics.GoParcel.dto.ParcelDto;
import com.neulogics.GoParcel.dto.ParcelLocationDto;
import com.neulogics.GoParcel.dto.StatusDto;
import com.neulogics.GoParcel.payload.ApiResponse;
import com.neulogics.GoParcel.security.CurrentUser;
import com.neulogics.GoParcel.security.UserPrincipal;
import com.neulogics.GoParcel.services.ParcelService;






@RestController
@RequestMapping("/api/v1")
public class ParcelController {
	
	@Autowired
	private ParcelService parcelService;
	
	@GetMapping("/")
	public String getHome() {
		return "Welcome to sendIT APP Rest Services";
	}
	
	@GetMapping("/parcels/{parcelId}")
	@PreAuthorize("hasRole('USER') or hasRole('RIDER') or hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> getParcelById(@PathVariable("parcelId") long parcelId) throws Exception{
		ApiResponse response = new ApiResponse(HttpStatus.OK.value(),"Parcel fetch successful",parcelService.getParcelById(parcelId));
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	//CREATE PARCEL ORDER
	@PostMapping("/parcels")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> createParcelOrder(@Valid @RequestBody ParcelDto newParcel,@CurrentUser UserPrincipal currentUser) throws Exception{
		ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(),"Parcel Order created successfully",parcelService.createParcelOrder(newParcel,currentUser));
		return new ResponseEntity<ApiResponse>(response,HttpStatus.CREATED);
}
	
	@GetMapping("/parcels")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> getAllParcelOrders(HttpServletRequest request) throws Exception{
		ApiResponse response = new ApiResponse(HttpStatus.OK.value(),"All Parcels fetch success",parcelService.getAllParcelOrders());
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	@PutMapping("/parcels/{parcelId}/cancel")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<ApiResponse> cancelParcelOrder(@PathVariable("parcelId") Long parcelId,@CurrentUser UserPrincipal currentUser) throws Exception {
		ApiResponse response = new ApiResponse(HttpStatus.OK.value(),"Parcel Order Cancelled!",parcelService.cancelParcelOrder(parcelId,currentUser));
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@PutMapping("/parcels/{parcelId}/present_location")
	@PreAuthorize("hasRole('RIDER') or hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> updateParcelLocation(@Valid @RequestBody ParcelLocationDto newLocation,@PathVariable("parcelId") Long parcelId) throws Exception {
		ApiResponse response = new ApiResponse(HttpStatus.OK.value(),"Parcel Order location updated!",parcelService.updateParcelLocation(newLocation,parcelId, null));
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}

	@PutMapping("/parcels/{parcelId}/destination")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> changeParcelDestination(@Valid @RequestBody ParcelLocationDto newDestinationDto,@PathVariable("parcelId") Long parcelId,@CurrentUser UserPrincipal currentUser) throws Exception {
		ApiResponse response = new ApiResponse(HttpStatus.OK.value(),"Parcel Order destination changed!",parcelService.changeDestination(newDestinationDto,parcelId, currentUser));
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@PutMapping("/parcels/{parcelId}/status")
	@PreAuthorize("hasRole('ADMIN') or hasRole('RIDER')")
	public ResponseEntity<ApiResponse> changeParcelStatus(@Valid @RequestBody StatusDto statusDto,@PathVariable("parcelId") Long parcelId) throws Exception {
		ApiResponse response = new ApiResponse(HttpStatus.OK.value(),"Parcel Order status changed!",parcelService.changeParcelStatus(statusDto,parcelId));
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	
//	@GetMapping("/parcels")
//	public UserPrincipal getlParcelOrders(@CurrentUser UserPrincipal currentUser) {
//		emailService.sendEmail(currentUser.getEmail(), "Here are your parcels", "Your Parcels have been delievered");;
//		return currentUser;
//	}
//	
}
