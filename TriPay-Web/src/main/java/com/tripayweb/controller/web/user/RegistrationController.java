package com.tripayweb.controller.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.recaptcha.model.JCaptchaRequest;
import com.google.recaptcha.model.JCaptchaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.recaptcha.api.IVerificationApi;
import com.google.recaptcha.model.ReCaptchaRequest;
import com.google.recaptcha.model.ReCaptchaResponse;
import com.tripayweb.app.api.IRegistrationApi;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.MobileOTPRequest;
import com.tripayweb.app.model.request.RegistrationRequest;
import com.tripayweb.app.model.request.ResendMobileOTPRequest;
import com.tripayweb.app.model.request.VerifyEmailRequest;
import com.tripayweb.app.model.response.MobileOTPResponse;
import com.tripayweb.app.model.response.RegistrationResponse;
import com.tripayweb.app.model.response.ResendMobileOTPResponse;
import com.tripayweb.app.model.response.VerifyEmailResponse;
import com.tripayweb.model.error.MobileOTPError;
import com.tripayweb.model.error.RegisterError;
import com.tripayweb.util.APIUtils;
import com.tripayweb.validation.MobileOTPValidation;
import com.tripayweb.validation.RegisterValidation;

import java.text.SimpleDateFormat;

@Controller
@RequestMapping(value = "/Api/{version}/{role}/{device}/{language}/WebRegistration")
public class RegistrationController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	private final IRegistrationApi registrationApi;
	private final IVerificationApi verificationApi;
	private final RegisterValidation registerValidation;
	private final MobileOTPValidation mobileOTPValidation;

	public RegistrationController(IRegistrationApi registrationApi, IVerificationApi verificationApi,
			RegisterValidation registerValidation, MobileOTPValidation mobileOTPValidation) {
		this.registrationApi = registrationApi;
		this.verificationApi = verificationApi;
		this.registerValidation = registerValidation;
		this.mobileOTPValidation = mobileOTPValidation;
	}

	@Override
	public void setMessageSource(MessageSource arg0) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/Process", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<RegistrationResponse> processRegistration(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody RegistrationRequest dto,
			HttpServletRequest request, HttpServletResponse response) 
	{
		System.err.println("inside the we register Controller");

		RegistrationResponse result = new RegistrationResponse();
//		JCaptchaRequest cap = new JCaptchaRequest();
//		cap.setSessionId(request.getSession().getId());
//		cap.setCaptchaResponse(dto.getCaptchaResponse());
//		JCaptchaResponse jCaptchaResponse = verificationApi.isValidJCaptcha(cap);
		RegisterError error = registerValidation.checkError(dto);
		System.err.println(error);
		if (error.isValid()) {
			System.err.println("after the validation of the register user");
//			if (jCaptchaResponse.isValid()) {
				if (role.equalsIgnoreCase(Role.USER.getValue())) {
					if (device.equalsIgnoreCase(Device.ANDROID.getValue())
							|| device.equalsIgnoreCase(Device.WINDOWS.getValue())
							|| device.equalsIgnoreCase(Device.IOS.getValue())) {
						result = registrationApi.register(dto);
					} else {
						result.setSuccess(false);
						result.setCode("F00");
						result.setMessage("Unknown device");
						result.setStatus("FAILED");
						result.setResponse(APIUtils.getFailedJSON().toString());
					}
				} else {
					result.setSuccess(false);
					result.setCode("F00");
					result.setMessage("Unauthorised access");
					result.setStatus("FAILED");
					result.setResponse(APIUtils.getFailedJSON().toString());
				}
//			} else {
//				result.setCode("F00");
//				result.setMessage("Invalid Captcha");
//			}
		} else {
			result.setCode("F00");
			result.setMessage("Input is not valid");
		}
		System.out.println("return jsp");
		return new ResponseEntity<RegistrationResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/MobileOTP", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<MobileOTPResponse> mobileOTP(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody MobileOTPRequest dto,
			HttpServletRequest request, HttpServletResponse response) {
		MobileOTPResponse result = new MobileOTPResponse();
		MobileOTPError error = mobileOTPValidation.checkError(dto);
		if (error.isValid()) {
			if (role.equalsIgnoreCase(Role.USER.getValue())) {
				if (device.equalsIgnoreCase(Device.ANDROID.getValue())
						|| device.equalsIgnoreCase(Device.WINDOWS.getValue())
						|| device.equalsIgnoreCase(Device.IOS.getValue())) {
					result = registrationApi.mobileOTP(dto);
				} else {
					result.setSuccess(false);
					result.setCode("F00");
					result.setMessage("Unknown device");
					result.setStatus("FAILED");
					result.setResponse(APIUtils.getFailedJSON().toString());
				}
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unauthorised access");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setCode("F00");
			result.setMessage("Invalid Input");
		}
		return new ResponseEntity<MobileOTPResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/ResendMobileOTP", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<ResendMobileOTPResponse> resendMobileOTP(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody ResendMobileOTPRequest dto,
			HttpServletRequest request, HttpServletResponse response) {
		ResendMobileOTPResponse result = new ResendMobileOTPResponse();
		JCaptchaRequest cap = new JCaptchaRequest();
		cap.setSessionId(request.getSession().getId());
		cap.setCaptchaResponse(dto.getCaptchaResponse());
		JCaptchaResponse jCaptchaResponse = verificationApi.isValidJCaptcha(cap);

		MobileOTPError error = mobileOTPValidation.checkError(dto);
		if (jCaptchaResponse.isValid()) {
			if (error.isValid()) {
				if (role.equalsIgnoreCase(Role.USER.getValue())) {
					if (device.equalsIgnoreCase(Device.ANDROID.getValue())
							|| device.equalsIgnoreCase(Device.WINDOWS.getValue())
							|| device.equalsIgnoreCase(Device.IOS.getValue())) {
						result = registrationApi.resendMobileOTP(dto);
					} else {
						result.setSuccess(false);
						result.setCode("F00");
						result.setMessage("Unknown device");
						result.setStatus("FAILED");
						result.setResponse(APIUtils.getFailedJSON().toString());
					}
				} else {
					result.setSuccess(false);
					result.setCode("F00");
					result.setMessage("Unauthorised access");
					result.setStatus("FAILED");
					result.setResponse(APIUtils.getFailedJSON().toString());
				}
			} else {
				result.setCode("F00");
				result.setMessage("Invalid Input");
			}
		}else{
			result.setCode("F00");
			result.setMessage("Invalid Captcha");
		}
		return new ResponseEntity<ResendMobileOTPResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/Activate/Email/{key}")
	public String verifyEmail(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @PathVariable("key") String key,
			HttpServletRequest request, ModelMap modelMap, HttpServletResponse response) {
		VerifyEmailRequest emailRequest = new VerifyEmailRequest();
		if (key != null) {
			emailRequest.setKey(key);
			VerifyEmailResponse emailResponse = registrationApi.verifyEmail(emailRequest);
		}
		return "VerifyEmail";
	}

}
