package com.tripayweb.controller.mobile.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tripayweb.model.error.RegisterError;
import com.tripayweb.validation.RegisterValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tripayweb.app.api.IRegistrationApi;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.MobileOTPRequest;
import com.tripayweb.app.model.request.RegistrationRequest;
import com.tripayweb.app.model.request.ResendMobileOTPRequest;
import com.tripayweb.app.model.response.MobileOTPResponse;
import com.tripayweb.app.model.response.RegistrationResponse;
import com.tripayweb.app.model.response.ResendMobileOTPResponse;
import com.tripayweb.util.APIUtils;

@Controller
@RequestMapping("/Api/{version}/{role}/{device}/{language}/Registration")
public class RegistrationAPIController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;
	
	private final IRegistrationApi registrationApi;

	private final RegisterValidation registerValidation;

	public RegistrationAPIController(IRegistrationApi registrationApi,RegisterValidation registerValidation) {
		this.registrationApi = registrationApi;
		this.registerValidation = registerValidation;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/Process", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<RegistrationResponse> processRegistration(
			@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
			@PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
			@RequestBody RegistrationRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		
		RegistrationResponse result = new RegistrationResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) 
		{
			
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue()))
			{
				
				RegisterError  error = registerValidation.checkError(dto);
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
		return new ResponseEntity<RegistrationResponse>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/MobileOTP", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<MobileOTPResponse> mobileOTP(
			@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
			@PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
			@RequestBody MobileOTPRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		
		MobileOTPResponse result = new MobileOTPResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
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
		return new ResponseEntity<MobileOTPResponse>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ResendMobileOTP", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<ResendMobileOTPResponse> resendMobileOTP(
			@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
			@PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
			@RequestBody ResendMobileOTPRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		ResendMobileOTPResponse result = new ResendMobileOTPResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				System.err.println("in the resend otp controller");
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
		return new ResponseEntity<ResendMobileOTPResponse>(result, HttpStatus.OK);
	}
}
