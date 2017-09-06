package com.tripayweb.api;

import com.tripayweb.model.app.request.VerifyMobileRequest;
import com.tripayweb.model.app.response.VerifyMobileResponse;

public interface IVerifyMobileApi {

	VerifyMobileResponse request(VerifyMobileRequest request);

}
