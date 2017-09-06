package com.tripayweb.validation;

import com.tripayweb.app.model.request.OfflinePaymentRequest;
import com.tripayweb.model.error.OfflinePaymentError;

public class OfflinePaymentValidation {
	
	OfflinePaymentError error = new OfflinePaymentError();
	boolean valid= true;
	public OfflinePaymentError checkPaymentError(OfflinePaymentRequest request) {
		
		if(CommonValidation.isNull(request.getMobileNumber())) {
			error.setMobileNumber("Enter customer's mobile number");
			valid = false;
		}else if (!CommonValidation.checkLength10(request.getMobileNumber())) {
			error.setMobileNumber("Mobile number must be 10-digit");
			valid = false;
		}else if(!CommonValidation.isNumeric(request.getMobileNumber())){
			error.setMobileNumber("Enter valid mobile number");
			valid =  false;
		}
		
		if(CommonValidation.isValidAmount(request.getAmount())) {
			error.setAmount("Amount should be less than 10000.0");
			valid = false;
		}
		
		error.setValid(valid);
		return error;
	}
	
	public OfflinePaymentError checkOTPError (OfflinePaymentRequest request) {
		if(CommonValidation.isNull(request.getOtp())) {
			error.setOtp("Enter the OTP sent to customer's mobile number");
			valid = false;
		}else if(!CommonValidation.isNumeric(request.getOtp())){
			error.setOtp("Please enter valid OTP");
			valid = false;
		}
		
		error.setValid(valid);
		return error;

	}

}
