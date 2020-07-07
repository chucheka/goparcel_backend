package com.neulogics.GoParcel.controllers;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.neulogics.GoParcel.payload.LoginRequest;
import com.neulogics.GoParcel.payload.SignUpRequest;
import com.neulogics.GoParcel.services.AuthService;




@RestController
@RequestMapping("/api/v1/auth")
public class AuthController{
	@Autowired
	AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	return authService.authenticateUser(loginRequest);
    	    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    return authService.registerUser(signUpRequest);
    }
}