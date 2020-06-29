/**
 * 
 */
package com.aran.tech.managementArea.services;

import org.springframework.stereotype.Service;

import com.aran.tech.managementArea.entity.User;

/**
 * @author oawon
 *
 */
@Service
public class UserService {

	
	public User createUser(User user) {
		return new User() ;

	}
}
