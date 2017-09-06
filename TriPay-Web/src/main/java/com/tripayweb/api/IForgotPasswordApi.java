package com.tripayweb.api;

import com.tripayweb.model.app.request.ForgotPasswordRequest;
import com.tripayweb.model.app.response.ForgotPasswordResponse;

public interface IForgotPasswordApi {

	ForgotPasswordResponse request(ForgotPasswordRequest request);
}
