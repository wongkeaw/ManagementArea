package com.aran.tech.managementArea.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aran.tech.managementArea.payload.JWTLoginSucessReponse;
import com.aran.tech.managementArea.payload.UserReponse;
import com.aran.tech.managementArea.payload.UserRequest;
import com.aran.tech.managementArea.services.SessionWebService;
import com.aran.tech.managementArea.services.UserService;
import com.aran.tech.managementArea.validator.MapValidationErrorService;
import com.aran.tech.managementArea.validator.UserValidator;

/**
 * @author oawon
 */
@RestController
@RequestMapping("/api/usersMA")
public class UserManagementController {
	
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private SessionWebService sessionWebService ;
    
    @PostMapping("/logout")
    public ResponseEntity<?> unauthenticatedUser(@Valid @RequestBody UserRequest user, BindingResult result , Principal principal){
    	sessionWebService.deleteSession(user.getId(), principal.getName());
        return ResponseEntity.ok(new JWTLoginSucessReponse(true, "logout successful.")) ;
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId, Principal principal){

    	UserReponse userReponse = new UserReponse(userService.findUserByIdentifier(userId, principal.getName())) ;
        return new ResponseEntity<UserReponse>(userReponse, HttpStatus.OK);
    }
    
    @GetMapping("/userunique/{userunique}")
    public ResponseEntity<?> getUserByUserunique(@PathVariable String userunique, Principal principal){

    	UserReponse userReponse = new UserReponse(userService.getUserByUserunique(userunique)) ;
        return new ResponseEntity<UserReponse>(userReponse, HttpStatus.OK);
    }
    
    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserRequest user, BindingResult result , Principal principal){
    	// Validate passwords match
        userValidator.validate(user,result);
        
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;
        UserReponse userReponse = new UserReponse(userService.saveOrUpdateProject(user , principal.getName())) ;
        return  new ResponseEntity<UserReponse>(userReponse, HttpStatus.OK);
    }
    
}
