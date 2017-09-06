package com.tripayweb.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.model.request.MobileOTPRequest;
import com.tripayweb.app.model.request.ResendMobileOTPRequest;
import com.tripayweb.model.error.MobileOTPError;

public class MobileOTPValidation {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public MobileOTPError checkError(MobileOTPRequest dto) {
		boolean valid = true;
		MobileOTPError error = new MobileOTPError();

		if (CommonValidation.isNull(dto.getKey())) {
			valid = false;
		} else if (!CommonValidation.isNumeric(dto.getKey())) {
			valid = false;
		}
		error.setValid(valid);
		return error;
	}
	
	public MobileOTPError checkError(ResendMobileOTPRequest dto) {
		boolean valid = true;
		MobileOTPError error = new MobileOTPError();

		if(CommonValidation.isNull(dto.getMobileNumber())){
			valid = false;
		}else if(!CommonValidation.isNumeric(dto.getMobileNumber())){
			valid = false;
		}else if(!CommonValidation.checkLength10(dto.getMobileNumber())){
			valid = false;
		}
		error.setValid(valid);
		return error;
	}

}
