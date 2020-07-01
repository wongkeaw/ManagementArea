package com.aran.tech.managementArea.exceptions;

/**
 * @author oawon
 */
public class UserNotFoundResponse  {

    private String username;

    public UserNotFoundResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
