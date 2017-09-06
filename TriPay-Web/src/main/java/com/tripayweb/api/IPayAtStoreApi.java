package com.tripayweb.api;

import com.tripayweb.model.app.request.PayAtStoreRequest;
import com.tripayweb.model.app.response.PayAtStoreResponse;

public interface IPayAtStoreApi {
	
	PayAtStoreResponse request(PayAtStoreRequest request);

}
