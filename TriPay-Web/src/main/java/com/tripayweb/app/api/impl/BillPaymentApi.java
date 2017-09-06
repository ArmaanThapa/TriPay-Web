package com.tripayweb.app.api.impl;

import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.api.IBillPaymentApi;
import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Language;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.Version;
import com.tripayweb.app.model.request.DTHBillPaymentRequest;
import com.tripayweb.app.model.request.ElectricityBillPaymentRequest;
import com.tripayweb.app.model.request.GasBillPaymentRequest;
import com.tripayweb.app.model.request.InsuranceBillPaymentRequest;
import com.tripayweb.app.model.request.LandlineBillPaymentRequest;
import com.tripayweb.app.model.response.DTHBillPaymentResponse;
import com.tripayweb.app.model.response.ElectricityBillPaymentResponse;
import com.tripayweb.app.model.response.GasBillPaymentResponse;
import com.tripayweb.app.model.response.InsuranceBillPaymentResponse;
import com.tripayweb.app.model.response.LandlineBillPaymentResponse;
import com.tripayweb.app.utils.SecurityUtils;
import com.tripayweb.util.APIUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class BillPaymentApi implements IBillPaymentApi {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public ElectricityBillPaymentResponse electricBill(ElectricityBillPaymentRequest request) {
		ElectricityBillPaymentResponse resp = new ElectricityBillPaymentResponse();

		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("serviceProvider", request.getServiceProvider());
			payload.put("accountNumber", request.getAccountNumber());
			payload.put("cycleNumber", request.getCycleNumber());
			payload.put("amount", request.getAmount());
			logger.info("PAYLOAD :: " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getElectricityBillUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
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
	public GasBillPaymentResponse gasBill(GasBillPaymentRequest request) {
		GasBillPaymentResponse resp = new GasBillPaymentResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("serviceProvider", request.getServiceProvider());
			payload.put("accountNumber", request.getAccountNumber());
			payload.put("amount", request.getAmount());
			logger.info("PAYLOAD :: " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getGasBillUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
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
	public InsuranceBillPaymentResponse insuranceBill(InsuranceBillPaymentRequest request) {
		InsuranceBillPaymentResponse resp = new InsuranceBillPaymentResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("serviceProvider", request.getServiceProvider());
			payload.put("policyNumber", request.getPolicyNumber());
			payload.put("policyDate", request.getPolicyDate());
			payload.put("amount", request.getAmount());
			logger.info("PAYLOAD :: " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getInsuranceBillUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
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
	public LandlineBillPaymentResponse landline(LandlineBillPaymentRequest request) {
		LandlineBillPaymentResponse resp = new LandlineBillPaymentResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("serviceProvider", request.getServiceProvider());
			payload.put("stdCode", request.getStdCode());
			payload.put("landlineNumber", request.getLandlineNumber());
			payload.put("accountNumber", request.getAccountNumber());
			payload.put("amount", request.getAmount());
			logger.info("PAYLOAD :: " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getLandlineBillUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
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
	public DTHBillPaymentResponse dthBill(DTHBillPaymentRequest request) {
		DTHBillPaymentResponse resp = new DTHBillPaymentResponse();

		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("serviceProvider", request.getServiceProvider());
			payload.put("dthNo", request.getDthNo());
			payload.put("amount", request.getAmount());
			logger.info("PAYLOAD :: " + payload.toString());
			
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getDthBillUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
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

}
