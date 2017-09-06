package com.tripayweb.validation;

import com.tripayweb.app.model.request.ChangeMPINRequest;
import com.tripayweb.app.model.request.ForgotMpinRequest;
import com.tripayweb.app.model.request.SetMPINRequest;
import com.tripayweb.model.error.MpinError;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MpinValidation {

	public MpinError checkNewMpinError(SetMPINRequest dto){
		MpinError error = new MpinError();
		boolean valid = true;
		if(CommonValidation.isNull(dto.getNewMpin())){
			error.setNewMpin("Please enter new MPIN");
			valid = false;
		}else if(!CommonValidation.checkLength4(dto.getNewMpin())){
			error.setNewMpin("MPIN must be 4 digits long");
			valid = false;
		}else if(!CommonValidation.isNumeric(dto.getNewMpin())){
			error.setNewMpin("MPIN must contain digits");
			valid = false;
		}
		
		if(CommonValidation.isNull(dto.getConfirmMpin())){
			error.setConfirmMpin("please re-enter MPIN");
			valid = false;
		}else if(!(dto.getNewMpin().equals(dto.getConfirmMpin()))){
			error.setConfirmMpin("MPIN mismatch");
			valid = false;
		}
		if(CommonValidation.isNull(dto.getUsername())){
			valid = false;
		}
		
		error.setValid(valid);
		return error;
	}
	
	public MpinError checkChangeMpinError(ChangeMPINRequest dto){
		MpinError error = new MpinError();
		boolean valid = true;
		if(CommonValidation.isNull(dto.getNewMpin())){
			error.setNewMpin("Please enter new MPIN");
			valid = false;
		}else if(!CommonValidation.checkLength6(dto.getNewMpin())){
			error.setNewMpin("Please enter 6 characters valid MPIN");
			valid = false;
		}else if(!CommonValidation.isNumeric(dto.getNewMpin())){
			error.setNewMpin("MPIN must contain digits");
			valid = false;
		}
		
		if(CommonValidation.isNull(dto.getConfirmMpin())){
			error.setConfirmMpin("please re-enter MPIN");
			valid = false;
		}else if(!(dto.getNewMpin().equals(dto.getConfirmMpin()))){
			error.setConfirmMpin("MPIN mismatch");
			valid = false;
		}
		if(CommonValidation.isNull(dto.getOldMpin())){
			error.setOldMpin("please enter your old mpin");
			valid = false;
		}
		if(CommonValidation.isNull(dto.getUsername())){
			valid = false;
		}
		error.setValid(valid);
		return error;
	}

	public MpinError checkError(ForgotMpinRequest dto){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		MpinError error = new MpinError();
		boolean valid = true;
		if(CommonValidation.isNull(dto.getPassword())){
			valid = false;
			error.setOldMpin("enter password");
		}
		if(CommonValidation.isNull(dto.getDateOfBirth())){
			valid = false;
			error.setNewMpin("enter date of birth");
		}else{
			try {
				dateFormat.parse(dto.getDateOfBirth());
			} catch (ParseException e) {
				valid = false;
				error.setNewMpin("enter date of birth in yyyy-mm-dd format");
			}
		}

		error.setValid(valid);
		return error;
	}



}
