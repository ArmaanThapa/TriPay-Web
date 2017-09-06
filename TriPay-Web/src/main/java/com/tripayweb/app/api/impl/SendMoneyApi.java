package com.tripayweb.app.api.impl;

import com.tripayweb.app.model.request.SendMoneyBankRequest;
import com.tripayweb.app.model.response.SendMoneyBankResponse;
import com.tripayweb.util.JSONParserUtil;
import com.tripayweb.util.SecurityUtil;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.api.ISendMoneyApi;
import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Language;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.Version;
import com.tripayweb.app.model.request.ListStoreApiRequest;
import com.tripayweb.app.model.request.OfflinePaymentRequest;
import com.tripayweb.app.model.request.PayAtStoreRequest;
import com.tripayweb.app.model.request.SendMoneyMobileRequest;
import com.tripayweb.app.model.response.ListStoreApiResponse;
import com.tripayweb.app.model.response.OfflinePaymentResponse;
import com.tripayweb.app.model.response.PayAtStoreResponse;
import com.tripayweb.app.model.response.SendMoneyMobileResponse;
import com.tripayweb.app.utils.SecurityUtils;
import com.tripayweb.util.APIUtils;
import com.tripayweb.util.LogCat;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class SendMoneyApi implements ISendMoneyApi {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public PayAtStoreResponse payAtStoreResponseRequest(PayAtStoreRequest request) {

		PayAtStoreResponse resp = new PayAtStoreResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("netAmount",request.getNetAmount());
			payload.put("id",request.getId());
			LogCat.print("PAYLOAD :: " + payload.toString());
			
			Client client = Client.create();
			WebResource webResource = client
					.resource(UrlMetadatas.getPayAtStoreUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				LogCat.print("RESPONSE CODE :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("RESPONSE :: " + strResponse);
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
			resp.setCode("F00");
			resp.setMessage("Service unavailable");
			resp.setStatus("FAILED");
			resp.setResponse(APIUtils.getFailedJSON().toString());
		}
		return resp;

	}

	@Override
	public SendMoneyMobileResponse sendMoneyMobileRequest(SendMoneyMobileRequest request) {
		SendMoneyMobileResponse resp = new SendMoneyMobileResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("mobileNumber", request.getMobileNumber());
			payload.put("amount", request.getAmount());
			payload.put("message",request.getMessage());
			LogCat.print("PAYLOAD :: " + payload.toString());
			
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getSendMoneyMobileUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);
				String strResponse = response.getEntity(String.class);

			if (response.getStatus() != 200) {
				LogCat.print("RESPONSE CODE :: " + response.getStatus());
				System.err.print("send money error ::"+strResponse);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				LogCat.print("RESPONSE :: " + strResponse);
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
	public ListStoreApiResponse listStoreResponseRequest(ListStoreApiRequest request) {
		ListStoreApiResponse resp = new ListStoreApiResponse();

		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			LogCat.print("PAYLOAD :: " + payload.toString());
			
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getListStoreUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				LogCat.print("RESPONSE CODE :: " + response.getStatus());
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				String strResponse = response.getEntity(String.class);
				LogCat.print("RESPONSE :: " + strResponse);
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
	public SendMoneyBankResponse sendMoneyBankRequest(SendMoneyBankRequest request) {
		SendMoneyBankResponse response  = new SendMoneyBankResponse();

        try{
            Client  client = Client.create();
            WebResource webResource = client.resource(UrlMetadatas.initiateBankTransfer(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
            ClientResponse clientResponse = webResource.accept("application/json").header("hash", SecurityUtils.getHash(request.toString())).type("application/json").post(ClientResponse.class,request.toJSON());
            if(clientResponse.getStatus() != 200){
                response.setCode("F00");
                response.setSuccess(false);
                response.setStatus("FAILED");
                response.setMessage("Response Status from server is ::"+clientResponse.getStatus());
            }else {
				String strResponse = clientResponse.getEntity(String.class);
				org.json.JSONObject json = new org.json.JSONObject(strResponse);
				final String code = JSONParserUtil.getString(json,"code");
				final String message  =  JSONParserUtil.getString(json,"message");
                response.setCode(code);
                response.setMessage(message);
				if(code.equalsIgnoreCase("S00")){
				    response.setSuccess(true);
				}else {
                    response.setSuccess(false);
                }

			}
		}catch(Exception e){
            e.printStackTrace();
            response.setSuccess(false);
            response.setCode("F00");
            response.setMessage("Service Unavailable");
            response.setStatus("FAILED");
            response.setResponse(APIUtils.getFailedJSON().toString());
        }
        return response;
	}
	
	
	@Override
	public OfflinePaymentResponse offlinePayment(OfflinePaymentRequest request) {
		OfflinePaymentResponse resp  = new OfflinePaymentResponse();

		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("mobileNumber", request.getMobileNumber());
			payload.put("amount", request.getAmount());
			LogCat.print("PAYLOAD :: " + payload.toString());
			
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.offlinePaymentUrl(Version.VERSION_1, Role.MERCHANT, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);
				String strResponse = response.getEntity(String.class);

			if (response.getStatus() != 200) {
				LogCat.print("RESPONSE CODE :: " + response.getStatus());
				System.err.print("Offline Payment error ::"+strResponse);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				LogCat.print("RESPONSE :: " + strResponse);
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
	public OfflinePaymentResponse verifyOTP(OfflinePaymentRequest request) {
		OfflinePaymentResponse resp  = new OfflinePaymentResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("otp", request.getOtp());
			payload.put("amount",request.getAmount());
			payload.put("mobileNumber",request.getMobileNumber());
			LogCat.print("PAYLOAD :: " + payload.toString());
			
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.offlinePaymentOTP(Version.VERSION_1, Role.MERCHANT, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);
				String strResponse = response.getEntity(String.class);

			if (response.getStatus() != 200) {
				LogCat.print("RESPONSE CODE :: " + response.getStatus());
				System.err.print("Offline Payment error ::"+strResponse);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				LogCat.print("RESPONSE :: " + strResponse);
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
	public SendMoneyBankResponse sendMoneyToMBankRequest(SendMoneyBankRequest request) {
		SendMoneyBankResponse response  = new SendMoneyBankResponse();

        try{
            Client  client = Client.create();
            System.out.println("Payload : " +request.toJSON());
            WebResource webResource = client.resource(UrlMetadatas.initiateMBankTransfer(Version.VERSION_1, Role.MERCHANT, Device.WEBSITE, Language.ENGLISH));
            ClientResponse clientResponse = webResource.accept("application/json").header("hash", SecurityUtils.getHash(request.toString())).type("application/json").post(ClientResponse.class,request.toJSON());
            if(clientResponse.getStatus() != 200){
                response.setCode("F00");
                response.setSuccess(false);
                response.setStatus("FAILED");
                response.setMessage("Response Status from server is ::"+clientResponse.getStatus());
            }else {
				String strResponse = clientResponse.getEntity(String.class);
				org.json.JSONObject json = new org.json.JSONObject(strResponse);
				final String code = JSONParserUtil.getString(json,"code");
				final String message  =  JSONParserUtil.getString(json,"message");
                response.setCode(code);
                response.setMessage(message);
				if(code.equalsIgnoreCase("S00")){
				    response.setSuccess(true);
				}else {
                    response.setSuccess(false);
                }

			}
		}catch(Exception e){
            e.printStackTrace();
            response.setSuccess(false);
            response.setCode("F00");
            response.setMessage("Service Unavailable");
            response.setStatus("FAILED");
            response.setResponse(APIUtils.getFailedJSON().toString());
        }
        return response;
	}
	
	
}
