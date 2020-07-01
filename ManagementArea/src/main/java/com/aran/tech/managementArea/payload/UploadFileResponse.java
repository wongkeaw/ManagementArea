/**
 * 
 */
package com.aran.tech.managementArea.payload;

/**
 * @author oawon
 *
 */
public class UploadFileResponse {
	
	private String imageToken ;
    private String fileName;
    private String fileType;
    private long size;
    private UserReponse user;
    
    public UploadFileResponse(String imageToken , String fileName, String fileType, long size, UserReponse user) {
    	this.imageToken = imageToken ;
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
        this.user = user ;
    }
    
    public String getImageToken() {
		return imageToken;
	}


	public void setImageToken(String imageToken) {
		this.imageToken = imageToken;
	}


	public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

	public UserReponse getUser() {
		return user;
	}

	public void setUser(UserReponse user) {
		this.user = user;
	}

    
}