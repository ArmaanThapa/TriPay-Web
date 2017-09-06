package com.tripayweb.api;

import com.tripayweb.model.app.request.LandlineBillPaymentRequest;
import com.tripayweb.model.app.response.LandlineBillPaymentResponse;

public interface ILandlineBillPaymentApi {
	
	LandlineBillPaymentResponse request(LandlineBillPaymentRequest request);

}
