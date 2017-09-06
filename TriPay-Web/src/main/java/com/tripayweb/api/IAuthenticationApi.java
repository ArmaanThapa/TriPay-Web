package com.tripayweb.api;

import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.response.UserDetailsResponse;

public interface IAuthenticationApi {

	String getAuthorityFromSession(String session , Role role);

	UserDetailsResponse getUserDetailsFromSession(String session);
}
