package com.tripayweb.api;

import com.tripayweb.model.app.request.DataCardTopupRequest;
import com.tripayweb.model.app.response.DataCardTopupResponse;

public interface IDataCardTopupApi {

	DataCardTopupResponse request(DataCardTopupRequest request);
	
}
