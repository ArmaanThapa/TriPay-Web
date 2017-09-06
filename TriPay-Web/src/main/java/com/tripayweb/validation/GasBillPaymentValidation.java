package com.tripayweb.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.model.request.GasBillPaymentRequest;
import com.tripayweb.model.error.GasBillPaymentError;
import com.tripayweb.util.LogCat;

public class GasBillPaymentValidation {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		GasBillPaymentValidation validation = new GasBillPaymentValidation();
		GasBillPaymentRequest request = new GasBillPaymentRequest();
		request.setServiceProvider("");
		request.setAccountNumber("abc");
		request.setAmount("abc");

		GasBillPaymentError error = validation.checkError(request);
		LogCat.print(error.getAccountNumber());
		LogCat.print(error.getServiceProvider());
		LogCat.print(error.getAmount());
	}

	public GasBillPaymentError checkError(GasBillPaymentRequest request) {
		GasBillPaymentError error = new GasBillPaymentError();
		boolean valid = true;
		if (CommonValidation.isNull(request.getAccountNumber())) {
			error.setAccountNumber("Please enter Account Number");
			valid = false;
		}
		if (CommonValidation.isNull(request.getAmount())) {
			error.setAmount("Please enter Amount");
			valid = false;
		}
		if (CommonValidation.isNull(request.getServiceProvider())) {
			error.setServiceProvider("Please select Service Provider");
			valid = false;
		}
		if (!CommonValidation.isNumeric(request.getAccountNumber())) {
			error.setAccountNumber("Enter valid account number");
			valid = false;
		}
		if (!CommonValidation.isNumeric(request.getAmount())) {
			error.setAmount("Enter valid amount in the field");
			valid = false;
		}
		error.setValid(valid);
		return error;
	}
}
