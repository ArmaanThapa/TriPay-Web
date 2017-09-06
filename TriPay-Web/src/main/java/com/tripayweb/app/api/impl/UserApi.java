package com.tripayweb.app.api.impl;

import com.tripayweb.app.model.*;
import com.tripayweb.app.model.request.*;
import com.tripayweb.app.model.response.*;
import com.tripayweb.model.web.SharePointDTO;
import com.tripayweb.util.ConvertUtil;
import com.tripayweb.util.JSONParserUtil;
import org.apache.commons.codec.binary.Base64;
import org.codehaus.jettison.json.JSONObject;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.tripayweb.app.api.IUserApi;
import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.app.utils.SecurityUtils;
import com.tripayweb.model.app.response.RedeemCodeResponse;
import com.tripayweb.util.APIUtils;
import com.tripayweb.util.LogCat;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.util.List;

public class UserApi implements IUserApi {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	private Object nullObject = null;

	@Override
	public UserDetailsResponse getUserDetails(UserDetailsRequest request, Role role) {
		UserDetailsResponse resp = new UserDetailsResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());

			LogCat.print("inside get user details api");
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getUserDetailsUrl(Version.VERSION_1, role, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			LogCat.print("HASH KEY :: " + SecurityUtils.getHash(payload.toString()));
			if (response.getStatus() != 200) {
				LogCat.print("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("UserDetails Response :: " + strResponse);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");

						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
							final double balance = (double) jobj.getJSONObject("details").getJSONObject("accountDetail")
									.get("balance");
							final int points = (int) jobj.getJSONObject("details").getJSONObject("accountDetail")
									.get("points");
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
							resp.setUserType(userType);
							resp.setCode(code);
							resp.setAccountType(accountTypeDescription);
							resp.setDailyTransaction(accountTypeDailyLimit);
							resp.setMonthlyTransaction(accountTypeMonthlyLimit);
							resp.setUserType(accountTypeName);
							resp.setBalance(balance);
							resp.setPoints(points);
							resp.setAccountNumber(accountNumber);
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
	public EditProfileResponse editProfile(EditProfileRequest request) {
		EditProfileResponse resp = new EditProfileResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("username", nullObject);
			payload.put("password", nullObject);
			payload.put("confirmPassword", nullObject);
			payload.put("userType", nullObject);
			payload.put("authority", nullObject);
			payload.put("status", nullObject);
			payload.put("address", request.getAddress());
			payload.put("contactNo", nullObject);
			payload.put("firstName", request.getFirstName());
			payload.put("middleName", "");
			payload.put("lastName", request.getLastName());
			payload.put("locationCode", nullObject);
			payload.put("gender", request.getGender());
			payload.put("dateOfBirth", nullObject);
			payload.put("email", request.getEmail());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getEditProfileUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			LogCat.print("URL "
					+ UrlMetadatas.getEditProfileUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			String strResponse = response.getEntity(String.class);
			if (response.getStatus() != 200) {
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				LogCat.print("EditProfile Response :: " + strResponse);
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {

				LogCat.print("EditProfile Response :: " + strResponse);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						final String details = (String) jobj.get("details");

						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setDetails(" ");
						resp.setResponse(strResponse);
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
					resp.setStatus("FAILED");
					resp.setDetails(" ");
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setCode("F00");
			resp.setMessage("Service unavailable");
			resp.setStatus("FAILED");
			resp.setDetails(" ");
			resp.setResponse(APIUtils.getFailedJSON().toString());
		}
		return resp;
	}

	@Override
	public MerchantResponse getAllMerchants(SessionDTO dto) {
		MerchantResponse resp = new MerchantResponse();
		try {

			JSONObject payload = new JSONObject();
			payload.put("sessionId", dto.getSessionId());
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getMerchantsURL(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				LogCat.print("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
			} else {
				LogCat.print("Response :: " + response.getStatus());
				String strResponse = response.getEntity(String.class);
				LogCat.print("ChangePassword Response :: " + strResponse);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
							JSONArray array = JSONParserUtil.getArray(jobj, "details");
							List<MerchantDTO> merchantList = ConvertUtil.convertFromArray(array);
							resp.setMerchantList(merchantList);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setMessage(message);
					} else {
						resp.setSuccess(false);
						resp.setCode("F00");
						resp.setMessage("Service unavailable");
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setCode("F00");
			resp.setMessage("Service unavailable");
		}
		return resp;
	}

	@Override
	public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
		ChangePasswordResponse resp = new ChangePasswordResponse();
		try {
			JSONObject payload = new JSONObject();
			// sessionId , password , username , newPassword , confirmPassword ,
			// key
			payload.put("sessionId", request.getSessionId());
			payload.put("password", request.getOldPassword());
			payload.put("username", request.getUsername());
			payload.put("newPassword", request.getNewPassword());
			payload.put("confirmPassword", request.getConfirmPassword());
			payload.put("key", request.getUsername());

			LogCat.print("Payload :: " + payload);

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getUpdatePasswordUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				LogCat.print("Response :: " + response.getStatus());

				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				LogCat.print("Response :: " + response.getStatus());
				String strResponse = response.getEntity(String.class);
				LogCat.print("ChangePassword Response :: " + strResponse);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						// final String details = (String) jobj.get("details");
						// LogCat.print("Details " + details);
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
	public ChangePasswordResponse updateMerchantPassword(ChangePasswordRequest request) {
		ChangePasswordResponse resp = new ChangePasswordResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("password", request.getOldPassword());
			payload.put("username", request.getUsername());
			payload.put("newPassword", request.getNewPassword());
			payload.put("confirmPassword", request.getConfirmPassword());
			payload.put("key", request.getUsername());

			Client client = Client.create();
			WebResource webResource = client.resource(UrlMetadatas.renewMerchantPasswordURL(Version.VERSION_1,
					Role.MERCHANT, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				LogCat.print("Response :: " + response.getStatus());

				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				LogCat.print("Response :: " + response.getStatus());
				String strResponse = response.getEntity(String.class);
				LogCat.print("ChangePassword Response :: " + strResponse);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						// final String details = (String) jobj.get("details");
						// LogCat.print("Details " + details);
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
	public UploadPictureResponse uploadPicture(UploadPictureRequest request) {
		UploadPictureResponse resp = new UploadPictureResponse();

		MultipartFile file = null;
		String fileName = null;

		try {
			file = request.getProfilePicture();
			fileName = "";
			JSONObject payload = new JSONObject();
			LogCat.print("session id :: " + request.getSessionId());
			payload.put("p", request.getPath());
			payload.put("s", request.getSessionId());
			payload.put("f", fileName);
			LogCat.print("fileFormat :: " + ".png");
			LogCat.print("Payload " + payload);
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getUploadPictureUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);
			LogCat.print("URL :: "
					+ UrlMetadatas.getUploadPictureUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			if (response.getStatus() != 200) {
				LogCat.print("Response  :::: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setDetails(" ");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("Delete MPIN :: " + strResponse);
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
						resp.setDetails(" ");
						resp.setResponse(strResponse);
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
					resp.setDetails(" ");
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
			resp.setDetails(" ");
			resp.setResponse(APIUtils.getFailedJSON().toString());
		}
		return resp;
	}

	@Override
	public ReceiptsResponse getReceipts(ReceiptsRequest request) {
		ReceiptsResponse resp = new ReceiptsResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("page", request.getPage());
			payload.put("size", request.getSize());
			payload.put("userStatus", "");
			LogCat.print("Receipts Payload :: " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getReceiptsUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			LogCat.print("URL :: "
					+ UrlMetadatas.getReceiptsUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			if (response.getStatus() != 200) {
				LogCat.print("1");
				LogCat.print("Response code :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("Receipts :: " + strResponse);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");

						if (code.equalsIgnoreCase("S00")) {
							final JSONArray receiptsArray = jobj.getJSONObject("details").getJSONArray("content");
							resp.setAdditionalInfo(receiptsArray);

							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setResponse(strResponse);
					} else {
						LogCat.print("22");
						resp.setSuccess(false);
						resp.setCode("F00");
						resp.setMessage("Service unavailable");
						resp.setStatus("FAILED");
						resp.setResponse(APIUtils.getFailedJSON().toString());
					}
				} else {
					LogCat.print("2");
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
					resp.setStatus("FAILED");
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			}
		} catch (Exception e) {
			LogCat.print("3");
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
	public ReceiptsResponse getMerchantReceipts(ReceiptsRequest request) {
		ReceiptsResponse resp = new ReceiptsResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("page", request.getPage());
			payload.put("size", request.getSize());
			payload.put("userStatus", "");
			LogCat.print("Merchant Receipts Payload :: " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(UrlMetadatas.getVpayqwikMerchantTransactionsURL(Version.VERSION_1,
					Role.MERCHANT, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				LogCat.print("Response code :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("Receipts :: " + strResponse);
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
	public ReceiptsResponse getSuccessfulTransactions(SessionDTO dto) {
		ReceiptsResponse resp = new ReceiptsResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", dto.getSessionId());
			LogCat.print("Transactions Payload :: " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getTransactionsUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				LogCat.print("1");
				LogCat.print("Response code :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("Transactions :: " + strResponse);
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
						LogCat.print("22");
						resp.setSuccess(false);
						resp.setCode("F00");
						resp.setMessage("Service unavailable");
						resp.setStatus("FAILED");
						resp.setResponse(APIUtils.getFailedJSON().toString());
					}
				} else {
					LogCat.print("2");
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
					resp.setStatus("FAILED");
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			}
		} catch (Exception e) {
			LogCat.print("3");
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
	public ReceiptsResponse updateFavouriteTransaction(FavouriteRequest dto) {
		ReceiptsResponse resp = new ReceiptsResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", dto.getSessionId());
			payload.put("favourite", dto.isFavourite());
			payload.put("transactionRefNo", dto.getTransactionRefNo());

			LogCat.print("Favourite Payload :: " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getFavouriteUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				LogCat.print("1");
				LogCat.print("Response code :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("Transactions :: " + strResponse);
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
						LogCat.print("22");
						resp.setSuccess(false);
						resp.setCode("F00");
						resp.setMessage("Service unavailable");
						resp.setStatus("FAILED");
						resp.setResponse(APIUtils.getFailedJSON().toString());
					}
				} else {
					LogCat.print("2");
					resp.setSuccess(false);
					resp.setCode("F00");
					resp.setMessage("Service unavailable");
					resp.setStatus("FAILED");
					resp.setResponse(APIUtils.getFailedJSON().toString());
				}
			}
		} catch (Exception e) {
			LogCat.print("3");
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
	public MPINResponse setMPIN(SetMPINRequest request) {
		MPINResponse resp = new MPINResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("username", request.getUsername());
			payload.put("newMpin", request.getNewMpin());
			payload.put("confirmMpin", request.getConfirmMpin());
			payload.put("dateOfBirth", request.getDateOfBirth());
			LogCat.print("URL ::" + payload);

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getSetMPINUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			LogCat.print("URL ::"
					+ UrlMetadatas.getSetMPINUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			if (response.getStatus() != 200) {
				LogCat.print("Response ::" + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("Set MPIN :: " + strResponse);
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
	public MPINResponse changeMPIN(ChangeMPINRequest request) {
		MPINResponse resp = new MPINResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("username", request.getUsername());
			payload.put("oldMpin", request.getOldMpin());
			payload.put("newMpin", request.getNewMpin());
			payload.put("confirmMpin", request.getConfirmMpin());
			LogCat.print("Payload  :: " + payload);

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getChangeMPINUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			LogCat.print("Request "
					+ UrlMetadatas.getChangeMPINUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			if (response.getStatus() != 200) {
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("Change MPIN :: " + strResponse);
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
	public MPINResponse deleteMPIN(DeleteMPINRequest request) {
		MPINResponse resp = new MPINResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			LogCat.print("payload" + payload);
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getDeleteMPINUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
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
				LogCat.print("Delete MPIN :: " + strResponse);
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
	public MPINResponse forgotMPIN(ForgotMpinRequest request) {
		MPINResponse resp = new MPINResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("password", request.getPassword());
			payload.put("dateOfBirth", request.getDateOfBirth());
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getForgotMPINUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);
			String strResponse = response.getEntity(String.class);
			if (response.getStatus() != 200) {
				System.out.print(strResponse);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				LogCat.print("Forgot Mpin :: " + strResponse);
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
							if (code.equalsIgnoreCase("F04")) {
								org.json.JSONObject json = JSONParserUtil.getObject(jobj, "details");
								final boolean valid = JSONParserUtil.getBoolean(json, "valid");
								final String password = JSONParserUtil.getString(json, "password");
								final String dateOfBirth = JSONParserUtil.getString(json, "dateOfBirth");
								if (password != null) {
									resp.setResponse(password);
								}
								if (dateOfBirth != null) {
									resp.setDetails(dateOfBirth);
								}
							}
						}
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
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
	public MPINResponse verifyMPIN(SetMPINRequest request) {
		MPINResponse resp = new MPINResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("username", request.getUsername());
			payload.put("oldMpin", request.getNewMpin());
			payload.put("newMpin", "");
			payload.put("confirmMpin", "");
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getVerifyMPINUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

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
				LogCat.print("Delete MPIN :: " + strResponse);
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
	public InviteFriendEmailResponse inviteEmailFriend(InviteFriendEmailRequest request) {
		InviteFriendEmailResponse resp = new InviteFriendEmailResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("receiversName", request.getReceiversName());
			payload.put("message", (request.getMessage() == null) ? "" : request.getMessage());
			payload.put("email", request.getEmail());
			payload.put("mobileNo", (request.getMobileNo() == null) ? "" : request.getMobileNo());

			LogCat.print("" + payload);

			Client client = Client.create();
			WebResource webResource = client.resource(UrlMetadatas.getInviteEmailFriendUrl(Version.VERSION_1, Role.USER,
					Device.WEBSITE, Language.ENGLISH));

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
				LogCat.print("Invite Friend Response :: " + strResponse);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");

						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						System.err.print("invite friend success code is" + code);
						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
							System.err.print("invite friend success code is S00");
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
	public InviteFriendMobileResponse inviteMobileFriend(InviteFriendMobileRequest request) {
		InviteFriendMobileResponse resp = new InviteFriendMobileResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("receiversName", request.getReceiversName());
			payload.put("message", "You are invited to download VPayQwik app");
			payload.put("email", "");
			payload.put("mobileNo", request.getMobileNo());

			LogCat.print("payload" + payload);

			Client client = Client.create();
			WebResource webResource = client.resource(UrlMetadatas.getInviteMobileFriendUrl(Version.VERSION_1,
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
				LogCat.print("Delete MPIN :: " + strResponse);
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

	public static String encodeImage(byte[] imageByteArray) {
		return Base64.encodeBase64URLSafeString(imageByteArray);
	}

	@Override
	public UserDetailsByAdminResponse userTransactionAndDetails(UserDetailsByAdminRequest request) {

		UserDetailsByAdminResponse resp = new UserDetailsByAdminResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("page", 0);
			payload.put("size", 100);

			LogCat.print("payload" + payload);

			Client client = Client.create();
			WebResource webResource = client.resource(UrlMetadatas.getUserDetailByAdminUrl(Version.VERSION_1,
					Role.ADMIN, Device.WEBSITE, Language.ENGLISH, request.getUsername()));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			LogCat.print("URL :: " + UrlMetadatas.getUserDetailByAdminUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE,
					Language.ENGLISH, request.getUsername()));

			if (response.getStatus() != 200) {
				LogCat.print("Response ::" + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("Responwse :: " + strResponse);
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
	public UserDetailsResponse reSendEmailTop(UserDetailsRequest request) {
		UserDetailsResponse resp = new UserDetailsResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());

			LogCat.print("inside ResendEmail api");
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.reSendEmailOTPUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			LogCat.print("HASH KEY :: " + SecurityUtils.getHash(payload.toString()));
			if (response.getStatus() != 200) {
				LogCat.print("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("UserDetails Response :: " + strResponse);
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
	public RedeemCodeResponse redeemPromoCode(RedeemCodeRequest request) {
		RedeemCodeResponse resp = new RedeemCodeResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("promoCode", request.getPromoCode());

			System.err.println("payload :: " + payload);

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.redeemPromoCode(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			System.err.println("URL  "
					+ UrlMetadatas.redeemPromoCode(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			LogCat.print("HASH KEY :: " + SecurityUtils.getHash(payload.toString()));
			if (response.getStatus() != 200) {
				LogCat.print("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("UserDetails Response :: " + strResponse);
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
	public SharePointsResponse sharePoints(SharePointDTO dto) {
		SharePointsResponse resp = new SharePointsResponse();
		try {

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getSharePointsURL(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(dto.toJSON().toString()))
					.post(ClientResponse.class, dto.toJSON());

			System.err.println("URL  "
					+ UrlMetadatas.getSharePointsURL(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			LogCat.print("HASH KEY :: " + SecurityUtils.getHash(dto.toJSON().toString()));
			if (response.getStatus() != 200) {
				LogCat.print("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("UserDetails Response :: " + strResponse);
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
	public KycResponse kycVerification(KycVerificationDTO request) {

		KycResponse resp = new KycResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("mobileNumber", request.getMobileNumber());
			payload.put("accountNumber", request.getAccountNumber());
			System.err.println("payload :: " + payload);

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.kycRequestUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			System.err.println("URL  "
					+ UrlMetadatas.kycRequestUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			LogCat.print("HASH KEY :: " + SecurityUtils.getHash(payload.toString()));
			if (response.getStatus() != 200) {
				LogCat.print("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("UserDetails Response :: " + strResponse);
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
						// resp.setResponse(strResponse);
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
	public KycResponse kycOTPVerification(KycVerificationDTO request) {

		KycResponse resp = new KycResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("otp", request.getOtp());
			System.err.println("payload :: " + payload);

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.kycOtpVerificationUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			System.err.println("URL  " + UrlMetadatas.kycOtpVerificationUrl(Version.VERSION_1, Role.USER,
					Device.WEBSITE, Language.ENGLISH));
			LogCat.print("HASH KEY :: " + SecurityUtils.getHash(payload.toString()));
			if (response.getStatus() != 200) {
				LogCat.print("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("UserDetails Response :: " + strResponse);
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
						// resp.setResponse(strResponse);
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
	public KycResponse resendKycOTP(KycVerificationDTO request) {

		KycResponse resp = new KycResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("mobileNumber", request.getMobileNumber());
			payload.put("key", "");
			LogCat.print(payload.toString());
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getResendKycOtpUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
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
						final Object details = (Object) jobj.get("details");
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