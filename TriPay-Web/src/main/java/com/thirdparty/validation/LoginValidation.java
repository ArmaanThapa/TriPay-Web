package com.thirdparty.validation;

import com.tripayweb.validation.CommonValidation;
import com.thirdparty.model.LoginDTO;
import com.thirdparty.model.error.LoginError;

public class LoginValidation {
    public LoginError checkError(LoginDTO dto){
        LoginError error = new LoginError();
        boolean valid = true;
        if(CommonValidation.isNull(dto.getUsername())){
            valid = false;
            error.setUsername("Enter Your Mobile No.");
        }else if(!CommonValidation.isNumeric(dto.getUsername())){
            valid  = false;
            error.setUsername("Mobile No. contains only digits");
        }else if(!CommonValidation.checkLength10(dto.getUsername())){
            valid = false;
            error.setUsername("Mobile Number must be 10 digits long");
        }

        if(CommonValidation.isNull(dto.getPassword())){
            valid = false;
            error.setPassword("Enter Your Password");
        }
        error.setValid(valid);
        return error;

    }
}
