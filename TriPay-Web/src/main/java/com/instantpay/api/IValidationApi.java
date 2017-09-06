package com.instantpay.api;

import com.instantpay.model.request.ValidationRequest;
import com.thirdparty.model.ResponseDTO;

public interface IValidationApi {
     String getAmount(ValidationRequest dto);
     ResponseDTO validateTransaction(ValidationRequest dto);

}
