package com.tripayweb.api;

import com.tripayweb.model.app.request.GasBillPaymentRequest;
import com.tripayweb.model.app.response.GasBillPaymentResponse;

public interface IGasBillPaymentApi {

	GasBillPaymentResponse request(GasBillPaymentRequest request);
}
