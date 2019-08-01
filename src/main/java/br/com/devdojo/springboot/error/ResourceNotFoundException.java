package br.com.devdojo.springboot.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3513241270934643439L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
