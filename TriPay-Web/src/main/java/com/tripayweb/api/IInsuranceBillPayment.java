package com.tripayweb.api;

import com.tripayweb.model.app.request.InsuranceBillPaymentRequest;
import com.tripayweb.model.app.response.InsuranceBillPaymentResponse;

public interface IInsuranceBillPayment {

	InsuranceBillPaymentResponse request(InsuranceBillPaymentRequest request);
}
