package com.tripayweb.validation;

import com.tripayweb.app.model.request.LoadMoneyRequest;
import com.tripayweb.model.error.LoadMoneyError;

public class LoadMoneyValidation {

	public LoadMoneyError checkError(LoadMoneyRequest request){
		boolean valid = true;
		double amount  = Double.parseDouble(request.getAmount());
		LoadMoneyError error = new LoadMoneyError();
		if(CommonValidation.isNull(request.getAmount())){
			error.setAmount("Enter amount");
			valid = false;
		}
		else if(!CommonValidation.isNumeric(request.getAmount())){
			error.setAmount("Please enter valid amount");
			valid = false;
		}

		else if(amount <= 0 && amount >= 10000) {
			error.setAmount("Amount must be between 1 to 10000");
			valid  = false;
		}
		error.setValid(valid);
		return error;
		
	}
	
}
