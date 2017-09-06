package com.tripayweb.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.model.request.LandlineBillPaymentRequest;
import com.tripayweb.model.error.LandlineBillPaymentError;
import com.tripayweb.util.LogCat;

public class LandlineBillPaymentValidation {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public LandlineBillPaymentError checkError(LandlineBillPaymentRequest landline) {

		LandlineBillPaymentError error = new LandlineBillPaymentError();
		boolean valid = true;
		if (CommonValidation.isNull(landline.getAccountNumber())) {
			logger.info("Enter Account Number");
			valid = false;
		}
		if (CommonValidation.isNull(landline.getAmount())) {
			logger.info("Enter Amount");
			valid = false;
		} else if (!CommonValidation.isNumeric(landline.getAmount())) {
			logger.info("Enter valid amount in the field");
			valid = false;
		}
		if (CommonValidation.isNull(landline.getLandlineNumber())) {
			logger.info("Enter Landline Number");
			valid = false;
		} else if (!CommonValidation.isNumeric(landline.getLandlineNumber())) {
			logger.info("Landline number must be in number format");
			valid = false;
		}
		if (CommonValidation.isNull(landline.getServiceProvider())) {
			logger.info("Select Service Provider");
			valid = false;
		}
		if (CommonValidation.isNull(landline.getStdCode())) {
			logger.info("Enter STD code");
			valid = false;
		}
		if (!CommonValidation.isNumeric(landline.getStdCode())) {
			logger.info("STD code must be in number format");
			valid = false;
		}
		if (!CommonValidation.checkLength6(landline.getStdCode())) {
			logger.info("STD code must be 6 digit long");
			valid = false;
		}
		LogCat.print("valid is "+valid);
		error.setValid(valid);
		return error;

	}
}
