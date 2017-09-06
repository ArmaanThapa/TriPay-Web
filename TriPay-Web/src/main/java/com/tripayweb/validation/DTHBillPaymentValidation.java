package com.tripayweb.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.model.request.DTHBillPaymentRequest;
import com.tripayweb.model.error.DTHBillPaymentError;
import com.tripayweb.util.LogCat;

public class DTHBillPaymentValidation {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());


	public DTHBillPaymentError checkError(DTHBillPaymentRequest dth) {
		DTHBillPaymentError error = new DTHBillPaymentError();
		boolean valid = true;
		if (CommonValidation.isNull(dth.getServiceProvider())) {
			error.setServiceProvider("Please select sevice provider");
			valid = false;
		}
		if (CommonValidation.isNull(dth.getAmount())) {
			error.setAmount("Please enter amount");
			valid = false;
		} else if (!CommonValidation.isNumeric(dth.getAmount())) {
			error.setAmount("Please enter valid amount in the field");
			valid = false;
		}
		if (CommonValidation.isNull(dth.getDthNo())) {
			error.setDthNo("Please enter DTH number");
			valid = false;
		} else if (!CommonValidation.isNumeric(dth.getDthNo())) {
			error.setDthNo("Please enter valid DTH number");
			valid = false;
		}

		error.setValid(valid);
		return error;

	}
}
