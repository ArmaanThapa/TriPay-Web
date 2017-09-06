package com.tripayweb.validation;

import com.tripayweb.app.model.request.KycVerificationDTO;
import com.tripayweb.model.error.KycError;

public class KycValidation {

	public KycError checkKycError(KycVerificationDTO dto) {
		System.err.println("mobileNo :"+dto.getMobileNumber());
		System.err.println("AccountNumber :"+dto.getAccountNumber());
		KycError error = new KycError();
		boolean valid=true;
		if(CommonValidation.isNull(dto.getAccountNumber())){
			error.setAccountNumber("Please enter your account number");
			valid = false;
		}else if(!CommonValidation.checkAccountNoLength15(dto.getAccountNumber())){
			error.setAccountNumber("Account Number has to be 15 digits long");
			valid = false;
		}else if(!CommonValidation.isNumeric(dto.getAccountNumber())){
			error.setAccountNumber("Account Number can contain only digits");
			valid = false;
		}
		
		if(CommonValidation.isNull(dto.getMobileNumber())) {
			error.setMobileNumber("Enter your registered mobile number");
			valid = false;
		}else if (!CommonValidation.checkLength10(dto.getMobileNumber())) {
			error.setMobileNumber("Mobile number must be 10-digit");
			valid = false;
		}else if(!CommonValidation.isNumeric(dto.getMobileNumber())){
			error.setMobileNumber("Enter valid mobile number");
			valid =  false;
		}
		error.setValid(valid);
		return error;
		
	}
	
	public KycError checkKycOTPError(KycVerificationDTO dto) {
		
		KycError error = new KycError();
		boolean valid=true;
		
		if(CommonValidation.isNull(dto.getOtp())) {
			error.setOtp("Enter the OTP sent to your mobile number");
			valid = false;
		} else if(!CommonValidation.checkOTPLength6(dto.getOtp())){
			error.setOtp("OTP must be 6 digits long");
			valid = false;
		}else if(!CommonValidation.isNumeric(dto.getOtp())){
			error.setOtp("Please enter valid OTP");
			valid = false;
		}
		
		error.setValid(valid);
		return error;

	}
}
