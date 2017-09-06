package com.tripayweb.api;

import com.tripayweb.model.app.request.PrepaidTopupRequest;
import com.tripayweb.model.app.response.PrepaidTopupResponse;

public interface IPrepaidTopupApi {

	
	PrepaidTopupResponse request(PrepaidTopupRequest request);
}
