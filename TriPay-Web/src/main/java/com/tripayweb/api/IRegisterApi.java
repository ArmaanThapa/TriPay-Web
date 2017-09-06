package com.tripayweb.api;

import com.tripayweb.model.app.request.RegisterRequest;
import com.tripayweb.model.app.response.RegisterResponse;

public interface IRegisterApi {

	RegisterResponse request(RegisterRequest request);
}
