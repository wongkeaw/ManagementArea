package com.aran.tech.managementArea.services;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aran.tech.managementArea.domain.GroupUser;
import com.aran.tech.managementArea.domain.PermissionAction;
import com.aran.tech.managementArea.domain.User;
import com.aran.tech.managementArea.exceptions.UserNotFoundException;
import com.aran.tech.managementArea.exceptions.UsernameAlreadyExistsException;
import com.aran.tech.managementArea.property.ImageProperties;
import com.aran.tech.managementArea.repositories.UserRepository;
/**
 * @author oawon
 */
@Service
public class UserService {

    private final String profileImage ;
    private final String blackguardImage ;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
	public UserService(ImageProperties imageProperties) {
		profileImage = imageProperties.getProfile() ;
		blackguardImage = imageProperties.getBlackguard();
	}
    
    public User createUser(User newUser) {
    	newUser.setStatus(User.STATUS_ACTIVE);
    	newUser.setProfileImage(profileImage);
    	newUser.setBlackguardImage(blackguardImage);
    	return this.saveUser(newUser) ;
    }
    public User saveUser (User newUser){

        try{
        	User user = null ;
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            //Username has to be unique (exception)
            newUser.setUsername(newUser.getUsername());
            // Make sure that password and confirmPassword match
            // We don't persist or show the confirmPassword
            newUser.setConfirmPassword("");
            GroupUser groupUser = new GroupUser() ;
            groupUser.setGroupType(GroupUser.TYPE_PRIVATE);
            groupUser.setStatus(GroupUser.STATUS_ACTIVE);
            groupUser.setName("private only me.");
            
            PermissionAction permissionActionRead = new PermissionAction() ;
            permissionActionRead.setName(PermissionAction.PRODUCT_TIME_LINE);
            permissionActionRead.setProduct(PermissionAction.PRODUCT_TIME_LINE_READ);
            permissionActionRead.setStatus(PermissionAction.STATUS_ACTIVE);
            
            PermissionAction permissionActionAdd = new PermissionAction() ;
            permissionActionAdd.setName(PermissionAction.PRODUCT_TIME_LINE);
            permissionActionAdd.setProduct(PermissionAction.PRODUCT_TIME_LINE_ADD);
            permissionActionAdd.setStatus(PermissionAction.STATUS_ACTIVE);
            
            groupUser.setPermissionActions(Arrays.asList(permissionActionRead , permissionActionAdd ));
            newUser.setGroupUsers(Arrays.asList(groupUser));
            user = userRepository.save(newUser);

            //groupUserRepository.save(groupUser) ;
            
            return user ;
        }catch (Exception e){
        	e.printStackTrace();
            throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
        }

    }

    public User saveOrUpdateProject(User user, String username){
    	User newUser = null ;
    	if (user.getId() != null ) {
    		newUser = userRepository.getById(user.getId()) ;
    		if (newUser == null) {
    			throw new UserNotFoundException("User Name: '"+username+"' cannot be updated because it doesn't exist");
    		} else if ( newUser.getUsername().equals(username) == false ) {
    			throw new UserNotFoundException("User Name: '"+username+"' cannot be updated ");
    		}
    	}
    	try {
    		
    		newUser.setUsername(user.getUsername());
    		newUser.setFullName(user.getFullName());
    		newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			
    		return userRepository.save(newUser) ;
		} catch (Exception e) {
			throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
		}
    }
    
    
    
    public User getUserByUserunique( String userunique){
    	User user = userRepository.findByUserunique(userunique);
    	return user ;
    }
    public User findUserByIdentifier(Long userId, String username){
    	User user = null ;
    	if (userId != null ) {
    		user = userRepository.getById(userId) ;
    		if (user == null) {
    			throw new UserNotFoundException("User Name: '"+username+"' cannot be updated because it doesn't exist");
    		} else if ( user.getUsername().equals(username) == false ) {
    			throw new UserNotFoundException("User Name: '"+username+"' cannot be updated ");
    		}
    	}
		user.setPassword(null);
		return user ;
    }
    
	public User saveOrUpdateImage(String photos , String uploadType, String username){
		
		User user = userRepository.findByUsername(username);
		
		if ("blackguardImage".equals(uploadType)) {
			user.setBlackguardImage(photos);
		}else {
			user.setProfileImage(photos);
		}
		User newUser = userRepository.save(user) ;
		return newUser ;
		
	}
}
