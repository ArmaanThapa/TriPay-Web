package com.tripayweb.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.model.request.InsuranceBillPaymentRequest;
import com.tripayweb.model.error.InsuranceBillPaymentError;
import com.tripayweb.util.LogCat;

public class InsuranceBillPaymentValidation {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		InsuranceBillPaymentValidation validation = new InsuranceBillPaymentValidation();
		InsuranceBillPaymentRequest request = new InsuranceBillPaymentRequest();
		request.setServiceProvider("airtel");
		request.setPolicyNumber("abc");
		request.setAmount("-234");
		request.setPolicyDate("16/05/2016");
		InsuranceBillPaymentError error = validation.checkError(request);
		LogCat.print(error.toString());

	}

	public InsuranceBillPaymentError checkError(InsuranceBillPaymentRequest insurance) {
		InsuranceBillPaymentError error = new InsuranceBillPaymentError();
		boolean valid = true;
		if (CommonValidation.isNull(insurance.getAmount())) {
			error.setAmount("Please enter amount  in the field");
			valid = false;
		} else if (!CommonValidation.isNumeric(insurance.getAmount())) {
			error.setAmount("Amount must be in numeric form");
			valid = false;
		}

		if (CommonValidation.isNull(insurance.getPolicyDate())) {
			error.setPolicyDate("Please select your policy date");
			valid = false;
		} else if (!CommonValidation.isDateValid(insurance.getPolicyDate())) {
			error.setPolicyDate("Please enter valid date");
			valid = false;
		}
		if (CommonValidation.isNull(insurance.getPolicyNumber())) {
			error.setAmount("Please enter policy number");
			valid = false;
		}
		if (CommonValidation.isNull(insurance.getServiceProvider())) {
			error.setServiceProvider("Please select Service Provider");
			valid = false;
		}
		if (!CommonValidation.isNumeric(insurance.getPolicyNumber())) {
			error.setPolicyNumber("Policy number must be in numeric form");
			valid = false;
		}

		error.setValid(valid);
		return error;
	}
}
