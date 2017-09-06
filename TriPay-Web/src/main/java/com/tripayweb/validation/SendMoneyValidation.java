package com.tripayweb.validation;

import com.tripayweb.app.model.request.SendMoneyBankRequest;
import com.tripayweb.model.error.SendMoneyBankError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.api.IUserApi;
import com.tripayweb.model.error.SendMoneyMobileError;
import com.tripayweb.app.model.request.SendMoneyMobileRequest;

public class SendMoneyValidation {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public SendMoneyMobileError checkMobileError(SendMoneyMobileRequest mobile) {
		SendMoneyMobileError error = new SendMoneyMobileError();
		boolean valid = true;
		if (CommonValidation.isNull(mobile.getAmount())) {
			error.setAmount("Please enter amount");
			valid = false;
		}
		if (CommonValidation.isNull(mobile.getMobileNumber())) {
			error.setMobileNumber("Please enter mobile number");
			valid = false;
		}
		if (!CommonValidation.isNumeric(mobile.getAmount())) {
			error.setAmount("enter valid amount in field");
			valid = false;
		}
		if (!CommonValidation.isNumeric(mobile.getMobileNumber())) {
			error.setMobileNumber("enter valid mobile number");
			valid = false;
		}
		if (!CommonValidation.checkLength10(mobile.getMobileNumber())) {
			error.setMobileNumber("enter 10 digit mobile number");
			valid = false;
		}
		if(CommonValidation.isNull(mobile.getMessage())){
			valid = false;
			error.setMobileNumber("Please enter valid message");
		}
		error.setValid(valid);
		return error;
	}

	public SendMoneyBankError checkBankError(SendMoneyBankRequest bank) {
		SendMoneyBankError error = new SendMoneyBankError();
		boolean valid = true;

		if(CommonValidation.isNull(bank.getAccountName())){
			valid = false;
			error.setAccountName("Enter Account Name");
		}
		if(CommonValidation.isNull(bank.getAmount())){
			valid  = false;
			error.setAmount("Enter Amount");
		}else if(!CommonValidation.isNumeric(bank.getAmount())){
			valid = false;
			error.setAmount("Enter valid amount");
		}else {
			double amount = Double.parseDouble(bank.getAmount());
			if(amount < 500){
				valid = false;
				error.setAmount("Amount must be at least 500");
			}
		}

		if(CommonValidation.isNull(bank.getBankCode())){
			valid = false;
			error.setBankCode("Select Bank");
		}else if(!CommonValidation.containsAlphabets(bank.getBankCode())){
			valid = false;
			error.setBankCode("Not a valid bank code");
		}

		if(CommonValidation.isNull(bank.getIfscCode())){
			valid = false;
			error.setIfscCode("Enter IFSC Code");
		}

		if(bank.getAccountNumber() < 99999){
			valid = false;
			error.setAccountNumber("Enter Valid Account Number");
		}
		error.setValid(valid);
		return error;

	}
}
