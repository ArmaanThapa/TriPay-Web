package com.tripayweb.controller.mobile.api.thirdparty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.api.IMeraEventsApi;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.ResponseStatus;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.LoginRequest;
import com.tripayweb.app.model.request.MeraEventsCommonRequest;
import com.tripayweb.app.model.request.MeraEventsListRequest;
import com.tripayweb.app.model.response.MeraEventsListResponse;
import com.tripayweb.app.model.response.MeraEventsResponse;
import com.tripayweb.app.model.response.SendMoneyMobileResponse;
import com.tripayweb.util.APIUtils;
import com.tripayweb.util.LogCat;

@Controller
@RequestMapping("/Api/{version}/{role}/{device}/{language}/MeraEvents")
public class MeraEventsController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;
	
	private final IAuthenticationApi authenticationApi;
	private final IMeraEventsApi meraEventsApi;
	
	public MeraEventsController(IAuthenticationApi authenticationApi,IMeraEventsApi meraEventsApi) {
		this.authenticationApi = authenticationApi;
		this.meraEventsApi = meraEventsApi;
	}
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/AuthCode", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MeraEventsResponse> getAccessToken (@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
			@PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
			@RequestBody MeraEventsCommonRequest dto,@RequestHeader(value = "hash", required = false) String hash,
			HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		MeraEventsResponse resp = new MeraEventsResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				if (dto.getSessionId() != null || dto.getSessionId().length() > 0) {
				dto.setClientId(APIUtils.Test_ClientID);
				resp = meraEventsApi.getAuthorizationCode(dto);
				session.setAttribute("authenticationCode", resp.getResponse());
				if(resp.getCode().equalsIgnoreCase("S00")){
					dto.setAuthorizationCode((String)session.getAttribute("authenticationCode"));
					dto.setClientSecret(APIUtils.Test_ClientSecret);
					resp = meraEventsApi.getAccessToken(dto);
					if(resp.getCode().equalsIgnoreCase("S00")) {
						session.setAttribute("accessToken", resp.getResponse());
						resp.setCode("S00");
						resp.setSuccess(true);
						resp.setStatus(ResponseStatus.SUCCESS);
						resp.setMessage("Access token generated");
						resp.setResponse(resp.getResponse());
					} else {
						resp.setSuccess(false);
						resp.setCode("F00");
						resp.setMessage("Access Denied");
						resp.setStatus(ResponseStatus.FAILURE);
						resp.setResponse(APIUtils.getFailedJSON().toString());
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Client Authentication Failed");
					resp.setStatus(ResponseStatus.FAILURE);
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Session null");
					resp.setStatus(ResponseStatus.FAILURE);
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			} else {
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Unknown device");
				resp.setStatus(ResponseStatus.FAILURE);
				resp.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			resp.setSuccess(false);
			resp.setCode("F00");
			resp.setMessage("Un-authorized Role");
			resp.setStatus(ResponseStatus.FAILURE);
			resp.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<MeraEventsResponse>(resp, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/EventList", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MeraEventsListResponse> getEventList (@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
			@PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
			@RequestBody MeraEventsListRequest dto,@RequestHeader(value = "hash", required = false) String hash,
			HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		MeraEventsListResponse resp = new MeraEventsListResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				if (dto.getSessionId() != null || dto.getSessionId().length() > 0) {
				dto.setCountryId(14);
				LogCat.print("Country Id : " +dto.getCountryId());
				dto.setAccess_token((String)session.getAttribute("accessToken"));
				LogCat.print("Access Token : : "+dto.getAccess_token());
				resp = meraEventsApi.getEventList(dto);
				if(resp.getCode().equalsIgnoreCase("S00")){
					resp.setCode("S00");
					resp.setSuccess(true);
					resp.setStatus(ResponseStatus.SUCCESS);
					resp.setMessage("Event List : ");
					resp.setResponse(resp.getResponse());
					
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Client Authentication Failed");
					resp.setStatus(ResponseStatus.FAILURE);
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Session null");
					resp.setStatus(ResponseStatus.FAILURE);
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			} else {
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Unknown device");
				resp.setStatus(ResponseStatus.FAILURE);
				resp.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			resp.setSuccess(false);
			resp.setCode("F00");
			resp.setMessage("Un-authorized Role");
			resp.setStatus(ResponseStatus.FAILURE);
			resp.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<MeraEventsListResponse>(resp, HttpStatus.OK);
	}
	
	
	
	
	
	
	}



