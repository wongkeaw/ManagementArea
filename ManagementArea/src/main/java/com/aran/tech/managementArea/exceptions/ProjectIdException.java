package com.aran.tech.managementArea.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author oawon
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException {

	private static final long serialVersionUID = -5866080325478598874L;

	public ProjectIdException(String message) {
        super(message);
    }
}
