package com.aran.tech.managementArea.payload;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class FetchUploadFileResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7546498095460190819L;
	@Getter
    @Setter
	private String fileName ;
	public FetchUploadFileResponse(String fileName) {
		super();
		this.fileName = fileName;
	}
	
}
