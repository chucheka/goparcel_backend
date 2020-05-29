package com.neulogics.GoParcel.interfaces;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.neulogic.pollingApp.payload.SignUpRequest;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {};
	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context){
		SignUpRequest signUpCredentials = (SignUpRequest) obj;
		return signUpCredentials.getPassword().equals(signUpCredentials.getConfirmPassword());
		}
}
