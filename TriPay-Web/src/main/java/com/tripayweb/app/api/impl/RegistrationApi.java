package com.tripayweb.app.api.impl;

import com.tripayweb.model.app.request.RegisterRequest;
import com.tripayweb.model.web.Status;
import com.tripayweb.model.web.UserType;
import com.tripayweb.util.JSONParserUtil;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.api.IRegistrationApi;
import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Language;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.Version;
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
import com.tripayweb.app.utils.SecurityUtils;
import com.tripayweb.util.APIUtils;
import com.tripayweb.util.LogCat;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class RegistrationApi implements IRegistrationApi {


	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public RegistrationResponse register(RegistrationRequest request)
	{
		
		System.err.println("in the registeration api");

		RegistrationResponse resp = new RegistrationResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("contactNo", request.getContactNo());
			payload.put("password", request.getPassword());
			payload.put("locationCode", request.getLocationCode());
			payload.put("firstName", request.getFirstName());
			payload.put("lastName", request.getLastName());
			payload.put("gender", request.getGender());
			payload.put("dateOfBirth", request.getDateOfBirth());
			payload.put("email", request.getEmail());
			payload.put("confirmPassword", request.getConfirmPassword());
			System.err.println("inside the register Api");
			
			
			
			
//			payload.put("userType", UserType.User);
//			payload.put("authority", "");
//			payload.put("status", Status.Inactive);
//			payload.put("address", "");
//			payload.put("middleName", "");
//			payload.put("accountNumber",request.getAccountNumber());
			LogCat.print(payload.toString());
			System.err.print("payload is ::"+payload.toString());
			Client client = Client.create();
			
			WebResource webResource = client.resource(
					UrlMetadatas.getRegisterUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);
			if (response.getStatus() != 200) {
				String strResponse = response.getEntity(String.class);
				System.err.print("registration error response ::"+strResponse);
				LogCat.print(strResponse);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				if (strResponse != null) {
					System.err.println("///////////////////////////register successfull");
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					System.err.print("registration sucess response ::"+strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						resp.setMessage(message);
						String errors = "";
						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							org.json.JSONObject details = JSONParserUtil.getObject(jobj,"details");
							if(details != null){
								boolean valid = JSONParserUtil.getBoolean(details,"valid");
								if(!valid){
									final String contactNo = JSONParserUtil.getString(details,"contactNo");
									final String email = JSONParserUtil.getString(details,"email");
									final String pinCode = JSONParserUtil.getString(details,"locationCode");
									if(!contactNo.equals("null")){
										errors = errors+"|"+contactNo;
										resp.setMessage(contactNo);
									}
									if(!email.equals("null")){
										errors = errors+"|"+email;
										resp.setMessage(email);
									}
									if(!pinCode.equals("null")) {
										errors = errors +"|"+pinCode;
										resp.setMessage(pinCode);
									}
								}
							}
							resp.setSuccess(false);
							resp.setDetails(errors);
						}

						resp.setCode(code);
						resp.setStatus(status);
						resp.setResponse(strResponse);
					}
					} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
					resp.setStatus("FAILED");
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setCode("F00");
			resp.setMessage("Service unavailable");
			resp.setStatus("FAILED");
			resp.setResponse(APIUtils.getFailedJSON().toString());
		}
		return resp;
	}

	@Override
	public MobileOTPResponse mobileOTP(MobileOTPRequest request) {

		MobileOTPResponse resp = new MobileOTPResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("mobileNumber", request.getMobileNumber());
			payload.put("key", request.getKey());
			LogCat.print(payload.toString());
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getMobileUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						final Object details = (String) jobj.get("details");
						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setDetails(details);
						resp.setResponse(strResponse);
					} else {
						resp.setSuccess(false);
						resp.setCode("F00");
						resp.setMessage("Service unavailable");
						resp.setStatus("FAILED");
						resp.setResponse(APIUtils.getFailedJSON().toString());
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
					resp.setStatus("FAILED");
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setCode("F00");
			resp.setMessage("Service unavailable");
			resp.setStatus("FAILED");
			resp.setResponse(APIUtils.getFailedJSON().toString());

		}
		return resp;
	}

	@Override
	public ResendMobileOTPResponse resendMobileOTP(ResendMobileOTPRequest request) {
		ResendMobileOTPResponse resp = new ResendMobileOTPResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", "");
			payload.put("mobileNumber", request.getMobileNumber());
			payload.put("key", "");
			System.err.println(request.getMobileNumber());
			LogCat.print(payload.toString());
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getResendMobileUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);
			String strResponse = response.getEntity(String.class);
			if (response.getStatus() != 200) {
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				if (strResponse != null) {
					LogCat.print(strResponse);
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						System.err.println("responseeeeeeeeeeeeeeeeee");
						final Object details = (Object) jobj.get("details");
						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setStatus(status);
						System.out.println("falseeeeeeeeeeeeeeeeeeeeeee inside");
						resp.setMessage(message);
						resp.setDetails(details);
						resp.setResponse(strResponse);
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
					resp.setStatus("FAILED");
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setCode("F00");
			resp.setMessage("Service unavailable");
			resp.setStatus("FAILED");
			resp.setResponse(APIUtils.getFailedJSON().toString());

		}
		return resp;
	}

	@Override
	public VerifyEmailResponse verifyEmail(VerifyEmailRequest request) {

		VerifyEmailResponse resp = new VerifyEmailResponse();
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getVerifyEmailUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH)+"/"+request.getKey());
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(request.getKey())).post(ClientResponse.class);
			String strResponse = response.getEntity(String.class);
			System.err.print(strResponse);

			if (response.getStatus() != 200) {
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						final Object details = (Object) jobj.get("details");

						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setDetails(details);
						resp.setResponse(strResponse);
					} 
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
					resp.setStatus("FAILED");
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setCode("F00");
			resp.setMessage("Service unavailable");
			resp.setStatus("FAILED");
			resp.setResponse(APIUtils.getFailedJSON().toString());

		}

		return resp;
	}

	@Override
	public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
		ChangePasswordResponse resp = new ChangePasswordResponse();
		try {


			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("oldPassword", request.getOldPassword());
			payload.put("newPassword", request.getNewPassword());
			payload.put("confirmPassword", request.getConfirmPassword());

			LogCat.print(payload.toString());
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getChangePasswordUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				String strResponse = response.getEntity(String.class);
				LogCat.print(strResponse);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setResponse(strResponse);
					} else {
						resp.setSuccess(false);
						resp.setCode("F00");
						resp.setMessage("Service unavailable");
						resp.setStatus("FAILED");
						resp.setResponse(APIUtils.getFailedJSON().toString());
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
					resp.setStatus("FAILED");
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setCode("F00");
			resp.setMessage("Service unavailable");
			resp.setStatus("FAILED");
			resp.setResponse(APIUtils.getFailedJSON().toString());
		}
		return resp;
	}

	@Override
	public RegistrationResponse registerMerchant(RegisterRequest request) {
		RegistrationResponse resp = new RegistrationResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("username", request.getContactNo());
			payload.put("password", "");
			payload.put("confirmPassword", "");
			payload.put("userType", "");
			payload.put("authority", "");
			payload.put("status", "");
			payload.put("address", "");
			payload.put("contactNo", request.getContactNo());
			payload.put("firstName", request.getFirstName());
			payload.put("middleName", "");
			payload.put("lastName", request.getLastName());
			payload.put("locationCode", "");
			payload.put("gender", "");
			payload.put("dateOfBirth", "");
			payload.put("email", request.getEmail());
			LogCat.print(payload.toString());
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getRegisterUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				String strResponse = response.getEntity(String.class);
				LogCat.print(strResponse);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setResponse(strResponse);
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
					resp.setStatus("FAILED");
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setCode("F00");
			resp.setMessage("Service unavailable");
			resp.setStatus("FAILED");
			resp.setResponse(APIUtils.getFailedJSON().toString());
		}
		return resp;

	}
}
