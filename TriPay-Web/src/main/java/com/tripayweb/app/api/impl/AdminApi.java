package com.tripayweb.app.api.impl;

import com.tripayweb.app.model.*;
import com.tripayweb.app.model.request.*;
import com.tripayweb.app.model.response.*;
import com.tripayweb.util.JSONParserUtil;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.tripayweb.app.api.IAdminApi;
import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.app.utils.SecurityUtils;
import com.tripayweb.util.APIUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import net.bull.javamelody.Main;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdminApi implements IAdminApi {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public LoginResponse login(LoginRequest request) {
		LoginResponse resp = new LoginResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("username", request.getUsername());
			payload.put("password", request.getPassword());
			payload.put("ipAddress",request.getIpAddress());

			logger.info("Payload " + payload);

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getLoginUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			logger.info(
					"URL " + UrlMetadatas.getLoginUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));

			if (response.getStatus() != 200) {
				logger.info("Response 1" + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());

			} else {

				String strResponse = response.getEntity(String.class);
				logger.info("Response 2" + response.getStatus());
				if (strResponse != null) {
					logger.info("Response 3" + strResponse);
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						logger.info("Response 4");
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						final String sessionId = (String) jobj.getJSONObject("details").get("sessionId");

						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setResponse(strResponse);
						resp.setSessionId(sessionId);

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
	public ServiceTypeResponse getServiceType() {
		ServiceTypeResponse resp = new ServiceTypeResponse();
		try {

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getServiceTypesURL(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash("123456")).get(ClientResponse.class);
			logger.info("URL " + UrlMetadatas.getLoginUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));

			if (response.getStatus() != 200) {
				logger.info("Response 1" + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");

			} else {

				String strResponse = response.getEntity(String.class);
				logger.info("Response 2" + response.getStatus());
				if (strResponse != null) {
					logger.info("Response 3" + strResponse);
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						logger.info("Response 4");
						final String code = (String) jobj.get("code");
						if (code.equalsIgnoreCase("S00")) {
							List<ServiceTypeDTO> serviceTypeDTOs = new ArrayList<>();
							resp.setSuccess(true);
							org.json.JSONArray details = JSONParserUtil.getArray(jobj,"details");
							if(details != null && details.length() > 0) {
								for (int i=0;i<details.length();i++){
									ServiceTypeDTO s = new ServiceTypeDTO();
									org.json.JSONObject temp = (org.json.JSONObject) details.get(i);
									s.setId(JSONParserUtil.getLong(temp,"id"));
									s.setName(JSONParserUtil.getString(temp,"name"));
									s.setDescription(JSONParserUtil.getString(temp,"description"));
									serviceTypeDTOs.add(s);
								}
								resp.setServiceDTOs(serviceTypeDTOs);
							}
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
					} else {
						resp.setSuccess(false);
						resp.setCode("F00");
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setCode("F00");
		}
		return resp;
	}

	@Override
	public ServiceTypeResponse getService() {
		ServiceTypeResponse resp = new ServiceTypeResponse();
		try {

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getServiceURL(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash("123456")).get(ClientResponse.class);
			logger.info("URL " + UrlMetadatas.getLoginUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			if (response.getStatus() != 200) {
				logger.info("Response 1" + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");

			} else {

				String strResponse = response.getEntity(String.class);
				logger.info("Response 2" + response.getStatus());
				if (strResponse != null) {
					logger.info("Response 3" + strResponse);
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						logger.info("Response 4");
						final String code = (String) jobj.get("code");
						if (code.equalsIgnoreCase("S00")) {
							List<ServicesDTO> serviceTypeDTOs = new ArrayList<>();
							resp.setSuccess(true);
							org.json.JSONArray details = JSONParserUtil.getArray(jobj,"details");
							if(details != null && details.length() > 0) {
								for (int i=0;i<details.length();i++){
									ServicesDTO s = new ServicesDTO();
									org.json.JSONObject temp = (org.json.JSONObject) details.get(i);
									s.setCode(JSONParserUtil.getString(temp,"code"));
									s.setDescription(JSONParserUtil.getString(temp,"description"));
									serviceTypeDTOs.add(s);
								}
								resp.setServicesDTOs(serviceTypeDTOs);
							}
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
					} else {
						resp.setSuccess(false);
						resp.setCode("F00");
					}
				} else {
					resp.setSuccess(false);
					resp.setCode("F00");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setCode("F00");
		}
		return resp;
	}

	@Override
	public AllTransactionResponse getAllTransaction(AllTransactionRequest request) {
		AllTransactionResponse resp = new AllTransactionResponse();
		try {

			JSONObject payload = new JSONObject();
			logger.info("All Transactions");

			logger.info("Session in Api " + request.getSessionId());
			payload.put("sessionId", request.getSessionId());
			payload.put("page", request.getPage());
			payload.put("size", request.getSize());
			payload.put("start",request.getStartDate());
			payload.put("end",request.getEndDate());
			payload.put("userStatus", "");

			logger.info("Payload " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getAllTransactionUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			logger.info("URL " + UrlMetadatas.getAllTransactionUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE,
					Language.ENGLISH));
			String strResponse = response.getEntity(String.class);
			if (response.getStatus() != 200) {
				logger.info("Response 1 :: " + strResponse);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {


				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						System.err.println("Transaction1 :: " + jobj);
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						final org.json.JSONObject totalTtransaction = (org.json.JSONObject) jobj
								.getJSONObject("details");
						org.json.JSONArray totalTransactionArray = null;
						if (totalTtransaction != null) {
							totalTransactionArray = totalTtransaction.getJSONArray("content");
						}
						JSONArray jsonArray = new JSONArray();
						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}

						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setResponse(strResponse);
						resp.setMonthlyTransaction(totalTtransaction.length());
						resp.setJsonArray(totalTransactionArray);

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
	public AllTransactionResponse getSettlementTransactions(AllTransactionRequest request) {
		AllTransactionResponse resp = new AllTransactionResponse();
		try {

			JSONObject payload = new JSONObject();
			logger.info("All Transactions");

			payload.put("sessionId", request.getSessionId());
			payload.put("startDate",request.getStartDate());
			payload.put("endDate",request.getEndDate());
			payload.put("reportType", request.getReportType().toUpperCase());

			logger.info("Payload " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getSettlementsTransactionURL(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			String strResponse = response.getEntity(String.class);
			if (response.getStatus() != 200) {
				logger.info("Response 1 :: " + strResponse);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {


				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						System.err.println("Transaction1 :: " + jobj);
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						org.json.JSONArray totalTransactionArray = null;

						JSONArray jsonArray = new JSONArray();
						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
							totalTransactionArray = jobj.getJSONArray("details");

						} else {
							resp.setSuccess(false);
						}

						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setResponse(strResponse);
						resp.setJsonArray(totalTransactionArray);

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
	public AllTransactionResponse getPromoTransaction(AllTransactionRequest request) {
		AllTransactionResponse resp = new AllTransactionResponse();
		try {

			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("page", request.getPage());
			payload.put("size", request.getSize());
			payload.put("start",request.getStartDate());
			payload.put("end",request.getEndDate());
			payload.put("userStatus", "");

			logger.info("Payload " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getPromoTransactionsURL(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);
			String strResponse = response.getEntity(String.class);
			if (response.getStatus() != 200) {
				logger.info("Response 1 :: " + strResponse);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						System.err.println("Transaction1 :: " + jobj);
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						final org.json.JSONObject totalTtransaction = (org.json.JSONObject) jobj
								.getJSONObject("details");
						org.json.JSONArray totalTransactionArray = null;
						if (totalTtransaction != null) {
							totalTransactionArray = totalTtransaction.getJSONArray("content");
						}
						JSONArray jsonArray = new JSONArray();
						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setResponse(strResponse);
						resp.setMonthlyTransaction(totalTtransaction.length());
						resp.setJsonArray(totalTransactionArray);

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
	public UserTransactionResponse getUserTransactionValues(SessionDTO dto) {
		UserTransactionResponse resp = new UserTransactionResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", dto.getSessionId());

			logger.info("URL " + UrlMetadatas.getValuesListURL(Version.VERSION_1, Role.ADMIN, Device.WEBSITE,
					Language.ENGLISH));
			Client client = Client.create();
			WebResource webResource = client.resource(UrlMetadatas.getValuesListURL(Version.VERSION_1, Role.ADMIN,
					Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);
			String strResponse = response.getEntity(String.class);
			if (response.getStatus() != 200) {
				System.err.print(strResponse);
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
	public AllUserResponse getAllUser(AllUserRequest request) {
		AllUserResponse resp = new AllUserResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("page", request.getPage());
			payload.put("size", request.getSize());
			payload.put("userStatus", request.getStatus().getValue());

			logger.info("Payload " + payload);

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getAllUserUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);
			String strResponse = response.getEntity(String.class);
			logger.info("URL "
					+ UrlMetadatas.getAllUserUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			if (response.getStatus() != 200) {
				logger.info("Response userAll 1 :: " + strResponse);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {

				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						System.err.println("User :: " + jobj);
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						final org.json.JSONObject details = jobj.getJSONObject("details");
						final org.json.JSONArray totalUser = (org.json.JSONArray) jobj.getJSONObject("details")
								.getJSONArray("content");
						long numberOfElements = details.getLong("numberOfElements");
						boolean firstPage = details.getBoolean("firstPage");
						boolean lastPage = details.getBoolean("lastPage");
						long size  = details.getLong("size");
						long totalPages = details.getLong("totalPages");
						resp.setTotalPages(totalPages);
						resp.setSize(size);
						resp.setNumberOfElements(numberOfElements);
						resp.setFirstPage(firstPage);
						resp.setLastPage(lastPage);
						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setResponse(strResponse);
						resp.setTotalUser(totalUser.length());
						resp.setJsonArray(totalUser);

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
	public AllUserResponse getAllMerchants(AllUserRequest request) {
		AllUserResponse resp = new AllUserResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("page", request.getPage());
			payload.put("size", request.getSize());
			payload.put("userStatus", request.getStatus().getValue());

			logger.info("Payload " + payload);

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getAllMerchantURL(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);
			String strResponse = response.getEntity(String.class);
			logger.info("URL "
					+ UrlMetadatas.getAllUserUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			if (response.getStatus() != 200) {
				logger.info("Response userAll 1 :: " + strResponse);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {

				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						System.err.println("User :: " + jobj);
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");

						if (code.equalsIgnoreCase("S00")) {
							final org.json.JSONArray details = jobj.getJSONArray("details");
							resp.setJsonArray(details);
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
	public UserTransactionResponse getUserTransaction(UserTransactionRequest request) {
		UserTransactionResponse resp = new UserTransactionResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("page", request.getPage());
			payload.put("size", request.getSize());
			payload.put("username", request.getUsername());

			logger.info("URL " + UrlMetadatas.getUserTransactionUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE,
					Language.ENGLISH));
			Client client = Client.create();
			WebResource webResource = client.resource(UrlMetadatas.getUserTransactionUrl(Version.VERSION_1, Role.ADMIN,
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
	public UserTransactionResponse refundLoadMoneyTransactions(RefundDTO dto) {
		UserTransactionResponse resp = new UserTransactionResponse();
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(UrlMetadatas.getRefundAmountURL(Version.VERSION_1, Role.ADMIN,
					Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(dto.toJSON().toString())).post(ClientResponse.class, dto.toJSON());
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

	@Override
	public MessageLogResponse getMessageLog(MessageLogRequest request) {
		MessageLogResponse resp = new MessageLogResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("start", request.getStartDate());
			payload.put("end",request.getEndDate());
			payload.put("sessionId", request.getSessionId());
			payload.put("username", "");

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getMessageLogUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			logger.info("URL "
					+ UrlMetadatas.getMessageLogUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			if (response.getStatus() != 200) {
				logger.info("Response ::" + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				if (strResponse != null) {
					logger.info("Response :: " + strResponse.toString());
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						final org.json.JSONArray totalTtransaction = (org.json.JSONArray) jobj.getJSONArray("details");

						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setResponse(strResponse);
						resp.setJsonArray(totalTtransaction);
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
	public EmailLogResponse getEmailLog(EmailLogRequest request) {
		EmailLogResponse resp = new EmailLogResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("start", request.getStartDate());
			payload.put("end", request.getEndDate());
			payload.put("username", "");
			
			
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getEmailLogUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			logger.info("URL "
					+ UrlMetadatas.getEmailLogUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			if (response.getStatus() != 200) {

				logger.info("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				logger.info("Response in else part ");
				String strResponse = response.getEntity(String.class);
				if (strResponse != null) {
					logger.info("Response :::: " + strResponse.toString());
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						final org.json.JSONArray totalTtransaction = (org.json.JSONArray) jobj.getJSONArray("details");
						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setResponse(strResponse);
						resp.setJsonArray(totalTtransaction);
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
	public BlockUnBlockUserResponse blockUser(BlockUnBlockUserRequest request) {
		BlockUnBlockUserResponse resp = new BlockUnBlockUserResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("username", request.getUsername());
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.blockUserUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
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

	@Override
	public BlockUnBlockUserResponse unblockUser(BlockUnBlockUserRequest request) {
		BlockUnBlockUserResponse resp = new BlockUnBlockUserResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("username", request.getUsername());
			logger.info("Payload :: " + payload);

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.unblockUserUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			logger.info("URL :: "
					+ UrlMetadatas.unblockUserUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			if (response.getStatus() != 200) {

				logger.info("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());

			} else {
				logger.info("Response :: " + response.getStatus());

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
	public AllTransactionResponse getDaily(AllTransactionRequest request) {

		AllTransactionResponse resp = new AllTransactionResponse();
		try {

			JSONObject payload = new JSONObject();
			logger.info("All Transactions ");

			logger.info("Session in Api " + request.getSessionId());
			payload.put("sessionId", request.getSessionId());
			// TODO
			payload.put("start", request.getStartDate());
			payload.put("end", request.getEndDate());
			payload.put("username", "");

			logger.info("Payload for End Date " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(UrlMetadatas.getBetweenDateTransactions(Version.VERSION_1,
					Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			logger.info("URL " + UrlMetadatas.getBetweenDateTransactions(Version.VERSION_1, Role.ADMIN, Device.WEBSITE,
					Language.ENGLISH));

			if (response.getStatus() != 200) {
				logger.info("Response 1 :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {

				String strResponse = response.getEntity(String.class);
				logger.info("Response 2 :: " + strResponse);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						final org.json.JSONArray totalTtransaction = (org.json.JSONArray) jobj.getJSONArray("details");

						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						System.err.println("LENGTH :: " + totalTtransaction.length());
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setResponse(strResponse);
						resp.setMonthlyTransaction(totalTtransaction.length());
						resp.setJsonArray(totalTtransaction);
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
	public AllUserResponse getAllTransactions(AllTransactionRequest request) {

		AllUserResponse resp = new AllUserResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("page", request.getPage());
			payload.put("size", request.getSize());
			payload.put("reportType", "ALL");

			logger.info("Payload " + payload);

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getAllTransactionsAdminURL(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);
			String strResponse = response.getEntity(String.class);
			logger.info("URL "
					+ UrlMetadatas.getAllUserUrl(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			if (response.getStatus() != 200) {
				logger.info("Response userAll 1 :: " + strResponse);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {

				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						System.err.println("User :: " + jobj);
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						final org.json.JSONObject details = jobj.getJSONObject("details");
						final org.json.JSONArray totalUser = (org.json.JSONArray) jobj.getJSONObject("details")
								.getJSONArray("content");
						long numberOfElements = details.getLong("numberOfElements");
						boolean firstPage = details.getBoolean("firstPage");
						boolean lastPage = details.getBoolean("lastPage");
						long size  = details.getLong("size");
						long totalPages = details.getLong("totalPages");
						resp.setTotalPages(totalPages);
						resp.setSize(size);
						resp.setNumberOfElements(numberOfElements);
						resp.setFirstPage(firstPage);
						resp.setLastPage(lastPage);
						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setResponse(strResponse);
						resp.setTotalUser(totalUser.length());
						resp.setJsonArray(totalUser);

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
	public AllTransactionResponse getSingleUserTransaction(UserTransactionRequest request) {
		AllTransactionResponse resp = new AllTransactionResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("page", 0);
			payload.put("size", 100);
			payload.put("userStatus", "");
			Client client = Client.create();
			WebResource webResource = client.resource(UrlMetadatas.getUserDetailByAdminUrl(Version.VERSION_1,
					Role.ADMIN, Device.WEBSITE, Language.ENGLISH, request.getUsername()));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			logger.info("URL :: " + UrlMetadatas.getBetweenDateTransactions(Version.VERSION_1, Role.ADMIN,
					Device.WEBSITE, Language.ENGLISH));
			if (response.getStatus() != 200) {
				logger.info("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				logger.info("Response : " + response.getStatus());
				String strResponse = response.getEntity(String.class);
				if (strResponse != null) {
					logger.info("Response Data " + strResponse.toString());
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						final org.json.JSONArray totalTtransaction = (org.json.JSONArray) jobj.getJSONObject("details")
								.getJSONArray("content");

						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setResponse(strResponse);
						resp.setJsonArray(totalTtransaction);
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
	public BlockUserResponse userBlock(BlockUserRequest request) {
		BlockUserResponse resp = new BlockUserResponse();
		try {

			JSONObject payload = new JSONObject();
			logger.info("All Trancation ");
			payload.put("sessionId", request.getAdminSessionId());
			payload.put("username", request.getUsername());
			logger.info("Payload for End Date " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getBlockUser(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			logger.info("URL "
					+ UrlMetadatas.getBlockUser(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));

			if (response.getStatus() != 200) {
				logger.info("Response 1 :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				logger.info("Response 2 :: " + strResponse);
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
	public AddMerchantResponse addMerchant(MRegistrationRequest request) {
		AddMerchantResponse response = new AddMerchantResponse();
		try {
			JSONObject payload = new JSONObject();
			logger.info("Add Merchant...");
			payload.put("sessionId", request.getSessionId());
			payload.put("contactNo", request.getContactNo());
			payload.put("firstName", request.getFirstName());
			payload.put("lastName", " ");
			payload.put("email", request.getEmail());
			payload.put("ipAddress",request.getIpAddress());
			payload.put("successURL",request.getSuccessURL());
			payload.put("failureURL",request.getFailureURL());
			payload.put("serviceName","Merchant Payment");
            payload.put("image",request.getImage());
			payload.put("fixed",request.isFixed());
			payload.put("paymentGateway", request.isPaymentGateway());
			payload.put("store", request.isStore());
			payload.put("minAmount",request.getMinAmount());
			payload.put("maxAmount",request.getMaxAmount());
			payload.put("value",request.getValue());
			logger.info("Payload for Add Merchant ::" + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.addMerchant(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			ClientResponse resp = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);
			if (resp.getStatus() != 200) {
				logger.info("Response 1 :: " + response.getStatus());
				response.setSuccess(false);
				response.setCode("F00");
				response.setMessage("Service unavailable");
				response.setStatus("FAILED");
				response.setDetails(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = resp.getEntity(String.class);
				logger.info("Response 2 :: " + strResponse);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");

						if (code.equalsIgnoreCase("S00")) {
							response.setSuccess(true);
							System.err.println("Success is true");
							final String details = (String) jobj.get("details");
			                response.setDetails(details);
                        } else {
							response.setSuccess(false);
						}
						response.setCode(code);
						response.setStatus(status);
						response.setMessage(message);


					} else {
						response.setSuccess(false);
						response.setCode("F00");
						response.setMessage("Service unavailable");
						response.setStatus("FAILED");
						response.setDetails(APIUtils.getFailedJSON().toString());
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setCode("F00");
			response.setMessage("Service unavailable");
			response.setStatus("FAILED");
			response.setDetails(APIUtils.getFailedJSON().toString());
		}

		return response;
	}

	@Override
	public AllTransactionResponse getSingleUser(UserTransactionRequest request) {

		AllTransactionResponse resp = new AllTransactionResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("page", 0);
			payload.put("size", 100000);
			payload.put("userStatus", "");
			Client client = Client.create();
			WebResource webResource = client.resource(UrlMetadatas.getSingle(Version.VERSION_1, Role.ADMIN,
					Device.WEBSITE, Language.ENGLISH, request.getUsername()));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				logger.info("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				logger.info("Response : " + response.getStatus());
				String strResponse = response.getEntity(String.class);
				if (strResponse != null) {
					logger.info("Response Data " + strResponse.toString());
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						final org.json.JSONArray totalTtransaction = (org.json.JSONArray) jobj.getJSONObject("details")
								.getJSONArray("content");

						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setResponse(strResponse);
						resp.setJsonArray(totalTtransaction);
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
	public List<NEFTResponse> getNEFTList(SessionDTO dto,boolean flag,Date date1,Date date2) {
		List<NEFTResponse> neftResponses = new ArrayList<>();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", dto.getSessionId());
			Client client = Client.create();
			WebResource webResource = client.resource(UrlMetadatas.getNeftListURL(Version.VERSION_1, Role.ADMIN,
					Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			System.out.println("this is the response"+response);
			if (response.getStatus() != 200) {
				logger.info("Response :: " + response.getStatus());
			} else {
				logger.info("Response : " + response.getStatus());
				String strResponse = response.getEntity(String.class);
				if (strResponse != null) {
					logger.info("Response Data " + strResponse.toString());
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");

						if (code.equalsIgnoreCase("S00")) {
							final org.json.JSONArray list = jobj.getJSONArray("details");
							if(flag==false){
							for(int i=0 ; i < list.length() ; i++){
								NEFTResponse neft = new NEFTResponse();
								org.json.JSONObject json = list.getJSONObject(i);
								neft.setName(JSONParserUtil.getString(json,"name"));
								neft.setMobileNo(JSONParserUtil.getString(json,"mobileNumber"));
								neft.setEmail(JSONParserUtil.getString(json,"email"));
								neft.setTransactionDate(JSONParserUtil.getString(json,"transactionDate"));
								neft.setTransactionID(JSONParserUtil.getString(json,"transactionID"));
								neft.setBankName(JSONParserUtil.getString(json,"bankName"));
								neft.setIfscCode(JSONParserUtil.getString(json,"ifscCode"));
								neft.setAccountNumber(JSONParserUtil.getString(json,"beneficiaryAccountNumber"));
								neft.setAccountName(JSONParserUtil.getString(json,"beneficiaryAccountName"));
								neft.setUserAccount(JSONParserUtil.getString(json,"virtualAccount"));
								neft.setBankAccount(JSONParserUtil.getString(json,"bankVirtualAccount"));
								neft.setAmount(JSONParserUtil.getString(json,"amount"));
								neft.setStatus(JSONParserUtil.getString(json,"status"));
								neftResponses.add(neft);
							}
						}
							else
							{
								
								for(int i=0 ; i < list.length() ; i++){
									
									org.json.JSONObject json = list.getJSONObject(i);
									String d=JSONParserUtil.getString(json,"transactionDate");
									Date d3=sdf.parse(d);
									Calendar calender=Calendar.getInstance();
									calender.setTime(d3);
									long m=calender.getTimeInMillis();
									calender.setTimeInMillis(m);
									Date txdate=calender.getTime();
									if (txdate.after(date1) && txdate.before(date2)){
									NEFTResponse neft = new NEFTResponse();
									neft.setName(JSONParserUtil.getString(json,"name"));
									neft.setMobileNo(JSONParserUtil.getString(json,"mobileNumber"));
									neft.setEmail(JSONParserUtil.getString(json,"email"));
									neft.setTransactionDate(JSONParserUtil.getString(json,"transactionDate"));
									neft.setTransactionID(JSONParserUtil.getString(json,"transactionID"));
									neft.setBankName(JSONParserUtil.getString(json,"bankName"));
									neft.setIfscCode(JSONParserUtil.getString(json,"ifscCode"));
									neft.setAccountNumber(JSONParserUtil.getString(json,"beneficiaryAccountNumber"));
									neft.setAccountName(JSONParserUtil.getString(json,"beneficiaryAccountName"));
									neft.setUserAccount(JSONParserUtil.getString(json,"virtualAccount"));
									neft.setBankAccount(JSONParserUtil.getString(json,"bankVirtualAccount"));
									neft.setAmount(JSONParserUtil.getString(json,"amount"));
									neft.setStatus(JSONParserUtil.getString(json,"status"));
									neftResponses.add(neft);
								}
							}
					}
						}
					}
					}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return neftResponses;


	}

	@Override
	public List<NEFTResponse> getNEFTList(SessionDTO dto)
{
		List<NEFTResponse> neftResponses = new ArrayList<>();
		JSONObject payload = new JSONObject();
		try {
			payload.put("sessionId", dto.getSessionId());
		
		Client client = Client.create();
		WebResource webResource = client.resource(UrlMetadatas.getNeftListMerchantURL(Version.VERSION_1, Role.ADMIN,
				Device.WEBSITE, Language.ENGLISH));

		ClientResponse response = webResource.accept("application/json").type("application/json")
				.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

		if (response.getStatus() != 200) {
			logger.info("Response :: " + response.getStatus());
		} else {
			logger.info("Response : " + response.getStatus());
			String strResponse = response.getEntity(String.class);
			if (strResponse != null) {
				logger.info("Response Data " + strResponse.toString());
				org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
				if (jobj != null) {
					final String status = (String) jobj.get("status");
					final String code = (String) jobj.get("code");
					final String message = (String) jobj.get("message");

					if (code.equalsIgnoreCase("S00")) {
						final org.json.JSONArray list = jobj.getJSONArray("details");
						for(int i=0 ; i < list.length() ; i++){
							NEFTResponse neft = new NEFTResponse();
							org.json.JSONObject json = list.getJSONObject(i);
							neft.setName(JSONParserUtil.getString(json,"name"));
							neft.setMobileNo(JSONParserUtil.getString(json,"mobileNumber"));
							neft.setEmail(JSONParserUtil.getString(json,"email"));
							neft.setTransactionDate(JSONParserUtil.getString(json,"transactionDate"));
							neft.setTransactionID(JSONParserUtil.getString(json,"transactionID"));
							neft.setBankName(JSONParserUtil.getString(json,"bankName"));
							neft.setIfscCode(JSONParserUtil.getString(json,"ifscCode"));
							neft.setAccountNumber(JSONParserUtil.getString(json,"beneficiaryAccountNumber"));
							neft.setAccountName(JSONParserUtil.getString(json,"beneficiaryAccountName"));
							neft.setUserAccount(JSONParserUtil.getString(json,"virtualAccount"));
							neft.setBankAccount(JSONParserUtil.getString(json,"bankVirtualAccount"));
							neft.setAmount(JSONParserUtil.getString(json,"amount"));
							neft.setStatus(JSONParserUtil.getString(json,"status"));
							neftResponses.add(neft);
						}
						}
					}
			
		}
		}
		}catch (org.codehaus.jettison.json.JSONException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return neftResponses;
		
	}

	@Override
	public ReceiptsResponse getSingleMerchantTransactionList(ReceiptsRequest request) {
		ReceiptsResponse resp=new ReceiptsResponse();
		
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("page", request.getPage());
			payload.put("size", request.getSize());
			//payload.put("username",request.getUsername());
			Client client = Client.create();
			System.out.println("this is the encoded email"+URLEncoder.encode(request.getUsername()));
			WebResource webResource = client.resource(UrlMetadatas.getMerchantsTransactions(Version.VERSION_1, Role.ADMIN,
					Device.WEBSITE, Language.ENGLISH,request.getUsername()));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				logger.info("Response :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				logger.info("Response : " + response.getStatus());
				String strResponse = response.getEntity(String.class);
				if (strResponse != null) {
					logger.info("Response Data " + strResponse.toString());
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						final org.json.JSONArray totalTtransaction = (org.json.JSONArray) jobj.getJSONObject("details")
								.getJSONArray("content");

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

//public static void main(String[] args) {
//	System.out.println(URLEncoder.encode("wel.abhijit@gmail.com"));
//	
//}
	
	}


