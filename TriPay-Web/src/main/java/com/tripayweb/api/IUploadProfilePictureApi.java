package com.tripayweb.api;

import com.tripayweb.model.app.request.UploadProfilePictureRequest;
import com.tripayweb.model.app.response.UploadProfilePictureResponse;

public interface IUploadProfilePictureApi {

	UploadProfilePictureResponse request(UploadProfilePictureRequest request);
}
