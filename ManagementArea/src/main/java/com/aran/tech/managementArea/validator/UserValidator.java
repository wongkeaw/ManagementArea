package com.aran.tech.managementArea.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.aran.tech.managementArea.domain.User;
/**
 * @author oawon
 */
@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
    	
    	if (object == null ) {
    		errors.reject("Length", "Null Pointer");
    	}

        User user = (User) object;

        if(user.getPassword() == null || user.getPassword().length() <6){
            errors.rejectValue("password","Length", "Password must be at least 6 character.");
        }

        if(user.getPassword() == null || (  !user.getPassword().equals(user.getConfirmPassword()))  ){
            errors.rejectValue("confirmPassword","Match", "Passwords must match.");

        }

        //confirmPassword



    }
}
