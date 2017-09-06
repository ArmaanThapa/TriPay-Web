package com.tripayweb.controller.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tripayweb.app.api.ITransactionApi;
import com.tripayweb.app.model.request.SendMoneyBankRequest;
import com.tripayweb.app.model.request.TransactionRequest;
import com.tripayweb.app.model.response.SendMoneyBankResponse;
import com.tripayweb.model.error.SendMoneyBankError;
import com.tripayweb.model.web.CarrotFryDTO;
import com.tripayweb.util.APIUtils;
import com.thirdparty.model.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coupon.api.ICouponApi;
import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.api.ISendMoneyApi;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.SendMoneyMobileRequest;
import com.tripayweb.app.model.response.SendMoneyMobileResponse;
import com.tripayweb.model.error.SendMoneyMobileError;
import com.tripayweb.util.Authorities;
import com.tripayweb.validation.PayAtStoreValidation;
import com.tripayweb.validation.SendMoneyValidation;

@Controller
@RequestMapping("/User/SendMoney")
public class SendMoneyController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;

	private final ISendMoneyApi sendMoneyApi;
	private final IAuthenticationApi authenticationApi;
	private final SendMoneyValidation sendMoneyValidation;
	private final PayAtStoreValidation payAtStoreValidation;
	private final ICouponApi couponApi; 
	private final ITransactionApi transactionApi;
	public SendMoneyController(ISendMoneyApi sendMoneyApi, IAuthenticationApi authenticationApi,
			SendMoneyValidation sendMoneyValidation, PayAtStoreValidation payAtStoreValidation,ICouponApi couponApi,ITransactionApi transactionApi) {
		this.sendMoneyApi = sendMoneyApi;
		this.authenticationApi = authenticationApi;
		this.sendMoneyValidation = sendMoneyValidation;
		this.payAtStoreValidation = payAtStoreValidation;
		this.couponApi = couponApi;
		this.transactionApi = transactionApi;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/Mobile", method = RequestMethod.POST)
	ResponseEntity<SendMoneyMobileResponse> sendMoneyMobileRequest(@ModelAttribute SendMoneyMobileRequest dto,
			HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		SendMoneyMobileResponse result = new SendMoneyMobileResponse();
		SendMoneyMobileError error = sendMoneyValidation.checkMobileError(dto);
		if (error.isValid()) {
			if (sessionId != null && sessionId.length() != 0) {
				String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
				if (authority != null) {
					if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
						TransactionRequest transactionRequest = new TransactionRequest();
						transactionRequest.setSessionId(sessionId);
						transactionRequest.setAmount(dto.getAmount());
						transactionRequest.setTransactionRefNo("D");
						ResponseDTO responseDTO1 = transactionApi.validateTransaction(transactionRequest);
						if(responseDTO1.isValid()) {
							dto.setSessionId(sessionId);
							result = sendMoneyApi.sendMoneyMobileRequest(dto);
						}else {
							result.setCode("F00");
							result.setMessage(responseDTO1.getMessage());
							result.setResponse(APIUtils.getCustomJSON("F00",responseDTO1.getMessage()).toString());

						}
						return new ResponseEntity<SendMoneyMobileResponse>(result, HttpStatus.OK);
					}
				}
			}
		} else {
			result.setCode("F00");
			result.setMessage("Invalid Input");
		}
		return new ResponseEntity<SendMoneyMobileResponse>(result, HttpStatus.OK);
	}


	@RequestMapping(value = "/Bank", method = RequestMethod.POST)
	ResponseEntity<SendMoneyBankResponse> sendMoneyBankRequest(@ModelAttribute SendMoneyBankRequest dto,
																   HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		SendMoneyBankResponse result = new SendMoneyBankResponse();
		SendMoneyBankError error = sendMoneyValidation.checkBankError(dto);
		if (error.isValid()) {
			if (sessionId != null && sessionId.length() != 0) {
				String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
				if (authority != null) {
					if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
						TransactionRequest transactionRequest = new TransactionRequest();
						transactionRequest.setSessionId(sessionId);
						transactionRequest.setAmount(dto.getAmount());
						transactionRequest.setTransactionRefNo("D");
						ResponseDTO responseDTO1 = transactionApi.validateTransaction(transactionRequest);
						if(responseDTO1.isValid()) {
							dto.setSessionId(sessionId);
							result = sendMoneyApi.sendMoneyBankRequest(dto);
						}else {
							result.setMessage(responseDTO1.getMessage());
							result.setCode("F00");
						}
						return new ResponseEntity<SendMoneyBankResponse>(result, HttpStatus.OK);
					}
				}
			}
		} else {
			result.setCode("F00");
			result.setMessage("Invalid Input");
		}
		return new ResponseEntity<SendMoneyBankResponse>(result, HttpStatus.OK);
	}
}
