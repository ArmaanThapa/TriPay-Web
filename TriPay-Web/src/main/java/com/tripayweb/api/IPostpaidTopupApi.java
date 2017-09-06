package com.tripayweb.api;

import com.tripayweb.model.app.request.PostpaidTopupRequest;
import com.tripayweb.model.app.response.PostpaidTopupResponse;

public interface IPostpaidTopupApi {

	
	PostpaidTopupResponse request(PostpaidTopupRequest request);
}
