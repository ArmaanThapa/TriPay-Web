package com.tripayweb.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.model.app.request.VerifyMobileRequest;
import com.tripayweb.model.error.VerifyMobileError;
import com.tripayweb.util.LogCat;

public class VerifyMobileValidation {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {

		VerifyMobileValidation verifyMobileValidation = new VerifyMobileValidation();
		// RegisterError registerError = new RegisterError();
		VerifyMobileRequest verifyMobileRequest = new VerifyMobileRequest();
		LogCat.print(verifyMobileRequest.toString());

		verifyMobileRequest.setMobileNumber("8861605914");
		verifyMobileRequest.setKey("45@@@21");
		VerifyMobileError verifyMobileError = verifyMobileValidation.checkError(verifyMobileRequest);
		// RegisterResponse response = registerApi.request(registerRequest);

		LogCat.print(verifyMobileError.toString());

	}

	public VerifyMobileError checkError(VerifyMobileRequest verifyMobileRequest) {
		VerifyMobileError error = new VerifyMobileError();
		boolean valid = true;
		if (CommonValidation.isNull(verifyMobileRequest.getMobileNumber())) {
			error.setMobileNumber("Please enter your mobile number");
			valid = false;

		}else if(CommonValidation.checkLength10(verifyMobileRequest.getMobileNumber())) {
			error.setMobileNumber(" mobile number must be 10-digit");
			valid = false;
		}
		if (CommonValidation.isNull(verifyMobileRequest.getKey())) {
			error.setKey("key should be numeric");
			valid = false;
		}
		else if (CommonValidation.isNumeric(verifyMobileRequest.getKey())) {
			error.setKey("key must be 	Numeric");
			valid = false;
		}
		error.setValid(valid);
		return error;

	}
}
