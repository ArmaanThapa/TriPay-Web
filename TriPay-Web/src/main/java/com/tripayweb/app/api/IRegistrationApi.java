package com.tripayweb.app.api;

import com.tripayweb.app.model.request.ChangePasswordRequest;
import com.tripayweb.app.model.request.MobileOTPRequest;
import com.tripayweb.app.model.request.RegistrationRequest;
import com.tripayweb.app.model.request.ResendMobileOTPRequest;
import com.tripayweb.app.model.request.VerifyEmailRequest;
import com.tripayweb.app.model.response.ChangePasswordResponse;
import com.tripayweb.app.model.response.MobileOTPResponse;
import com.tripayweb.app.model.response.RegistrationResponse;
import com.tripayweb.app.model.response.ResendMobileOTPResponse;
import com.tripayweb.app.model.response.VerifyEmailResponse;
import com.tripayweb.model.app.request.RegisterRequest;

public interface IRegistrationApi {

	RegistrationResponse register(RegistrationRequest request);

	MobileOTPResponse mobileOTP(MobileOTPRequest request);

	ResendMobileOTPResponse resendMobileOTP(ResendMobileOTPRequest request);

	VerifyEmailResponse verifyEmail(VerifyEmailRequest request);
	
	ChangePasswordResponse changePassword(ChangePasswordRequest cpr);

	RegistrationResponse registerMerchant(RegisterRequest request);

}
