package com.tripayweb.controller.mobile.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.tripayweb.app.api.ILogoutApi;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.LogoutRequest;
import com.tripayweb.app.model.response.LogoutResponse;
import com.tripayweb.util.APIUtils;

@Controller
@RequestMapping("/Api/{version}/{role}/{device}/{language}/Logout")
public class LogoutAPIController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;

	private final ILogoutApi logoutApi;

	public LogoutAPIController(ILogoutApi logoutApi) {
		this.logoutApi = logoutApi;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@RequestMapping(value = "/Process", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<LogoutResponse> processLogout(
			@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
			@PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
			@RequestBody LogoutRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		LogoutResponse result = new LogoutResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = logoutApi.logout(dto, Role.USER);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		}else if(role.equalsIgnoreCase(Role.MERCHANT.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = logoutApi.logout(dto, Role.MERCHANT);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<LogoutResponse>(result, HttpStatus.OK);
	}
	
}
