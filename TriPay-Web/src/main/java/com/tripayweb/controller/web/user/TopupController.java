package com.tripayweb.controller.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.instantpay.api.IValidationApi;
import com.instantpay.model.request.ValidationRequest;
import com.tripayweb.app.api.ITransactionApi;
import com.tripayweb.app.model.request.TransactionRequest;
import com.tripayweb.model.app.response.TransactionUserResponse;
import com.tripayweb.model.web.CarrotFryDTO;
import com.tripayweb.util.APIUtils;
import com.tripayweb.util.ConvertUtil;
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
import com.tripayweb.app.api.ITopupApi;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.request.DataCardTopupRequest;
import com.tripayweb.app.model.request.PostpaidTopupRequest;
import com.tripayweb.app.model.request.PrepaidTopupRequest;
import com.tripayweb.app.model.response.DataCardTopupResponse;
import com.tripayweb.app.model.response.PostpaidTopupResponse;
import com.tripayweb.app.model.response.PrepaidTopupResponse;
import com.tripayweb.model.error.TopupError;
import com.tripayweb.util.Authorities;
import com.tripayweb.validation.TopupValidation;

@Controller
@RequestMapping("/User/MobileTopup")
public class TopupController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;

	private final ITopupApi appTopupApi;
	private final IAuthenticationApi authenticationApi;
	private final TopupValidation topupValidation;
	private final ICouponApi couponApi;
	private final ITransactionApi transactionApi;
	private final IValidationApi validationApi;
	public TopupController(ITopupApi appTopupApi, IAuthenticationApi authenticationApi, TopupValidation topupValidation,
			ICouponApi couponApi,ITransactionApi transactionApi,IValidationApi validationApi) {
		this.appTopupApi = appTopupApi;
		this.authenticationApi = authenticationApi;
		this.topupValidation = topupValidation;
		this.couponApi = couponApi;
		this.transactionApi = transactionApi;
		this.validationApi = validationApi;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@RequestMapping(value = "/ProcessPrepaid", method = RequestMethod.POST)
	ResponseEntity<PrepaidTopupResponse> prePaid(@ModelAttribute("topup") PrepaidTopupRequest dto,
			HttpServletRequest request, ModelMap modelMap, HttpServletResponse response, HttpSession session) {

		System.err.println("HIT ON PREPAID TOPUP");
		PrepaidTopupResponse result = new PrepaidTopupResponse();
		TopupError error = topupValidation.checkError(dto);
		if (error.isValid()) {
			System.err.println(error);
			String sessionId = (String) session.getAttribute("sessionId");
			if (sessionId != null && sessionId.length() != 0) {
				String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
				System.err.println(authority);
				if (authority != null) {
					if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
						TransactionRequest transactionRequest = new TransactionRequest();
						transactionRequest.setSessionId(sessionId);
						transactionRequest.setAmount(dto.getAmount());
						ValidationRequest validationRequest = ConvertUtil.convertPrepaidTopupRequest(dto);
						//ResponseDTO responseDTO = validationApi.validateTransaction(validationRequest);
//						if(responseDTO.isSuccess()) {
						if(true) {
							TransactionRequest newRequest = new TransactionRequest();
							newRequest.setSessionId(sessionId);
							newRequest.setAmount(dto.getAmount());
							newRequest.setTransactionRefNo("D");
							ResponseDTO responseDTO1 = transactionApi.validateTransaction(newRequest);
							if(responseDTO1.isValid()) {
								dto.setSessionId(sessionId);
								result = appTopupApi.prePaid(dto);
								CarrotFryDTO carrotFryDTO = new CarrotFryDTO();
								carrotFryDTO.setAmount(dto.getAmount());
								carrotFryDTO.setCity(" ");
								carrotFryDTO.setEmail((String) session.getAttribute("emailId"));
								carrotFryDTO.setMobileNo((String) session.getAttribute("username"));
								couponApi.sendCarrotFryCoupons(carrotFryDTO);
							}else{
								result.setCode("F00");
								result.setMessage(responseDTO1.getMessage());
								result.setResponse(APIUtils.getCustomJSON("F00",responseDTO1.getMessage()).toString());

							}
						}else {
							result.setCode("F00");
							//result.setMessage(responseDTO.getMessage());
						//	result.setResponse(APIUtils.getCustomJSON("F00",responseDTO.getMessage()).toString());

						}
						return new ResponseEntity<PrepaidTopupResponse>(result, HttpStatus.OK);
					}
				}
			}
		} else {
			result.setCode("F00");
			result.setMessage("Invalid Input");
		}
		return new ResponseEntity<PrepaidTopupResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/ProcessPostpaid", method = RequestMethod.POST)
	ResponseEntity<PostpaidTopupResponse> postPaid(@ModelAttribute("topup") PostpaidTopupRequest dto,
			HttpServletRequest request, ModelMap modelMap, HttpServletResponse response, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		PostpaidTopupResponse result = new PostpaidTopupResponse();
		TopupError error = topupValidation.checkError(dto);
		if (error.isValid()) {
			if (sessionId != null && sessionId.length() != 0) {
				String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
				if (authority != null) {
					if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
						TransactionRequest transactionRequest = new TransactionRequest();
						transactionRequest.setSessionId(sessionId);
						transactionRequest.setAmount(dto.getAmount());
						ValidationRequest validationRequest = ConvertUtil.convertPostpaidTopupRequest(dto);
						ResponseDTO responseDTO = validationApi.validateTransaction(validationRequest);
						if(responseDTO.isSuccess()) {
							TransactionRequest newRequest = new TransactionRequest();
							newRequest.setSessionId(sessionId);
							newRequest.setAmount(dto.getAmount());
							newRequest.setTransactionRefNo("D");
							ResponseDTO responseDTO1 = transactionApi.validateTransaction(newRequest);
							if(responseDTO1.isValid()) {

								dto.setSessionId(sessionId);
								result = appTopupApi.postPaid(dto);
								CarrotFryDTO carrotFryDTO = new CarrotFryDTO();
								carrotFryDTO.setAmount(dto.getAmount());
								carrotFryDTO.setCity(" ");
								carrotFryDTO.setEmail((String) session.getAttribute("emailId"));
								carrotFryDTO.setMobileNo((String) session.getAttribute("username"));
								couponApi.sendCarrotFryCoupons(carrotFryDTO);
							}else {
								result.setMessage(responseDTO1.getMessage());
								result.setCode("F00");
								result.setResponse(APIUtils.getCustomJSON("F00",responseDTO1.getMessage()).toString());

							}
						}else {
							result.setCode("F00");
							result.setMessage(responseDTO.getMessage());
							result.setResponse(APIUtils.getCustomJSON("F00",responseDTO.getMessage()).toString());

						}
						return new ResponseEntity<PostpaidTopupResponse>(result, HttpStatus.OK);
					}
				}
			}
		} else {
			result.setCode("F00");
			result.setMessage("Invalid Input");
		}
		return new ResponseEntity<PostpaidTopupResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/ProcessDataCard", method = RequestMethod.POST)
	ResponseEntity<DataCardTopupResponse> dataCard(@ModelAttribute("topup") DataCardTopupRequest dto,
			HttpServletRequest request, ModelMap modelMap, HttpServletResponse response, HttpSession session) {
		String sessionId = (String) session.getAttribute("sessionId");
		DataCardTopupResponse result = new DataCardTopupResponse();
		TopupError error = topupValidation.checkError(dto);
		if (error.isValid()) {
			if (sessionId != null && sessionId.length() != 0) {
				String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
				if (authority != null) {
					if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
						TransactionRequest transactionRequest = new TransactionRequest();
						transactionRequest.setSessionId(sessionId);
						transactionRequest.setAmount(dto.getAmount());
						ValidationRequest validationRequest = ConvertUtil.convertDataCardRequest(dto);
						ResponseDTO responseDTO = validationApi.validateTransaction(validationRequest);
						if(responseDTO.isSuccess()) {
							TransactionRequest newRequest = new TransactionRequest();
							newRequest.setSessionId(sessionId);
							newRequest.setAmount(dto.getAmount());
							newRequest.setTransactionRefNo("D");
							ResponseDTO responseDTO1 = transactionApi.validateTransaction(newRequest);
							if(responseDTO1.isValid()) {
								dto.setSessionId(sessionId);
								result = appTopupApi.dataCard(dto);
								CarrotFryDTO carrotFryDTO = new CarrotFryDTO();
								carrotFryDTO.setAmount(dto.getAmount());
								carrotFryDTO.setCity(" ");
								carrotFryDTO.setEmail((String) session.getAttribute("emailId"));
								carrotFryDTO.setMobileNo((String) session.getAttribute("username"));
								couponApi.sendCarrotFryCoupons(carrotFryDTO);
							}else {
								result.setCode("F00");
								result.setMessage(responseDTO1.getMessage());
								result.setResponse(APIUtils.getCustomJSON("F00",responseDTO1.getMessage()).toString());

							}
						}else {
							result.setCode("F00");
							result.setMessage(responseDTO.getMessage());
							result.setResponse(APIUtils.getCustomJSON("F00",responseDTO.getMessage()).toString());

						}
						return new ResponseEntity<DataCardTopupResponse>(result, HttpStatus.OK);
					}
				}
			}
		} else {
			result.setCode("F00");
			result.setMessage("Invalid Input");
		}
		return new ResponseEntity<DataCardTopupResponse>(result, HttpStatus.OK);
	}

}
