package com.tripayweb.api.impl;

import com.tripayweb.app.model.response.UserDetailsResponse;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Language;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.Version;
import com.tripayweb.app.utils.SecurityUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class AuthenticationApi implements IAuthenticationApi {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public String getAuthorityFromSession(String session , Role role) {
		
		

		try {
			String status = null;
			String code = null;
			String message = null;
			JSONObject details = null;

			Client client = Client.create();
			JSONObject payload = new JSONObject();
			payload.put("sessionId", session);
			logger.info("map String::" + payload.toString());

			WebResource webResource = client
					.resource(UrlMetadatas.getAuthority(Version.VERSION_1, role, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			
			/*ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", APIConstants.URL.DUMMY_HASH).post(ClientResponse.class, payload);*/

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				JSONObject jobj = new JSONObject(output);
				if (jobj != null) {
					status = (String) jobj.get("status");
					code = (String) jobj.get("code");
					message = (String) jobj.get("message");
					details = (JSONObject) jobj.get("details");

					logger.info("Details " + details);
					if (details != null) {
						String authority = details.getString("authority");
						
						return authority;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserDetailsResponse getUserDetailsFromSession(String session) {
		UserDetailsResponse resp = new UserDetailsResponse();
		try {
			JSONObject details = null;
			JSONObject user = null;
			Client client = Client.create();
			JSONObject payload = new JSONObject();
			payload.put("sessionId", session);
			logger.info("map String::" + payload.toString());

			WebResource webResource = client
					.resource(UrlMetadatas.getAuthority(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				JSONObject jobj = new JSONObject(output);
				if (jobj != null) {
					resp.setSuccess(true);
					details = (JSONObject) jobj.get("details");
					if(details != null){
						user = (JSONObject) details.get("user");
						if(user != null){
							resp.setAuthority(user.getString("authority"));
							resp.setEmail(user.getString("email"));
							resp.setContactNo(user.getString("contactNo"));
							resp.setFirstName(user.getString("firstName"));
							resp.setLastName(user.getString("lastName"));
							resp.setImage(user.getString("image"));
							return resp;
						}
					}



				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
