package com.tripayweb.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.model.request.ElectricityBillPaymentRequest;
import com.tripayweb.model.error.ElectricityBillPaymentError;
import com.tripayweb.util.LogCat;

public class ElectricityBillPaymentValidation {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		ElectricityBillPaymentValidation electricityValidation = new ElectricityBillPaymentValidation();

		ElectricityBillPaymentRequest electricityRequest = new ElectricityBillPaymentRequest();
		electricityRequest.setAmount("0");
		electricityRequest.setAccountNumber("");
		electricityRequest.setCycleNumber("");
		electricityRequest.setServiceProvider("");

		ElectricityBillPaymentError error = electricityValidation.checkError(electricityRequest);
		LogCat.print(error.toString());

	}

	public ElectricityBillPaymentError checkError(ElectricityBillPaymentRequest request) {
		ElectricityBillPaymentError error = new ElectricityBillPaymentError();
		boolean valid = true;
		if (CommonValidation.isNull(request.getAccountNumber())) {
			error.setAccountNumber("Please enter account number");
			valid = false;
		} else if (!CommonValidation.isNumeric(request.getAccountNumber())) {
			error.setAccountNumber("Enter valid account number");
			valid = false;
		}
		if (CommonValidation.isNull(request.getAmount())) {
			error.setAmount("Please enter amount");
			valid = false;
		} else if (!CommonValidation.isNumeric(request.getAmount())) {
			error.setAmount("Enter valid Amount");
			valid = false;
		}
		if (CommonValidation.isNull(request.getServiceProvider())) {
			error.setServiceProvider("Please select service provider");
			valid = false;
		}

		error.setValid(valid);
		return error;

	}
}
