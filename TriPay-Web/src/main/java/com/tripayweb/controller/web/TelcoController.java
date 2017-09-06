package com.tripayweb.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tripayweb.api.ITelcoApi;
import com.tripayweb.model.app.request.TelcoRequest;
import com.tripayweb.model.app.response.TelcoReponse;
import com.tripayweb.util.LogCat;

@Controller
@RequestMapping("/User")
public class TelcoController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private MessageSource messageSource;
	
	private final ITelcoApi telco;

	public TelcoController(ITelcoApi telco) {
		this.telco = telco;
	}

	@Override
	public void setMessageSource(MessageSource arg0) {
	}

	@RequestMapping(method = RequestMethod.POST, value = "/Telco/CircleOperator", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<TelcoReponse> getHome(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
			@RequestBody TelcoRequest req, ModelMap model) {
		TelcoReponse resp = telco.request(req);
		if (resp != null) {
			LogCat.print("Returning");
			LogCat.print("code " + resp.getServiceCode());
			LogCat.print("number " + resp.getName());
		}
		return new ResponseEntity<TelcoReponse>(resp, HttpStatus.OK);
	}
}
