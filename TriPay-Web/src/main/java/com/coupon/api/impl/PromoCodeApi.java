package com.coupon.api.impl;

import com.tripayweb.util.JSONParserUtil;
import org.codehaus.jettison.json.JSONObject;
import org.json.JSONArray;

import com.coupon.api.IPromoCodeApi;
import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Language;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.Version;
import com.tripayweb.app.model.request.PromoCodeRequest;
import com.tripayweb.app.model.request.SessionDTO;
import com.tripayweb.app.model.response.PromoCodeResponse;
import com.tripayweb.app.utils.SecurityUtils;
import com.tripayweb.util.APIUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class PromoCodeApi implements IPromoCodeApi{

	@Override
	public PromoCodeResponse generateRequest(PromoCodeRequest request) {
		System.err.print("inside gen");
        PromoCodeResponse resp = new PromoCodeResponse();
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.savePromoCode(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			
			System.err.println("PAYLOAD :: "+request.toJSON().toString());

			System.err.println("URL :: "+UrlMetadatas.savePromoCode(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(request.toJSON().toString())).post(ClientResponse.class, request.toJSON());
			String strResponse = response.getEntity(String.class);
            System.err.println("Response :: "+strResponse);
            if (response.getStatus() != 200) {
				System.err.println("Response :: "+strResponse);
				System.err.println("res :: "+response);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				System.err.println("Response 11:: "+response.getStatus());
//				String strResponse = response.getEntity(String.class);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
							resp.setMessage(message);
						} else {
							resp.setSuccess(false);
							if(code.equalsIgnoreCase("F04")){
								org.json.JSONObject jsonObject = jobj.getJSONObject("details");
								boolean valid = jsonObject.getBoolean("valid");
								if(!valid){
									resp.setPromoCode(jsonObject.getString("promoCode"));
									resp.setStartDate(jsonObject.getString("startDate"));
									resp.setTerms(jsonObject.getString("terms"));
									resp.setEndDate(jsonObject.getString("endDate"));
									resp.setServiceType(jsonObject.getString("serviceType"));
								}

							}
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
	public PromoCodeResponse listPromo(SessionDTO request) {
		 	
		PromoCodeResponse resp = new PromoCodeResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.listPromoCode(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			
			System.err.println("PAYLOAD :: "+payload.toString());

			System.err.println("URL :: "+UrlMetadatas.savePromoCode(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);
			String strResponse = response.getEntity(String.class);
			if (response.getStatus() != 200) {
				
				System.err.println("Response :: "+strResponse);
				System.err.println("res :: "+response);
				resp.setSuccess(false);
				resp.setCode("F00");
				resp.setMessage("Service unavailable");
				resp.setStatus("FAILED");
				resp.setResponse(APIUtils.getFailedJSON().toString());
			} else {
				System.err.println("Response 11:: "+response.getStatus());
//				String strResponse = response.getEntity(String.class);
				if (strResponse != null) {
					org.json.JSONObject jobj = new org.json.JSONObject(strResponse);
					if (jobj != null) {
						final String status = (String) jobj.get("status");
						final String code = (String) jobj.get("code");
						final String message = (String) jobj.get("message");
						final JSONArray arr =  jobj.getJSONArray("details");
						
						if (code.equalsIgnoreCase("S00")) {
							resp.setSuccess(true);
						} else {
							resp.setSuccess(false);
						}
						resp.setCode(code);
						resp.setStatus(status);
						resp.setMessage(message);
						resp.setResponse(strResponse);
						resp.setDetails(arr);
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
