package com.tripayweb.controller.mobile.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coupon.api.ICouponApi;
import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.api.ITransactionApi;
import com.tripayweb.app.model.request.*;
import com.tripayweb.app.model.response.SendMoneyBankResponse;
import com.tripayweb.model.error.OfflinePaymentError;
import com.tripayweb.model.error.SendMoneyBankError;
import com.tripayweb.util.Authorities;
import com.tripayweb.validation.OfflinePaymentValidation;
import com.tripayweb.validation.PayAtStoreValidation;
import com.tripayweb.validation.SendMoneyValidation;
import com.thirdparty.model.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.tripayweb.app.api.ISendMoneyApi;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.response.ListStoreApiResponse;
import com.tripayweb.app.model.response.OfflinePaymentResponse;
import com.tripayweb.app.model.response.PayAtStoreResponse;
import com.tripayweb.app.model.response.SendMoneyMobileResponse;
import com.tripayweb.util.APIUtils;

@Controller
@RequestMapping("/Api/{version}/{role}/{device}/{language}/SendMoney")
public class SendMoneyAPIController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;
	private final ISendMoneyApi sendMoneyApi;
	private final IAuthenticationApi authenticationApi;
	private final SendMoneyValidation sendMoneyValidation;
	private final PayAtStoreValidation payAtStoreValidation;
	private final ICouponApi couponApi;
	private final ITransactionApi transactionApi;
	private final OfflinePaymentValidation offlinePaymentValidation;
	public SendMoneyAPIController(ISendMoneyApi sendMoneyApi, IAuthenticationApi authenticationApi, 
					SendMoneyValidation sendMoneyValidation, PayAtStoreValidation payAtStoreValidation,
					ICouponApi couponApi,ITransactionApi transactionApi,OfflinePaymentValidation offlinePaymentValidation) {
		this.sendMoneyApi = sendMoneyApi;
		this.authenticationApi = authenticationApi;
		this.sendMoneyValidation = sendMoneyValidation;
		this.payAtStoreValidation = payAtStoreValidation;
		this.couponApi = couponApi;
		this.transactionApi = transactionApi;
		this.offlinePaymentValidation = offlinePaymentValidation;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/Mobile", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<SendMoneyMobileResponse> sendMoneyMobile(
			@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
			@PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
			@RequestBody SendMoneyMobileRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		SendMoneyMobileResponse result = new SendMoneyMobileResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				TransactionRequest newRequest = new TransactionRequest();
				newRequest.setSessionId(dto.getSessionId());
				newRequest.setAmount(dto.getAmount());
				newRequest.setTransactionRefNo("D");
				ResponseDTO responseDTO1 = transactionApi.validateTransaction(newRequest);
				if(responseDTO1.isValid()) {

					result = sendMoneyApi.sendMoneyMobileRequest(dto);
				}else {
					result.setCode("F00");
					result.setMessage(responseDTO1.getMessage());
				}
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<SendMoneyMobileResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/Bank", method = RequestMethod.POST)
	ResponseEntity<SendMoneyBankResponse> sendMoneyBankRequest(@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
                                                               @PathVariable(value = "device") String device, @PathVariable(value = "language") String language,@RequestBody SendMoneyBankRequest dto,
                                                               @RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		SendMoneyBankResponse result = new SendMoneyBankResponse();
		SendMoneyBankError error = sendMoneyValidation.checkBankError(dto);
		if (error.isValid()) {
			TransactionRequest newRequest = new TransactionRequest();
			newRequest.setSessionId(dto.getSessionId());
			newRequest.setAmount(dto.getAmount());
			newRequest.setTransactionRefNo("D");
			ResponseDTO responseDTO1 = transactionApi.validateTransaction(newRequest);
			if(responseDTO1.isValid()) {

				result = sendMoneyApi.sendMoneyBankRequest(dto);
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
		return new ResponseEntity<SendMoneyBankResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/Store", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<PayAtStoreResponse> sendMoneyStore(
			@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
			@PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
			@RequestBody PayAtStoreRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		PayAtStoreResponse result = new PayAtStoreResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = sendMoneyApi.payAtStoreResponseRequest(dto);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<PayAtStoreResponse>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ListStore", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<ListStoreApiResponse> listStore(
			@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
			@PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
			@RequestBody ListStoreApiRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		ListStoreApiResponse result = new ListStoreApiResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = sendMoneyApi.listStoreResponseRequest(dto);
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<ListStoreApiResponse>(result, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/OfflinePayment", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<OfflinePaymentResponse> paymentAtStore(
			@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
			@PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
			@RequestBody OfflinePaymentRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		OfflinePaymentResponse result = new OfflinePaymentResponse();
		OfflinePaymentError error = new OfflinePaymentError();
		if (role.equalsIgnoreCase(Role.MERCHANT.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				error = offlinePaymentValidation.checkPaymentError(dto);
				if(error.isValid()) {
					result = sendMoneyApi.offlinePayment(dto);
				} else {
					result.setCode("F04");
					result.setMessage("Invalid Input");
					result.setDetails(error.toJSON().toString());
				}
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<OfflinePaymentResponse>(result, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/Offline/VerifyOTP", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<OfflinePaymentResponse> verifyOTP(
			@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
			@PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
			@RequestBody OfflinePaymentRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		OfflinePaymentResponse result = new OfflinePaymentResponse();
		OfflinePaymentError error = new OfflinePaymentError();
		if (role.equalsIgnoreCase(Role.MERCHANT.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				error = offlinePaymentValidation.checkOTPError(dto);
				if(error.isValid()){
					result = sendMoneyApi.verifyOTP(dto);
				}else {
					result.setCode("F04");
					result.setMessage("Invalid Input");
					result.setDetails(error.toJSON().toString());
				}
			} else {
				result.setSuccess(false);
				result.setCode("F00");
				result.setMessage("Unknown device");
				result.setStatus("FAILED");
				result.setResponse(APIUtils.getFailedJSON().toString());
			}
		} else {
			result.setSuccess(false);
			result.setCode("F00");
			result.setMessage("Unauthorised access");
			result.setStatus("FAILED");
			result.setResponse(APIUtils.getFailedJSON().toString());
		}
		return new ResponseEntity<OfflinePaymentResponse>(result, HttpStatus.OK);
	}
	
	
}
