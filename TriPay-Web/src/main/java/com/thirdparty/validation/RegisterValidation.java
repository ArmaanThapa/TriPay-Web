package com.thirdparty.validation;


import com.tripayweb.validation.CommonValidation;
import com.thirdparty.model.ChangePasswordDTO;
import com.thirdparty.model.ForgotPasswordDTO;
import com.thirdparty.model.RegisterDTO;
import com.thirdparty.model.VerifyDTO;
import com.thirdparty.model.error.ChangePasswordError;
import com.thirdparty.model.error.ForgotPasswordError;
import com.thirdparty.model.error.RegisterError;
import com.thirdparty.model.error.VerifyError;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegisterValidation {
    public RegisterError checkError(RegisterDTO dto){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        RegisterError error = new RegisterError();
        boolean valid = true;
        if(CommonValidation.isNull(dto.getFirstName())){
            valid = false;
            error.setFirstName("Enter Your Name");
        }
        if(CommonValidation.isNull(dto.getPassword())){
            valid = false;
            error.setPassword("Enter Your Password");
        }else if(!CommonValidation.isValidPassword(dto.getPassword())){
            valid = false;
            error.setPassword("Password must be only 6 characters long");
        }else if(!dto.getPassword().equals(dto.getConfirmPassword())){
            valid = false;
            error.setPassword("Both Passwords must match");
        }
        if(CommonValidation.isNull(dto.getContactNo())){
            valid = false;
            error.setContactNo("Enter Your Contact No");
        }else if(!CommonValidation.isNumeric(dto.getContactNo())){
            valid = false;
            error.setContactNo("Mobile Number must contain only digits");
        }else if(!CommonValidation.checkLength10(dto.getContactNo())){
            valid = false;
            error.setContactNo("Mobile Number is 10 digits long");
        }
        if(CommonValidation.isNull(dto.getEmail())){
            valid = false;
            error.setEmail("Enter email");
        }else if(!CommonValidation.isValidMail(dto.getEmail())){
            valid = false;
            error.setEmail("Enter valid email");
        }

        if(CommonValidation.isNull(dto.getDateOfBirth())){
            valid = false;
            error.setDateOfBirth("Enter Date Of Birth");
        }else {
            try {

                format.parse(dto.getDateOfBirth());
            } catch (ParseException e) {
                valid = false;
                error.setDateOfBirth("Enter Date Of Birth in YYYY-MM-DD format");
            }
        }
        error.setValid(valid);
        return error;
    }

    public VerifyError checkError(VerifyDTO dto){
        VerifyError error = new VerifyError();
        boolean valid = true;
        if(CommonValidation.isNull(dto.getOtp())){
            valid = false;
            error.setOtp("Enter OTP");
        }else if(!CommonValidation.isNumeric(dto.getOtp())){
            valid = false;
            error.setOtp("Enter Valid OTP");
        }
        error.setValid(valid);
        return error;
    }

    public ForgotPasswordError checkError(ForgotPasswordDTO dto){
        ForgotPasswordError error = new ForgotPasswordError();
        boolean valid = true;
        if(CommonValidation.isNull(dto.getUsername())){
            valid = false;
            error.setUsername("Enter Your mobile number");
        }else if(!CommonValidation.isNumeric(dto.getUsername())){
            valid = false;
            error.setUsername("Mobile number contains only digits");
        }else if(!CommonValidation.checkLength10(dto.getUsername())){
            valid = false;
            error.setUsername("Enter 10 digit mobile number");
        }
        error.setValid(valid);
        return error;
    }

    public ChangePasswordError checkError(ChangePasswordDTO dto){
        ChangePasswordError error = new ChangePasswordError();
        boolean valid = true;
        if(CommonValidation.isNull(dto.getNewPassword())){
            valid = false;
            error.setNewPassword("Enter New Password");
        }else if(!CommonValidation.isValidPassword(dto.getNewPassword())){
            valid = false;
            error.setNewPassword("Password must be 6 characters long");
        }
        if(CommonValidation.isNull(dto.getConfirmPassword())){
            valid = false;
            error.setConfirmPassword("Re Enter New Password");
        }else if(!(dto.getNewPassword().equals(dto.getConfirmPassword()))){
            valid = false;
            error.setConfirmPassword("Both Passwords must be equal");
        }
        error.setValid(valid);
        return error;
    }

}
