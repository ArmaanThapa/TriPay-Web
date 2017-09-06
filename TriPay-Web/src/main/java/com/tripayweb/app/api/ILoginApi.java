package com.tripayweb.app.api;

import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.ChangePasswordWithOtpRequest;
import com.tripayweb.app.model.request.ForgetPasswordUserRequest;
import com.tripayweb.app.model.request.ResendForgotPasswordOtpRequest;
import com.tripayweb.app.model.response.ChangePasswordWithOtpResponse;
import com.tripayweb.app.model.response.ForgetPasswordUserResponse;
import com.tripayweb.app.model.response.LoginResponse;
import com.tripayweb.app.model.response.ResendForgotPasswordOtpResponse;
import com.tripayweb.app.model.request.LoginRequest;

public interface ILoginApi {

	LoginResponse login(LoginRequest request, Role role);

	ForgetPasswordUserResponse forgetPasswordUserRequest(ForgetPasswordUserRequest request);

	ChangePasswordWithOtpResponse changePasswordWithOtpRequest(ChangePasswordWithOtpRequest request);

	ResendForgotPasswordOtpResponse resendForgotPasswordOtpRequest(ResendForgotPasswordOtpRequest request);
}
