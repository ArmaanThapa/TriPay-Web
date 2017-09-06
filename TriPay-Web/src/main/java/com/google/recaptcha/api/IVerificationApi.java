package com.google.recaptcha.api;

import com.google.recaptcha.model.JCaptchaRequest;
import com.google.recaptcha.model.JCaptchaResponse;
import com.google.recaptcha.model.ReCaptchaRequest;
import com.google.recaptcha.model.ReCaptchaResponse;

public interface IVerificationApi {
	
//	ReCaptchaResponse isValidCapatcha(ReCaptchaRequest request);
	JCaptchaResponse isValidJCaptcha(JCaptchaRequest request);
}
