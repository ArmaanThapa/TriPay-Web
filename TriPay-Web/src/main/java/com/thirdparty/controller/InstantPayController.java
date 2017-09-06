package com.thirdparty.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tripayweb.app.model.response.IPayCallBack;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tripayweb.thirdparty.request.BalanceRequest;
import com.tripayweb.thirdparty.request.InstantPayConstants;
import com.tripayweb.thirdparty.request.ServicesRequest;
import com.tripayweb.thirdparty.request.StatusCheckRequest;
import com.tripayweb.thirdparty.request.TransactionRequest;
import com.tripayweb.thirdparty.request.ValidationRequest;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/InstantPay")
public class InstantPayController implements MessageSourceAware {

	private MessageSource messageSource;

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/Transaction/Process", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<String> processTransaction(TransactionRequest dto, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		try {
			String stringResponse = "";
			Client c = Client.create();
			c.addFilter(new LoggingFilter(System.out));
			System.out.println(dto);
			for(int i=0 ; i< 10 ;i++){
				System.err.print("i::"+i);
			}
			WebResource resource = c.resource(InstantPayConstants.URL_TRANSACTION)
					.queryParam(InstantPayConstants.API_KEY_TOKEN, (dto.getToken() == null) ? "" : dto.getToken())
					.queryParam(InstantPayConstants.API_KEY_SPKEY, (dto.getSpKey() == null) ? "" : dto.getSpKey())
					.queryParam(InstantPayConstants.API_KEY_AGENTID, (dto.getAgentId() == null) ? "" : dto.getAgentId())
					.queryParam(InstantPayConstants.API_KEY_ACCOUNT, (dto.getAccount() == null) ? "" : dto.getAccount())
					.queryParam(InstantPayConstants.API_KEY_AMOUNT, (dto.getAmount() == null) ? "" : dto.getAmount())
					.queryParam(InstantPayConstants.API_KEY_OPTIONAL1,
							(dto.getOptional1() == null) ? "" : dto.getOptional1())
					.queryParam(InstantPayConstants.API_KEY_OPTIONAL2,
							(dto.getOptional2() == null) ? "" : dto.getOptional2())
					.queryParam(InstantPayConstants.API_KEY_OPTIONAL3,
							(dto.getOptional3() == null) ? "" : dto.getOptional3())
					.queryParam(InstantPayConstants.API_KEY_OPTIONAL4,
							(dto.getOptional4() == null) ? "" : dto.getOptional4())
					.queryParam(InstantPayConstants.API_KEY_OPTIONAL5,
							(dto.getOptional5() == null) ? "" : dto.getOptional5())
					.queryParam(InstantPayConstants.API_KEY_FORMAT, (dto.getFormat() == null) ? "" : dto.getFormat());
			ClientResponse clientResponse = resource.get(ClientResponse.class);
			stringResponse = clientResponse.getEntity(String.class);
			System.err.println("RESPONSE FETCHED AND RESPONSE IS ::"+stringResponse);
			return new ResponseEntity<String>(stringResponse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("", HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/Transaction/VNet", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public String processTransaction(HttpServletRequest request,
											  HttpServletResponse response, HttpSession session) {
			String stringResponse = "";
		try {
			Client c = Client.create();
			c.addFilter(new LoggingFilter(System.out));
			WebResource resource = c.resource("http://219.65.65.171:9080/NASApp/BANA623WAR/BANKAWAY?Action.ShoppingMall.Login.Init=Y&BankId=029&MD=P&USER_LANG_ID=001&UserType=1&AppType=corporate");
			ClientResponse clientResponse = resource.get(ClientResponse.class);
			stringResponse = clientResponse.getEntity(String.class);
			System.err.println(stringResponse);

		} catch (Exception e) {
			e.printStackTrace();
//			return new ResponseEntity<String>("", HttpStatus.OK);
		}
		return stringResponse;
	}

	@RequestMapping(value = "/Validation/Process", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<String> processValidation(ValidationRequest dto, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		try {
			String stringResponse = "";
			Client c = Client.create();
			c.addFilter(new LoggingFilter(System.out));
			WebResource resource = c.resource(InstantPayConstants.URL_VALIDATION)
					.queryParam(InstantPayConstants.API_KEY_TOKEN, (dto.getToken() == null) ? "" : dto.getToken())
					.queryParam(InstantPayConstants.API_KEY_SPKEY, (dto.getSpKey() == null) ? "" : dto.getSpKey())
					.queryParam(InstantPayConstants.API_KEY_AGENTID, (dto.getAgentId() == null) ? "" : dto.getAgentId())
					.queryParam(InstantPayConstants.API_KEY_ACCOUNT, (dto.getAccount() == null) ? "" : dto.getAccount())
					.queryParam(InstantPayConstants.API_KEY_AMOUNT, (dto.getAmount() == null) ? "" : dto.getAmount())
					.queryParam(InstantPayConstants.API_KEY_OPTIONAL1,
							(dto.getOptional1() == null) ? "" : dto.getOptional1())
					.queryParam(InstantPayConstants.API_KEY_OPTIONAL2,
							(dto.getOptional2() == null) ? "" : dto.getOptional2())
					.queryParam(InstantPayConstants.API_KEY_OPTIONAL3,
							(dto.getOptional3() == null) ? "" : dto.getOptional3())
					.queryParam(InstantPayConstants.API_KEY_OPTIONAL4,
							(dto.getOptional4() == null) ? "" : dto.getOptional4())
					.queryParam(InstantPayConstants.API_KEY_OPTIONAL5,
							(dto.getOptional5() == null) ? "" : dto.getOptional5())
					.queryParam(InstantPayConstants.API_KEY_FORMAT, (dto.getFormat() == null) ? "" : dto.getFormat());
			ClientResponse clientResponse = resource.get(ClientResponse.class);
			stringResponse = clientResponse.getEntity(String.class);
			return new ResponseEntity<String>(stringResponse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("{}", HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/Services/Process", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<String> getServices(ServicesRequest dto, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		try {
			String stringResponse = "";
			Client c = Client.create();
			c.addFilter(new LoggingFilter(System.out));
			WebResource resource = c.resource(InstantPayConstants.URL_SERVICE)
					.queryParam(InstantPayConstants.API_KEY_TOKEN, dto.getToken())
					.queryParam(InstantPayConstants.API_KEY_TYPE, dto.getType())
					.queryParam(InstantPayConstants.API_KEY_FORMAT, dto.getFormat());
			ClientResponse clientResponse = resource.get(ClientResponse.class);
			stringResponse = clientResponse.getEntity(String.class);
			return new ResponseEntity<String>(stringResponse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("{}", HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/StatusCheck/Process", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<String> checkStatus(StatusCheckRequest dto, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		try {
			String stringResponse = "";
			Client c = Client.create();
			c.addFilter(new LoggingFilter(System.out));
			WebResource resource = c.resource(InstantPayConstants.URL_STATUS)
					.queryParam(InstantPayConstants.API_KEY_TOKEN, dto.getToken())
					.queryParam(InstantPayConstants.API_KEY_AGENTID, dto.getAgentId())
					.queryParam(InstantPayConstants.API_KEY_FORMAT, dto.getFormat());
			ClientResponse clientResponse = resource.get(ClientResponse.class);
			stringResponse = clientResponse.getEntity(String.class);
			return new ResponseEntity<String>(stringResponse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("{}", HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/Balance/Process", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<String> getBalance(BalanceRequest dto, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		try {
			String stringResponse = "";
			Client c = Client.create();
			c.addFilter(new LoggingFilter(System.out));
			WebResource resource = c.resource(InstantPayConstants.URL_BALANCE)
					.queryParam(InstantPayConstants.API_KEY_TOKEN, dto.getToken())
					.queryParam(InstantPayConstants.API_KEY_FORMAT, dto.getFormat());

			ClientResponse clientResponse = resource.get(ClientResponse.class);
			stringResponse = clientResponse.getEntity(String.class);
			return new ResponseEntity<String>(stringResponse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("{}", HttpStatus.OK);
		}
	}

}
