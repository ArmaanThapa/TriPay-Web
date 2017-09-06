package com.tripayweb.validation;

import com.tripayweb.app.model.request.LoginRequest;
import com.tripayweb.model.error.LoginError;
import com.thirdparty.model.LoginDTO;

public class LoginValidation {

	public LoginError checkError(LoginRequest dto) {
		LoginError error = new LoginError();
		boolean valid = true;
		if (CommonValidation.isNull(dto.getUsername())) {
			valid = false;
		} else if (!CommonValidation.isNumeric(dto.getUsername())) {
			valid = false;
		} else if (!CommonValidation.checkLength10(dto.getUsername())) {
			valid = false;
		}
		error.setValid(valid);
		return error;
	}

	public LoginError checkMerchantLogin(LoginRequest dto) {
		LoginError error = new LoginError();
		boolean valid = true;
		if (CommonValidation.isNull(dto.getUsername())) {
			valid = false;
			error.setMobileNumber("Enter email");
		} else if (!CommonValidation.isValidMail(dto.getUsername())) {
			valid = false;
			error.setMobileNumber("Not a valid mail");
		}
		if(CommonValidation.isNull(dto.getPassword())){
			valid = false;
			error.setPassword("Enter Password");
		}
		error.setValid(valid);
		return error;
	}

	public LoginError checkError(LoginDTO dto) {
		LoginError error = new LoginError();
		boolean valid = true;
		if (CommonValidation.isNull(dto.getUsername())) {
			valid = false;
			error.setMobileNumber("Please enter mobile number");
		} else if (!CommonValidation.isNumeric(dto.getUsername())) {
			valid = false;
			error.setMobileNumber("Enter valid mobile number");
		} else if (!CommonValidation.checkLength10(dto.getUsername())) {
			valid = false;
            error.setMobileNumber("Enter valid mobile number");
		}
		if(CommonValidation.isNull(dto.getPassword())){
			valid = false;
			error.setPassword("Please enter Password");
		}
		if(CommonValidation.isNull(dto.getToken())){
			valid = false;
            error.setToken("TOKEN NOT FOUND");
		}

		error.setValid(valid);
		return error;
	}
}
