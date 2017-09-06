package com.tripayweb.api;

import com.tripayweb.model.app.request.EditProfileRequest;
import com.tripayweb.model.app.response.EditProfileResponse;

public interface IEditProfileApi {

	EditProfileResponse request(EditProfileRequest request);
}
