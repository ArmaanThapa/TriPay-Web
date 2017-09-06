package com.tripayweb.api.impl;

import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.api.ITelcoApi;
import com.tripayweb.model.app.request.TelcoRequest;
import com.tripayweb.model.app.response.TelcoReponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TelcoApi implements ITelcoApi {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final String BASE_URL="http://172.16.3.10/"; // DON'T CHANGE IT
	public static final String TELCO_URL = BASE_URL+"Api/v1/User/Windows/en/Plans/GetTelco";

	@Override
	public TelcoReponse request(TelcoRequest request) {
		TelcoReponse resp = new TelcoReponse();
		try {

			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("number", request.getNumber());

			logger.info("map String::" + payload.toString());

			Client client = Client.create();
			WebResource webResource = client.resource(TELCO_URL);
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", "TEST").post(ClientResponse.class, payload);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			} else {
				String strResponse = response.getEntity(String.class);
				org.json.JSONObject jobj = new org.json.JSONObject(strResponse);

				if (jobj != null) {
					System.err.println("OUTPUT " + jobj.toString());

					final String code = (String) jobj.getJSONObject("operator").get("code");
					final String name = (String) jobj.getJSONObject("operator").get("name");
					final String circleCcode = (String) jobj.getJSONObject("circle").get("code");
					final String circleNname = (String) jobj.getJSONObject("circle").get("name");
					final String serviceCode = (String) jobj.getJSONObject("operator").get("serviceCode");

					resp.setCode(code);
					resp.setName(name);
					resp.setCircleCode(circleCcode);
					resp.setCircleName(circleNname);
					resp.setServiceCode(serviceCode);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setServiceCode("F00");
			resp.setName("Invaild");
		}
		return resp;
	}

}
