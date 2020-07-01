package com.aran.tech.managementArea.exceptions;

public class FileNotFoundResponse {

	private String filename ;

	
	public FileNotFoundResponse(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}
