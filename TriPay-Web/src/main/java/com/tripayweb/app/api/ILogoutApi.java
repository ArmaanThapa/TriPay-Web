package com.tripayweb.app.api;

import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.LogoutRequest;
import com.tripayweb.app.model.response.LogoutResponse;

public interface ILogoutApi {

	LogoutResponse logout(LogoutRequest request, Role role);

}
