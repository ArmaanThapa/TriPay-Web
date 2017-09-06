package com.tripayweb.app.api.impl;

import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.api.ITopupApi;
import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Language;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.Version;
import com.tripayweb.app.model.request.BrowsePlansRequest;
import com.tripayweb.app.model.request.DataCardTopupRequest;
import com.tripayweb.app.model.request.GetOperatorAndCircleForMobRequest;
import com.tripayweb.app.model.request.GetOperatorAndCircleRequest;
import com.tripayweb.app.model.request.PostpaidTopupRequest;
import com.tripayweb.app.model.request.PrepaidTopupRequest;
import com.tripayweb.app.model.response.BrowsePlansResponse;
import com.tripayweb.app.model.response.DataCardTopupResponse;
import com.tripayweb.app.model.response.GetOperatorAndCircleForMobResponse;
import com.tripayweb.app.model.response.GetOperatorAndCircleResponse;
import com.tripayweb.app.model.response.PostpaidTopupResponse;
import com.tripayweb.app.model.response.PrepaidTopupResponse;
import com.tripayweb.app.utils.SecurityUtils;
import com.tripayweb.util.APIUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TopupApi implements ITopupApi {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	private Object nullObject = null;
	
	@Override
	public PrepaidTopupResponse prePaid(PrepaidTopupRequest request) {
		PrepaidTopupResponse resp = new PrepaidTopupResponse();
		
		try {
			
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("topupType", request.getTopupType());
			payload.put("serviceProvider", request.getServiceProvider());
			payload.put("mobileNo", request.getMobileNo());
			payload.put("area", request.getArea());
			payload.put("amount", request.getAmount());
			logger.info("PAYLOAD :: " + payload.toString());
			System.err.println(request.getSessionId());
			System.err.println(request.getTopupType());
			System.err.println(request.getServiceProvider());
			System.err.println(request.getMobileNo());
			System.err.println(request.getArea());
			System.err.println(request.getAmount());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getPrePaidTopupUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);
				String strResponse = response.getEntity(String.class);
				
			if (response.getStatus() != 200) {
				logger.info("RESPONSE CODE :: " + response.getStatus());
				logger.info("Response is ::"+strResponse);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
				
			} else {
				
				logger.info("RESPONSE :: " + strResponse);
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
	public PostpaidTopupResponse postPaid(PostpaidTopupRequest request) {

		PostpaidTopupResponse resp = new PostpaidTopupResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("topupType", request.getTopupType());
			payload.put("serviceProvider", request.getServiceProvider());
			payload.put("mobileNo", request.getMobileNo());
			payload.put("area", request.getArea());
			payload.put("amount", request.getAmount());
			logger.info("PAYLOAD :: " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getPostPaidTopupUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

				String strResponse = response.getEntity(String.class);
			if (response.getStatus() != 200) {
				System.out.println("String response ::"+strResponse);
				logger.info("RESPONSE CODE :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				logger.info("RESPONSE :: " + strResponse);
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
	public DataCardTopupResponse dataCard(DataCardTopupRequest request) {
		DataCardTopupResponse resp = new DataCardTopupResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("topupType", request.getTopupType());
			payload.put("serviceProvider", request.getServiceProvider());
			payload.put("mobileNo", request.getMobileNo());
			payload.put("area", request.getArea());
			payload.put("amount", request.getAmount());
			logger.info("PAYLOAD :: " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getDataCardTopupUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				logger.info("RESPONSE CODE :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				logger.info("RESPONSE :: " + strResponse);
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
	public GetOperatorAndCircleResponse operatorAndcircle(GetOperatorAndCircleRequest request) {
		GetOperatorAndCircleResponse resp = new GetOperatorAndCircleResponse();

		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			logger.info("PAYLOAD :: " + payload.toString());

			Client client = Client.create();

			WebResource webResource = client.resource(UrlMetadatas.getOperatorAndCircleUrl(Version.VERSION_1, Role.USER,
					Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				logger.info("RESPONSE CODE :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				logger.info("RESPONSE " + strResponse);
				if (strResponse != null) {
					logger.info("Response " + strResponse.toString());
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
	public GetOperatorAndCircleForMobResponse operatorAndcircleForMob(GetOperatorAndCircleForMobRequest request) {
		GetOperatorAndCircleForMobResponse resp = new GetOperatorAndCircleForMobResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", (request.getSessionId()==null)?"":request.getSessionId());
			payload.put("mobileNumber", request.getMobileNumber());
			logger.info("PAYLOAD :: " + payload.toString());
			logger.info("inside calling of payload");
			Client client = Client.create();
			WebResource webResource = client.resource(UrlMetadatas.getOperatorAndCircleForMobUrl(Version.VERSION_1,
					Role.USER, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				logger.info("CODE :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				logger.info("RESPONSE :: " + strResponse);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						// final String status = (String) jobj.get("status");
						// final String code = (String) jobj.get("code");
						// final String message = (String) jobj.get("message");
						// if (code.equalsIgnoreCase("S00")) {
						resp.setSuccess(true);
						// } else {
						// resp.setSuccess(false);
						// }
						resp.setCode("S00");
						resp.setStatus("SUCCESS");
						resp.setMessage("Getting operator and circle for mobile");
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
	public BrowsePlansResponse getPlansForMobile(BrowsePlansRequest request) {
		BrowsePlansResponse resp = new BrowsePlansResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", (request.getSessionId()==null)?"":request.getSessionId());
			payload.put("circleCode", request.getCircleCode());
			payload.put("operatorCode",request.getOperatorCode());
			logger.info("PAYLOAD :: " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(UrlMetadatas.getPlans(Version.VERSION_1,
					Role.USER, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				logger.info("CODE :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				logger.info("RESPONSE :: " + strResponse);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						// final String status = (String) jobj.get("status");
						// final String code = (String) jobj.get("code");
						// final String message = (String) jobj.get("message");
						// if (code.equalsIgnoreCase("S00")) {
						resp.setSuccess(true);
						// } else {
						// resp.setSuccess(false);
						// }
						resp.setCode("S00");
						resp.setStatus("SUCCESS");
						resp.setMessage("Getting Plans for mobile");
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
