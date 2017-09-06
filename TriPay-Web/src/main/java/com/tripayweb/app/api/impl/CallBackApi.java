package com.tripayweb.app.api.impl;

import com.tripayweb.app.model.response.IPayCallBack;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.api.ICallBackApi;
import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Language;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.Version;
import com.tripayweb.app.model.request.CallBackRequest;
import com.tripayweb.app.model.response.CallBackResponse;
import com.tripayweb.app.utils.SecurityUtils;
import com.tripayweb.util.APIUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CallBackApi implements ICallBackApi {

	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public CallBackResponse attempt(IPayCallBack request) {
		
		CallBackResponse resp = new CallBackResponse();
		try {
			
			JSONObject payload = new JSONObject();
			payload.put("ipay_id", request.getIpay_id());
			payload.put("agent_id", request.getAgent_id());
			payload.put("opr_id", request.getOpr_id());
			payload.put("status", request.getStatus());
			payload.put("res_code", request.getRes_code());
			payload.put("res_msg", request.getRes_msg());
			
			System.err.println("Payload :: "+payload);
			logger.info("PAYLOAD :: " + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.getCallBackUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
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

}
