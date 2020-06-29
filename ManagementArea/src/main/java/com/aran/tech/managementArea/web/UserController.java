/**
 * 
 */
package com.aran.tech.managementArea.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aran.tech.managementArea.entity.User;
import com.aran.tech.managementArea.services.UserService;
import com.aran.tech.managementArea.validator.MapValidationErrorService;
import com.aran.tech.managementArea.validator.UserValidator;

/**
 * @author oawon
 *
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
	

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
        
    	userValidator.validate(user,result);
    	
    	 ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
         if(errorMap != null)return errorMap;
    	
    	User rsUser = userService.createUser(user) ;
        
        return  new ResponseEntity<User>(rsUser, HttpStatus.CREATED);
    }
}
