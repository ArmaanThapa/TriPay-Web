package com.tripayweb.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tripayweb.app.api.IVersionApi;
import com.tripayweb.app.model.request.Utility;
import com.tripayweb.app.model.request.VersionRequest;
import com.tripayweb.app.model.response.VersionResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tripayweb.model.app.request.CheckVersionRequest;
import com.tripayweb.validation.VersionVaildation;

@Controller
@RequestMapping("/Version")
public class VersionController {

	private final IVersionApi versionApi;

	public VersionController(IVersionApi versionApi){
			this.versionApi = versionApi;
	}

    @RequestMapping(value = {"/Check","/Validate"}, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<VersionResponse> checkAppVersion(HttpServletRequest request, HttpServletResponse response,
													@RequestBody Utility dto) throws JSONException {

		VersionResponse result = versionApi.validateVersion(dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = {"/Update","/Increment"}, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<VersionResponse> checkAppVersion(HttpServletRequest request, HttpServletResponse response,
													@RequestBody VersionRequest dto) throws JSONException {
		VersionResponse result = versionApi.updateVersion(dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}


}
