package com.neulogics.GoParcel.exception;




import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.SendFailedException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.neulogics.GoParcel.payload.ErrorResponse;




@ControllerAdvice
public class CustomGlobalExceptionHandler{
	
	@ExceptionHandler({AccessDeniedException.class})
	public ResponseEntity<ErrorResponse> handleAccessDeniedException(Exception ex,WebRequest req){
		ErrorResponse error = new ErrorResponse();
				error.setStatus(HttpStatus.FORBIDDEN.value());
				error.setMessage(ex.getMessage());
				error.setTimeStamp(System.currentTimeMillis());
				return new ResponseEntity<ErrorResponse>(error,HttpStatus.FORBIDDEN);	
						
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		
		ErrorResponse error = new ErrorResponse();
		
		List<String> errors =  ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(err -> err.getDefaultMessage())
				.collect(Collectors.toList());
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("Invalid Input(s)");
		error.setTimeStamp(System.currentTimeMillis());
		error.setErrors(errors);
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> constraintViolationException(ConstraintViolationException ex) {
	
		ErrorResponse error = new ErrorResponse();
		
		List<String> errors = new ArrayList<>();
		
		errors.add(ex.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		error.setErrors(errors);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException exc){
	ErrorResponse error = new ErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(BadRequestException exc){
		ErrorResponse error = new ErrorResponse();
		
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(AppException exc){
		ErrorResponse error = new ErrorResponse();
		
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(exc.getLocalizedMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(ActionNotAllowedException exc){
		ErrorResponse error = new ErrorResponse();
		
		
		error.setStatus(HttpStatus.EXPECTATION_FAILED.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(SendFailedException exc){
		ErrorResponse error = new ErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}
