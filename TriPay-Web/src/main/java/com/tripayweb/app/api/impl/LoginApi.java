package com.tripayweb.app.api.impl;

import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.api.ILoginApi;
import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Language;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.Version;
import com.tripayweb.app.model.request.ChangePasswordWithOtpRequest;
import com.tripayweb.app.model.request.ForgetPasswordUserRequest;
import com.tripayweb.app.model.request.LoginRequest;
import com.tripayweb.app.model.request.ResendForgotPasswordOtpRequest;
import com.tripayweb.app.model.response.ChangePasswordWithOtpResponse;
import com.tripayweb.app.model.response.ForgetPasswordUserResponse;
import com.tripayweb.app.model.response.LoginResponse;
import com.tripayweb.app.model.response.ResendForgotPasswordOtpResponse;
import com.tripayweb.app.utils.SecurityUtils;
import com.tripayweb.util.APIUtils;
import com.tripayweb.util.LogCat;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class LoginApi implements ILoginApi {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public LoginResponse login(LoginRequest request, Role role) {
		LoginResponse resp = new LoginResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("username", request.getUsername());
			payload.put("password", request.getPassword());
			payload.put("ipAddress", request.getIpAddress());
			payload.put("registrationId", request.getRegistrationId());
			LogCat.print("PAYLOAD :: " + payload.toString());
			System.err.println("inside the login API");
            System.err.println(request.getUsername());
            System.err.println(request.getPassword());
            System.err.println(request.getIpAddress());
            System.err.println(request.getRegistrationId());
            
            
            
			Client client = Client.create();
			WebResource webResource = client
					.resource(UrlMetadatas.getLoginUrl(Version.VERSION_1, role, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			LogCat.print(
					"URL ::" + UrlMetadatas.getLoginUrl(Version.VERSION_1, role, Device.WEBSITE, Language.ENGLISH));

			LogCat.print("Hash :: " + SecurityUtils.getHash(payload.toString()));
			if (response.getStatus() != 200) {
				LogCat.print("CODE :: " + response.getStatus());
				String strResponse = response.getEntity(String.class);
				LogCat.print("RESPONSE :: " + strResponse);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("RESPONSE :: " + strResponse);
				System.err.println("Response::" + strResponse);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						// final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						if (code.equalsIgnoreCase("S00")) {

							final double balance = (double) jobj.getJSONObject("details").getJSONObject("accountDetail")
									.get("balance");
							final long accountNumber = (long) jobj.getJSONObject("details")
									.getJSONObject("accountDetail").get("accountNumber");
							final String accountTypeDescription = (String) jobj.getJSONObject("details")
									.getJSONObject("accountDetail").getJSONObject("accountType").get("description");
							final String accountTypeName = (String) jobj.getJSONObject("details")
									.getJSONObject("accountDetail").getJSONObject("accountType").get("name");

							final double accountTypeMonthlyLimit = (double) jobj.getJSONObject("details")
									.getJSONObject("accountDetail").getJSONObject("accountType").get("monthlyLimit");
							final double accountTypeDailyLimit = (double) jobj.getJSONObject("details")
									.getJSONObject("accountDetail").getJSONObject("accountType").get("dailyLimit");
							final double accountTypeBalanceLimit = (double) jobj.getJSONObject("details")
									.getJSONObject("accountDetail").getJSONObject("accountType").get("balanceLimit");

							final String username = (String) jobj.getJSONObject("details").getJSONObject("userDetail")
									.get("username");
							final String firstName = (String) jobj.getJSONObject("details").getJSONObject("userDetail")
									.get("firstName");
							final String middleName = (String) jobj.getJSONObject("details").getJSONObject("userDetail")
									.get("middleName");
							final String lastName = (String) jobj.getJSONObject("details").getJSONObject("userDetail")
									.get("lastName");
							final String address = (String) jobj.getJSONObject("details").getJSONObject("userDetail")
									.get("address");
							final String contactNo = (String) jobj.getJSONObject("details").getJSONObject("userDetail")
									.get("contactNo");
							final String userType = (String) jobj.getJSONObject("details").getJSONObject("userDetail")
									.get("userType");
							final String authority = (String) jobj.getJSONObject("details").getJSONObject("userDetail")
									.get("authority");
							final String emailStatus = (String) jobj.getJSONObject("details")
									.getJSONObject("userDetail").get("emailStatus");
							final String mobileStatus = (String) jobj.getJSONObject("details")
									.getJSONObject("userDetail").get("mobileStatus");
							final String email = (String) jobj.getJSONObject("details").getJSONObject("userDetail")
									.get("email");
							final String image = (String) jobj.getJSONObject("details").getJSONObject("userDetail")
									.get("image");
							final String sessionId = (String) jobj.getJSONObject("details").get("sessionId");

							resp.setAddress(address);
							resp.setAuthority(authority);
							resp.setContactNo(contactNo);
							resp.setEmail(email);
							resp.setEmailStatus(emailStatus);
							resp.setFirstName(firstName);
							resp.setImage(image);
							resp.setLastName(lastName);
							resp.setMiddleName(middleName);
							resp.setMobileStatus(mobileStatus);
							resp.setSessionId(sessionId);
							resp.setUserType(userType);
							resp.setCode(code);
							resp.setAccountType(accountTypeDescription);
							resp.setDailyTransaction(accountTypeDailyLimit);
							resp.setMonthlyTransaction(accountTypeMonthlyLimit);
							resp.setUserType(accountTypeName);
							resp.setBalance(balance);
							resp.setAccountNumber(accountNumber);
							resp.setSuccess(true);
							resp.setUsername(username);
							resp.setMessage(message);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						// resp.setStatus(status);
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

	public ForgetPasswordUserResponse forgetPasswordUserRequest(ForgetPasswordUserRequest request) {

		ForgetPasswordUserResponse resp = new ForgetPasswordUserResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("username", request.getUsername());
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getPasswordOtpUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

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
					} else {
						resp.setSuccess(false);
						resp.setCode("F00");
						resp.setMessage("Service unavailable");
						resp.setDetails(null);
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
	public ChangePasswordWithOtpResponse changePasswordWithOtpRequest(ChangePasswordWithOtpRequest request) {
		ChangePasswordWithOtpResponse resp = new ChangePasswordWithOtpResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("username", request.getUsername());
			payload.put("newPassword", request.getNewPassword());
			payload.put("confirmPassword", request.getConfirmPassword());
			payload.put("key", request.getKey());
			LogCat.print(payload.toString());
			Client client = Client.create();
			WebResource webResource = client.resource(UrlMetadatas.getChangePasswordWithOTPUrl(Version.VERSION_1,
					Role.USER, Device.WEBSITE, Language.ENGLISH));

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
						final Object details = (Object) jobj.get("details");
						LogCat.print(strResponse);
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
	public ResendForgotPasswordOtpResponse resendForgotPasswordOtpRequest(ResendForgotPasswordOtpRequest request) {
		ResendForgotPasswordOtpResponse resp = new ResendForgotPasswordOtpResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("mobileNumber", request.getMobileNumber());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.resetPasswordOtpUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

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

}
