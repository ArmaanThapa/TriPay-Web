package com.tripayweb.controller.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tripayweb.app.api.ICallBackApi;
import com.tripayweb.app.model.request.CallBackRequest;
import com.tripayweb.app.model.response.CallBackResponse;

@Controller
public class CallBackController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;

	private ICallBackApi callBackApi;

	public CallBackController(ICallBackApi callBackApi) {
		this.callBackApi = callBackApi;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	// http://www.xyz.com/callbackpage?ipay_id=value&agent_id=value&opr_id=value&status=value&res_code=value&res_msg=value
	@RequestMapping(method = RequestMethod.GET, value = "/InstantPay/callbackpage")
	public ResponseEntity<String> instantPayCallback(@RequestParam(value = "ipay_id", required = false) String ipayId,
			@RequestParam(value = "agent_id", required = false) String agentId,
			@RequestParam(value = "opr_id", required = false) String operatorId,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "res_code", required = false) String responseCode,
			@RequestParam(value = "res_msg", required = false) String responseMessage, HttpServletRequest request,
			ModelMap modelMap, HttpServletResponse response, CallBackRequest req) {

		if (status.equalsIgnoreCase("REFUND")) {
			req.setAgent_id(agentId);
			req.setIpay_id(ipayId);
			req.setOpr_id(operatorId);
			req.setRes_code(responseCode);
			req.setRes_msg(responseMessage);
			req.setStatus(status);
//			CallBackResponse resp = callBackApi.attempt(req);
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("", HttpStatus.OK);
		}
	}

}
