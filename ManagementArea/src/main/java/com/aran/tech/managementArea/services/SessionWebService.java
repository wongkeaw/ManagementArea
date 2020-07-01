/**
 * 
 */
package com.aran.tech.managementArea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aran.tech.managementArea.domain.SessionWeb;
import com.aran.tech.managementArea.domain.User;
import com.aran.tech.managementArea.exceptions.UserNotFoundException;
import com.aran.tech.managementArea.repositories.SessionWebRepository;
import com.aran.tech.managementArea.repositories.UserRepository;

/**
 * @author oawon
 *
 */
@Service
public class SessionWebService {

    @Autowired
    private SessionWebRepository sessionWebRepository ;
    
    @Autowired
    private UserRepository userRepository;
    
    public SessionWeb saveOrUpdateSession(SessionWeb session) {
    	SessionWeb sess = sessionWebRepository.save(session) ;
    	return sess ;
    }
    
	public SessionWeb getById(Long id) {
    	return sessionWebRepository.getById(id) ;
    }
    public void deleteSession(Long id, String username) {
    	User newUser = null ;
    	if (id != null ) {
    		newUser = userRepository.getById(id) ;
    		if (newUser == null) {
    			throw new UserNotFoundException("User Name cannot be updated because it doesn't exist");
    		} else if ( newUser.getUsername().equals(username) == false ) {
    			throw new UserNotFoundException("User Name cannot be updated ");
    		}
    	}
    	try {
    		sessionWebRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}
