package com.aran.tech.managementArea.exceptions;

/**
 * @author oawon
 */
public class UsernameAlreadyExistsResponse {

    private String username;

    public UsernameAlreadyExistsResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	@Override
	public String toString() {
		return "UsernameAlreadyExistsResponse { " + (username != null ? "username=" + username : "") + " }";
	}
    
}
