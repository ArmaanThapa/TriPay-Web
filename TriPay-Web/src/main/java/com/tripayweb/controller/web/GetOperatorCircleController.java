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

import com.tripayweb.app.api.ITopupApi;
import com.tripayweb.app.model.request.GetOperatorAndCircleRequest;
import com.tripayweb.app.model.response.GetOperatorAndCircleResponse;
import com.tripayweb.util.LogCat;

@Controller
@RequestMapping("/User")
public class GetOperatorCircleController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private MessageSource messageSource;
	
	private final ITopupApi topupAPi;

	public GetOperatorCircleController(ITopupApi topupAPi) {
		this.topupAPi = topupAPi;
	}

	@Override
	public void setMessageSource(MessageSource arg0) {

	}

	@RequestMapping(method = RequestMethod.POST, value = "/Telco/GetOperatorsCircles", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<GetOperatorAndCircleResponse> getHome(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
			@RequestBody GetOperatorAndCircleRequest req, ModelMap model) {

		GetOperatorAndCircleResponse resp = topupAPi.operatorAndcircle(req);
		System.out.println("Test "+req.getSessionId());
		if (resp != null) {
				LogCat.print("Returning");
				LogCat.print("code "+resp.getCode());
				
				return new ResponseEntity<GetOperatorAndCircleResponse>(resp, HttpStatus.OK);
		}

		return new ResponseEntity<GetOperatorAndCircleResponse>(resp, HttpStatus.OK);
	}
}
