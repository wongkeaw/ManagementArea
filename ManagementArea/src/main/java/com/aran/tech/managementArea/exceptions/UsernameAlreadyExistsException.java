package com.aran.tech.managementArea.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author oawon
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -3925103016472401387L;

	public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
