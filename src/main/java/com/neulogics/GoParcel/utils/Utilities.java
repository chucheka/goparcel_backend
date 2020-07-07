package com.neulogics.GoParcel.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.utils.ObjectUtils;
import com.neulogics.GoParcel.config.CloudinaryConfig;
import com.neulogics.GoParcel.exception.FileUploadException;
import com.neulogics.GoParcel.model.PasswordResetToken;
import com.neulogics.GoParcel.model.User;
import com.neulogics.GoParcel.repository.PasswordTokenRepository;
import com.neulogics.GoParcel.repository.UserRepository;

@Component
public class Utilities {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    CloudinaryConfig cloudc;

	
	@Autowired
	private PasswordTokenRepository passwordTokenRepository;

		public String validatePasswordResetToken(String token) {
	
			final Optional<PasswordResetToken> passToken = passwordTokenRepository.findByToken(token);
			
			System.out.println(passToken.get().getUser());
			String str = passToken.isEmpty()?null:passToken.get().getUser().getEmail();
			
			return str;
		
			
		}
		public void deletePasswordResetToken(String token) {
			
			Optional<PasswordResetToken> passToken = passwordTokenRepository.findByToken(token);
			
			if(!passToken.isEmpty()) {
				passwordTokenRepository.delete(passToken.get());
			}
			
		}
//		
//		private boolean isTokenExpired(PasswordResetToken passToken) {
//			final Calendar cal = Calendar.getInstance();
//			return passToken.getExpiryDate().before(cal.getTime());
//			}
		
		public void changeUserPassword(User user, String password) {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			user.setPassword(bCryptPasswordEncoder.encode(password));
			userRepository.save(user);
			}
		public List<String> validateAndUploadFile(MultipartFile file) {
			List<String> errors  = new ArrayList<>();
			final long FILE_SIZE_BYTES = 204800;
			
			Optional<MultipartFile> optionalFile = Optional.ofNullable(file);
			
				
			
				 optionalFile.ifPresent(sourceFile->{
						if(sourceFile.isEmpty() || sourceFile.getSize() == 0) {
							errors.add("Please select a file for " + sourceFile.getName());
							
						}
						
						if(!(sourceFile.getContentType().equalsIgnoreCase("image/jpg") ||sourceFile.getContentType().equalsIgnoreCase("image/jpeg")||sourceFile.getContentType().equalsIgnoreCase("image/png"))) {
							errors.add("Only jpg/jpeg/png files are supported");
						}
						
						if(sourceFile.getSize() > FILE_SIZE_BYTES) {
							errors.add("File size should be 200kb max");
						}
						
					});
				 
				if (errors.isEmpty()) {
					return null;
			}else {
				return errors;
			}
			
			
			
			
		}
		public String uploadFileToCloud(MultipartFile sourceFile) throws IOException {
			Map uploadResult =  cloudc.upload(sourceFile.getBytes(), 
			    	ObjectUtils.asMap("folder","goparcel/img",
			    			"use_filename",false));
			String secure_url = "secure_url";
			return (String)uploadResult.get("secure_url");
		}
		
}
