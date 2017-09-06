package com.tripayweb.validation;

import com.tripayweb.app.model.request.OnePayRequest;
import com.tripayweb.model.error.OneClickPayError;

public class OneClickPayqwikValidation {
	public OneClickPayError checkError(OnePayRequest dto){
		OneClickPayError error=new OneClickPayError();
		if(!(CommonValidation.isNull(dto.getTransactionRefNo())&&Double.parseDouble(dto.getAmount())==0)){
			
		
		if(CommonValidation.isValidLoadMoneyTransaction(dto.getAmount())){
			error.setValidLoadMoneyTransaction(true);
			error.setNull(false);
			error.setValid(true);
			return error;
		}
		}
		error.setValid(false);
		return error;
	}

}
