package com.neulogics.GoParcel.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neulogics.GoParcel.exception.FileUploadException;
import com.neulogics.GoParcel.exception.ResourceNotFoundException;
import com.neulogics.GoParcel.model.Address;
import com.neulogics.GoParcel.model.RiderDetails;
import com.neulogics.GoParcel.model.User;
import com.neulogics.GoParcel.payload.ApiResponse;
import com.neulogics.GoParcel.payload.ErrorResponse;
import com.neulogics.GoParcel.payload.ResetPasswordRequest;
import com.neulogics.GoParcel.payload.SetNewPasswordPayload;
import com.neulogics.GoParcel.payload.UserIdentityAvailability;
import com.neulogics.GoParcel.payload.RiderDetailsRequest;
import com.neulogics.GoParcel.repository.RiderDetailsRepository;
import com.neulogics.GoParcel.repository.UserRepository;
import com.neulogics.GoParcel.security.CustomUserDetailsService;
import com.neulogics.GoParcel.services.EmailService;
import com.neulogics.GoParcel.services.ParcelService;
import com.neulogics.GoParcel.utils.Utilities;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ParcelService parcelService;
	
	@Autowired
	private RiderDetailsRepository riderDetailsRepository;
	
	@Autowired
	private Utilities utilities;
	
	@Autowired
	private EmailService emailService;
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/resetPassword")
	public ApiResponse resetPassword(HttpServletRequest request,@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
		Optional<User> user = userRepository.findByEmail(resetPasswordRequest.getEmail());
		if (user.isEmpty()) {
			throw new ResourceNotFoundException("Email does not exist");
			}
		
		String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(user.get(), token);
		String appUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getLocalPort();
		String link = appUrl + "/users/changePassword?token=" + token;
		String emailMsg = "Click the link below to reset password \n" + link;
		Map<String, String> resetLink = new HashMap<>();
	
		resetLink.put("link",link);
		String message = "Reset Link Sent!!";
		emailService.sendEmail(user.get().getEmail(),"Reset Password",emailMsg);
		return new ApiResponse(HttpStatus.OK.value(),message);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/changePassword")
	public ApiResponse showChangePasswordPage(HttpServletRequest request,@RequestParam("token") String token) {
		String result = utilities.validatePasswordResetToken(token);
	if(result == null) {
		throw new ResourceNotFoundException("Token expired or invalid.\n Request for a fresh password reset link");
		} else {
			
			String appUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getLocalPort();
			String link = appUrl + "/users/resetPassword";
			
			Map<String, String> resetLink = new HashMap<>();
			resetLink.put("link",link);
			resetLink.put("email",result);
			String message = "Click the link below to update password";
			
			utilities.deletePasswordResetToken(token);
			return new ApiResponse(HttpStatus.OK.value(),message,resetLink);
		}
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/savePassword")
	public ApiResponse saveNewPassword(@Valid @RequestBody SetNewPasswordPayload payload) {
		Optional<User> user = userRepository.findByEmail(payload.getEmail());
		utilities.changeUserPassword(user.get(), payload.getNewPassword());
		
		return new ApiResponse(HttpStatus.OK.value(),"Successfully changed password",null);
		
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/{riderId}/riderDetails",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}) 
	public Object saveUserDetails(@PathVariable("riderId") Long riderId,@ModelAttribute("riderDetailsRequest") RiderDetailsRequest request) {
		
		
		
		// Check if user exist
		Optional<User> optionalUser = userRepository.findById(riderId);
		User user = new User();
		if(optionalUser.isPresent()) {
			user = optionalUser.get();
		}else {
			throw new ResourceNotFoundException("Rider with id: "+ riderId + " does not exist");
		}
		// Validate files
		RiderDetails riderDetails = new RiderDetails();
		riderDetails.setCity(request.getCity());
		riderDetails.setStreet(request.getStreet());
		riderDetails.setState(request.getState());
		
		List<MultipartFile> files = Arrays.asList(request.getPhoto(),request.getPhoto());
		
		//Save files to cloudinary
		
		for(MultipartFile file : files) {
			
			List<String> result = utilities.validateAndUploadFile(file);
			
			if( result!= null) {
				return new ErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),"File upload failed",result);
			}
			
		}
		try {
			riderDetails.setPhotoUrl(utilities.uploadFileToCloud(request.getPhoto()));
			riderDetails.setIdCardImageUrl(utilities.uploadFileToCloud(request.getIdCardImage()));
		} catch (IOException e) {
			throw new FileUploadException(e.getMessage());
		}
		
		System.out.println(riderDetails);
		// Return Response
		return new ApiResponse(HttpStatus.CREATED.value(),"Rider details saved",riderDetailsRepository.save(riderDetails));
		
	}
	@GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }
    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }
    
    
    @GetMapping("/{userId}/parcels")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> getAllUserParcels(@PathVariable("userId") long userId) throws Exception {
		ApiResponse response = new ApiResponse(HttpStatus.OK.value(),"All parcel orders for user with User Id: "+userId,parcelService.getParcelByUserId(userId));
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
}

	
	
