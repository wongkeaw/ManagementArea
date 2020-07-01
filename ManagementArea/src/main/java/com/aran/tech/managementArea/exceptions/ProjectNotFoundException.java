package com.aran.tech.managementArea.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author oawon
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectNotFoundException extends RuntimeException {
    
	private static final long serialVersionUID = -1917321711833265697L;

	public ProjectNotFoundException(String message) {
        super(message);
    }
}
