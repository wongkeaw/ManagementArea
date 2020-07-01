package com.aran.tech.managementArea.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author oawon
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends RuntimeException  {

	private static final long serialVersionUID = 2226627812261879356L;

	public UserNotFoundException(String message) {
		super(message);
	}
	
	

}
