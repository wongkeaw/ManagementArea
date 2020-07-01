package com.aran.tech.managementArea.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aran.tech.managementArea.domain.GroupUser;
import com.aran.tech.managementArea.domain.PermissionAction;
import com.aran.tech.managementArea.domain.User;
import com.aran.tech.managementArea.repositories.UserRepository;

/**
 * @author oawon
 */
@Service
public class PermissionActionService {
    @Autowired
    private UserRepository userRepository;
	
    @Transactional
    public List<PermissionAction> findAllPermissionActions(String username){
    	System.out.println("findAllPermissionActions:"+username);
    	List<PermissionAction> permissionActions = new ArrayList<>();
    	User user = userRepository.findByUsername(username);
    	List<GroupUser> groupUsers = user.getGroupUsers() ;
    	System.out.println("groupUsers size :" + groupUsers); 
    	
    	for (GroupUser groupUser : groupUsers) {
    		List<PermissionAction> permiss = groupUser.getPermissionActions() ;
    		permissionActions.addAll(permiss) ;
		}
		
    	System.out.println("permissionActions size :" + permissionActions.size());
    	return permissionActions ;
    }

	public PermissionAction findPermissionActionByIdentifier(String permissionActionId, String name) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
