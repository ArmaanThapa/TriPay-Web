package com.tripayweb.controller.web.merchant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.api.ISendMoneyApi;
import com.tripayweb.app.api.ITransactionApi;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.SendMoneyBankRequest;
import com.tripayweb.app.model.request.TransactionRequest;
import com.tripayweb.app.model.response.SendMoneyBankResponse;
import com.tripayweb.model.error.SendMoneyBankError;
import com.tripayweb.util.Authorities;
import com.tripayweb.validation.SendMoneyValidation;
import com.thirdparty.model.ResponseDTO;

@Controller
@RequestMapping("/Api/{version}/{role}/{device}/{language}/SendMoney")
public class AccountController implements MessageSourceAware {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;

	private final ISendMoneyApi sendMoneyApi;
	private final IAuthenticationApi authenticationApi;
	private final SendMoneyValidation sendMoneyValidation;
	private final ITransactionApi transactionApi;

	public AccountController(ISendMoneyApi sendMoneyApi,IAuthenticationApi authenticationApi,
						SendMoneyValidation sendMoneyValidation,ITransactionApi transactionApi) {
		this.sendMoneyApi = sendMoneyApi;
		this.authenticationApi = authenticationApi;
		this.sendMoneyValidation = sendMoneyValidation;
		this.transactionApi = transactionApi;
	}

	@Override
	public void setMessageSource(MessageSource arg0) {
		this.messageSource = messageSource;
	}
	
	@RequestMapping(value = "/BankAccount", method = RequestMethod.POST)
	ResponseEntity<SendMoneyBankResponse> sendMoneyBankRequest(@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
            												   @PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
            												   @RequestBody SendMoneyBankRequest dto,@RequestHeader(value = "hash", required = false) String hash,
            												   HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		
		SendMoneyBankResponse result = new SendMoneyBankResponse();
		if(role.equalsIgnoreCase(Role.MERCHANT.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				
				SendMoneyBankError error = sendMoneyValidation.checkBankError(dto);
				if (error.isValid()) {
					TransactionRequest newRequest = new TransactionRequest();
					newRequest.setSessionId(dto.getSessionId());
					newRequest.setAmount(dto.getAmount());
					newRequest.setTransactionRefNo("D");
					ResponseDTO responseDTO1 = transactionApi.validateMTransaction(newRequest);
					if(responseDTO1.isValid()) {

						result = sendMoneyApi.sendMoneyToMBankRequest(dto);
						return new ResponseEntity<SendMoneyBankResponse>(result, HttpStatus.OK);
					}else{
						result.setCode("F00");
						result.setMessage(responseDTO1.getMessage());
					}
				} else {
					result.setCode("F04");
					result.setMessage("Invalid Input");
					result.setDetails(error.toJSON().toString());
				} 
			}else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
			}
		}else {
			result.setCode("F06");
			result.setMessage("Unauthorized role");
			result.setStatus("FAILED");
		}
		return new ResponseEntity<SendMoneyBankResponse>(result, HttpStatus.OK);
	}
	
}
