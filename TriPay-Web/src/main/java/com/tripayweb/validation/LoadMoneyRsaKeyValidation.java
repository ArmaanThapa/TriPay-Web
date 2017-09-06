package com.tripayweb.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.model.app.request.LoadMoneyRsaKeyRequest;
import com.tripayweb.model.error.LoadMoneyRsaKeyError;

public class LoadMoneyRsaKeyValidation {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public LoadMoneyRsaKeyError checkError(LoadMoneyRsaKeyRequest request) {
		LoadMoneyRsaKeyError error = new LoadMoneyRsaKeyError();
		boolean valid = true;

		error.setValid(valid);
		return error;
	}
}
