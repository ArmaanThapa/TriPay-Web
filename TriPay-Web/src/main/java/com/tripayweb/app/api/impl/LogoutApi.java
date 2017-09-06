package com.tripayweb.app.api.impl;

import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.api.ILogoutApi;
import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Language;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.Version;
import com.tripayweb.app.model.request.LogoutRequest;
import com.tripayweb.app.model.response.LogoutResponse;
import com.tripayweb.app.utils.SecurityUtils;
import com.tripayweb.util.APIUtils;
import com.tripayweb.util.LogCat;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class LogoutApi implements ILogoutApi {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public LogoutResponse logout(LogoutRequest request, Role role) {
		LogoutResponse resp = new LogoutResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());

			Client client = Client.create();
			WebResource webResource = client
					.resource(UrlMetadatas.getLogoutUrl(Version.VERSION_1, role, Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				LogCat.print("Response Code :: " + response.getStatus());
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
						LogCat.print("Response :: "+resp.getCode());
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
