package com.aran.tech.managementArea.payload;

import com.aran.tech.managementArea.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author oawon
 */
@JsonIgnoreProperties({ "password", "confirmPassword" })
public class UserReponse extends User {

	private static final long serialVersionUID = -343178244210586205L;

	
	public UserReponse(User user ) {
		this.setId(user.getId());
		this.setUserunique(user.getUserunique());
		this.setUsername(user.getUsername());
		this.setFullName(user.getFullName());
		this.setStatus(user.getStatus());
		this.setCreated_At(user.getCreated_At());
		this.setUpdated_At(user.getUpdated_At());
		this.setProfileImage(user.getProfileImage());
    	this.setBlackguardImage(user.getBlackguardImage());
	}
	
	public UserReponse() {
	}
	
	@Getter
	@Setter
	private boolean isLoadFetch ;
	 
}

