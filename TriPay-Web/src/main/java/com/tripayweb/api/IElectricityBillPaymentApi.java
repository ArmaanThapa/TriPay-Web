package com.tripayweb.api;

import com.tripayweb.model.app.request.ElectricityBillPaymentRequest;
import com.tripayweb.model.app.response.ElectricityBillPaymentResponse;

public interface IElectricityBillPaymentApi {

	ElectricityBillPaymentResponse request(ElectricityBillPaymentRequest request);

}
