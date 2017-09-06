package com.tripayweb.api;

import com.tripayweb.model.app.request.DTHBillPaymentRequest;
import com.tripayweb.model.app.response.DTHBillPaymentResponse;

public interface IDTHBillPaymentApi {

	DTHBillPaymentResponse request(DTHBillPaymentRequest request);

}
