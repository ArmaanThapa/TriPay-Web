package com.tripayweb.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.model.request.PayAtStoreRequest;
import com.tripayweb.model.error.PayAtStoreError;
import com.tripayweb.util.LogCat;

public class PayAtStoreValidation {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		PayAtStoreValidation sendMoneyValidation = new PayAtStoreValidation();

		PayAtStoreRequest storeRequest = new PayAtStoreRequest();

		PayAtStoreError error = sendMoneyValidation.checkError(storeRequest);
		LogCat.print(error.toString());
	}

	public PayAtStoreError checkError(PayAtStoreRequest store) {
		boolean valid = true;
		PayAtStoreError error = new PayAtStoreError();
		if(store.getNetAmount() <= 10){
			error.setAmount("Amount must be greater than 10");
			valid = false;
		}
		if(store.getId() <= 0){
			valid = false;
			error.setServiceProvider("Please enter valid Merchant ID");
		}
		error.setValid(valid);
		return error;
	}
}
