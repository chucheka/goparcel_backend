package com.neulogics.GoParcel.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.neulogics.GoParcel.exception.ResourceNotFoundException;
import com.neulogics.GoParcel.model.Role;
import com.neulogics.GoParcel.model.RoleName;
import com.neulogics.GoParcel.model.User;
import com.neulogics.GoParcel.payload.ApiResponse;
import com.neulogics.GoParcel.payload.ErrorResponse;
import com.neulogics.GoParcel.payload.JwtAuthenticationResponse;
import com.neulogics.GoParcel.payload.LoginRequest;
import com.neulogics.GoParcel.payload.SignUpRequest;
import com.neulogics.GoParcel.repository.RoleRepository;
import com.neulogics.GoParcel.repository.UserRepository;
import com.neulogics.GoParcel.security.JwtTokenProvider;
import com.neulogics.GoParcel.security.UserPrincipal;

@Service
public class AuthService {
	 @Autowired
	    AuthenticationManager authenticationManager;

	    @Autowired
	    UserRepository userRepository;

	    @Autowired
	    RoleRepository roleRepository;

	    @Autowired
	    PasswordEncoder passwordEncoder;

	    @Autowired
	    JwtTokenProvider tokenProvider;

	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
		

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		

		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));



	}

	public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) {
		
		if(userRepository.existsByUsername(signUpRequest.getUsername())) {
        	ErrorResponse response = new ErrorResponse();
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setMessage("Username is already taken!");
			List<String> errors = new ArrayList<>();
			errors.add("Username is already taken!");
			response.setErrors(errors);
			return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
        	ErrorResponse response = new ErrorResponse();
        			response.setStatus(HttpStatus.BAD_REQUEST.value());
        			response.setMessage("Email Address already in use!");
        			List<String> errors = new ArrayList<>();
        			errors.add("Email Address already in use!");
        			response.setErrors(errors);
            return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getUsername(),signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<String> strPhoneNumbers = new HashSet<>();
        strPhoneNumbers.add(signUpRequest.getPhoneNumber1());
        strPhoneNumbers.add(signUpRequest.getPhoneNumber2());
        
        
        
        Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new ResourceNotFoundException("User Role not set."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
							.orElseThrow(() -> new ResourceNotFoundException("Admin Role not set."));
					roles.add(adminRole);
					break;
				case "rider":
					Role riderRole = roleRepository.findByName(RoleName.ROLE_RIDER)
							.orElseThrow(() -> new ResourceNotFoundException("Rider Role not set."));
					roles.add(riderRole);
					break;
				default:
					Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
							.orElseThrow(() -> new ResourceNotFoundException("User Role not set."));
					roles.add(userRole);
				}
			});
		}
		
		
		
		user.getPhoneNumbers().addAll(strPhoneNumbers);
		
		user.setRoles(roles);
		System.out.println(user.getPhoneNumbers());
		
        User result = userRepository.save(user);
        
        result.setPassword("********");
        ApiResponse response = new ApiResponse();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("User registration successfully created");
        response.setPayload(result);
        return new ResponseEntity<ApiResponse>(response,HttpStatus.CREATED);
 
	}

}
