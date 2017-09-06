package com.tripayweb.api;

import com.tripayweb.model.app.request.LogoutRequest;
import com.tripayweb.model.app.response.LogoutResponse;

public interface ILogoutApi {

	LogoutResponse request(LogoutRequest request);
}
