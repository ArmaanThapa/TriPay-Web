package com.tripayweb.app.api.impl;

import com.tripayweb.app.model.request.SessionDTO;
import com.tripayweb.app.model.request.Utility;
import com.tripayweb.app.model.request.VersionRequest;
import com.tripayweb.app.model.response.VersionResponse;
import com.tripayweb.util.APIUtils;
import com.tripayweb.util.JSONParserUtil;
import org.codehaus.jettison.json.JSONObject;

import com.tripayweb.app.api.IVersionApi;
import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Language;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.Version;
import com.tripayweb.app.utils.SecurityUtils;
import com.tripayweb.model.app.request.CheckVersionRequest;
import com.tripayweb.model.app.response.CheckVersionResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VersionApi implements IVersionApi {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public VersionResponse validateVersion(Utility dto) {
		VersionResponse response = new VersionResponse();
        try {


            Client client = Client.create();
            WebResource webResource = client.resource(
                    UrlMetadatas.validateVersion(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));
            ClientResponse clientResponse = webResource.accept("application/json").type("application/json")
                    .header("hash", SecurityUtils.getHash(dto.toString())).post(ClientResponse.class, dto.toJSON() );
            String strResponse = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                org.json.JSONObject jsonObject = new org.json.JSONObject(strResponse);
                response.setStatus(JSONParserUtil.getString(jsonObject,"status"));
                response.setCode(JSONParserUtil.getString(jsonObject,"code"));
                response.setMessage(JSONParserUtil.getString(jsonObject,"message"));
                response.setDetails(APIUtils.getFailedJSON().toString());
            } else {
                logger.info("RESPONSE CODE :: " + response.getStatus());
                System.err.print(strResponse);
                response.setCode("F00");
                response.setMessage("Service unavailable");
                response.setStatus("FAILED");
                response.setDetails(APIUtils.getFailedJSON().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode("F00");
            response.setMessage("Service unavailable");
            response.setStatus("FAILED");
            response.setDetails(APIUtils.getFailedJSON().toString());
        }
		return response;
	}

	@Override
	public VersionResponse updateVersion(VersionRequest dto) {
		VersionResponse response = new VersionResponse();
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(
					UrlMetadatas.updateVersion(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			ClientResponse clientResponse = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(dto.toString())).post(ClientResponse.class, dto.toJSON());
			String strResponse = clientResponse.getEntity(String.class);
			if (clientResponse.getStatus() == 200) {
				org.json.JSONObject jsonObject = new org.json.JSONObject(strResponse);
				response.setStatus(JSONParserUtil.getString(jsonObject, "status"));
				response.setCode(JSONParserUtil.getString(jsonObject, "code"));
				response.setMessage(JSONParserUtil.getString(jsonObject, "message"));
				response.setDetails(APIUtils.getFailedJSON().toString());
			} else {
				logger.info("RESPONSE CODE :: " + response.getStatus());
				System.err.print(strResponse);
				response.setCode("F00");
				response.setMessage("Service unavailable");
				response.setStatus("FAILED");
				response.setDetails(APIUtils.getFailedJSON().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("F00");
			response.setMessage("Service unavailable");
			response.setStatus("FAILED");
			response.setDetails(APIUtils.getFailedJSON().toString());
		}

		return response;
	}

	@Override
	public VersionResponse listVersion(SessionDTO dto) {
		VersionResponse response = new VersionResponse();
		try {
			Client client = Client.create();
			JSONObject obj = new JSONObject();
			obj.put("sessionId", dto.getSessionId());

			WebResource webResource = client.resource(
					UrlMetadatas.listAllVersions(Version.VERSION_1, Role.ADMIN, Device.WEBSITE, Language.ENGLISH));
			ClientResponse clientResponse = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(dto.toString())).post(ClientResponse.class, obj);
			String strResponse = clientResponse.getEntity(String.class);
			if (clientResponse.getStatus() == 200) {

				System.out.println("Response :: " + clientResponse.getStatus());
				org.json.JSONObject jsonObject = new org.json.JSONObject(strResponse);
				response.setStatus(JSONParserUtil.getString(jsonObject, "status"));
				response.setCode(JSONParserUtil.getString(jsonObject, "code"));
				response.setMessage(JSONParserUtil.getString(jsonObject, "message"));
				response.setDetails(JSONParserUtil.getArray(jsonObject, "details"));
				response.setArrayDetails(JSONParserUtil.getArray(jsonObject, "details"));
				
			} else {
				
				logger.info("RESPONSE CODE :: " + response.getStatus());
				System.err.print(strResponse);
				response.setCode("F00");
				response.setMessage("Service unavailable");
				response.setStatus("FAILED");
				response.setDetails(APIUtils.getFailedJSON().toString());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("F00");
			response.setMessage("Service unavailable");
			response.setStatus("FAILED");
			response.setDetails(APIUtils.getFailedJSON().toString());
		}

		return response;
	}
}
