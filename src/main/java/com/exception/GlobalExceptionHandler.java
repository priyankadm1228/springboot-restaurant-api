package com.smartmenu.backend.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 static class ErrorResponse
	{
		private String message;
		private int status;
		private LocalDateTime timestamp; 
		
		public ErrorResponse(String message, int status)
		{
			this.message = message;
			this.status = status;
			this.timestamp = LocalDateTime.now();
			
		}
		
		public String getMessage()
		{
			return message;
		}
		
		public int getStatus()
		{
			return status;
		}
		
		public LocalDateTime getTimestamp()
		{
			return timestamp;
		}
		
	}
	 
	@ExceptionHandler(NotFoundException.class)
	
	public ResponseEntity<ErrorResponse> handlenotFound(NotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}

}
