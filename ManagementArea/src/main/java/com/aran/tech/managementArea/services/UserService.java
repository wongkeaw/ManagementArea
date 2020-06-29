/**
 * 
 */
package com.aran.tech.managementArea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aran.tech.managementArea.entity.User;
import com.aran.tech.managementArea.repositories.UserRepository;

/**
 * @author oawon
 *
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.save(user) ;
	}
}
