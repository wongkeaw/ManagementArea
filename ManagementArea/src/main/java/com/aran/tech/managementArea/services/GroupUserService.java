/**
 * 
 */
package com.aran.tech.managementArea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aran.tech.managementArea.domain.GroupUser;
import com.aran.tech.managementArea.repositories.GroupUserRepository;

/**
 * @author oawon
 *
 */
@Service
public class GroupUserService {

    @Autowired
    private GroupUserRepository groupUserRepository;
    
    public GroupUser findGroupUserById(long id){
    	
    	return groupUserRepository.getById(id) ;
    	
    }
}
