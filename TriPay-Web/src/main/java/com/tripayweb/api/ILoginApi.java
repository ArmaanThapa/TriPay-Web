package com.tripayweb.api;

import com.tripayweb.model.app.request.LoginRequest;
import com.tripayweb.model.app.response.LoginResponse;

public interface ILoginApi {

	LoginResponse request(LoginRequest request);

}
