package com.tripayweb.validation;

import com.tripayweb.app.model.request.ChangePasswordRequest;
import com.tripayweb.model.error.ChangePasswordError;

public class ChangePasswordValidation {

	public ChangePasswordError checkError(ChangePasswordRequest dto){
		ChangePasswordError error = new ChangePasswordError();
		boolean valid = true;
		if(CommonValidation.isNull(dto.getOldPassword())){
			valid = false;
			error.setCurrentPassword("please enter your current password");
		}
		if(CommonValidation.isNull(dto.getNewPassword())){
			valid = false;
			error.setNewPassword("please enter your new password");
		}else if(CommonValidation.checkLength6(dto.getNewPassword())){
			valid = false;
			error.setNewPassword("password must be at least 6 characters long");
		}else if(CommonValidation.isNull(dto.getConfirmPassword())){
			valid = false;
			error.setNewPassword("please re-enter your new password");
		}else if(!(dto.getNewPassword().equals(dto.getConfirmPassword()))){
			valid = false;
			error.setNewPassword("password mis-match");
		}
		return error;
	}
}
