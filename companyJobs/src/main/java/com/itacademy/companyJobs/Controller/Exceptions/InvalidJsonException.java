package com.itacademy.companyJobs.Controller.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PARTIAL_CONTENT) 
public class InvalidJsonException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidJsonException(String message) {
		super(message);
	}
	
	

}
