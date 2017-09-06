package com.tripayweb.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.model.app.request.SendMoneyMobileRequest;
import com.tripayweb.model.error.SendMoneyMobileError;
import com.tripayweb.util.LogCat;

public class SendMoneyMobileValidation {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SendMoneyMobileValidation sendMoneyValidation = new SendMoneyMobileValidation();

		SendMoneyMobileRequest sendMoneyRequest = new SendMoneyMobileRequest();
		sendMoneyRequest.setAmount("22iui");
		sendMoneyRequest.setMobileNumber("kj5555");

		SendMoneyMobileError error = sendMoneyValidation.checkMobileError(sendMoneyRequest);
		LogCat.print(error.toString());
	}

	public SendMoneyMobileError checkMobileError(SendMoneyMobileRequest mobile) {
		SendMoneyMobileError error = new SendMoneyMobileError();
		boolean valid = true;
		if (CommonValidation.isNull(mobile.getAmount())) {
			error.setAmount("Please enter amount");
			valid = false;
		} else if (!CommonValidation.isNumeric(mobile.getAmount())) {
			error.setAmount("enter valid amount in field");
			valid = false;
		}
		if (CommonValidation.isNull(mobile.getMobileNumber())) {
			error.setMobileNumber("Please enter mobile number");
			valid = false;
		}

		else if (!CommonValidation.checkLength10(mobile.getMobileNumber())) {
			error.setMobileNumber("mobile number must be 10-digit");
			valid = false;
		}
		error.setValid(valid);
		return error;

	}
}
