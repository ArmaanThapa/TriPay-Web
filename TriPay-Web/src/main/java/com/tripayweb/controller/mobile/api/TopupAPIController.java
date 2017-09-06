package com.tripayweb.controller.mobile.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupon.api.ICouponApi;
import com.instantpay.api.IValidationApi;
import com.instantpay.model.request.ValidationRequest;
import com.tripayweb.api.IAuthenticationApi;
import com.tripayweb.app.api.ITransactionApi;
import com.tripayweb.app.model.request.*;
import com.tripayweb.app.model.response.*;
import com.tripayweb.model.web.CarrotFryDTO;
import com.tripayweb.util.ConvertUtil;
import com.thirdparty.model.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tripayweb.app.api.ITopupApi;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Role;
import com.tripayweb.util.APIUtils;

@Controller
@RequestMapping("/Api/{version}/{role}/{device}/{language}/Topup")
public class TopupAPIController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;

	private final ITopupApi appTopupApi;
	private final IAuthenticationApi authenticationApi;
	private final ICouponApi couponApi;
	private final IValidationApi validationApi;
	private final ITransactionApi transactionApi;
	public TopupAPIController(ITopupApi topupApi,IAuthenticationApi authenticationApi,ICouponApi couponApi,IValidationApi validationApi,ITransactionApi transactionApi) {
		this.appTopupApi = topupApi;
		this.authenticationApi = authenticationApi;
		this.couponApi = couponApi;
		this.validationApi = validationApi;
		this.transactionApi = transactionApi;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/PrePaid", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<PrepaidTopupResponse> prePaid(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody PrepaidTopupRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		PrepaidTopupResponse result = new PrepaidTopupResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				ValidationRequest validationRequest = ConvertUtil.convertPrepaidTopupRequest(dto);
				ResponseDTO responseDTO = validationApi.validateTransaction(validationRequest);
				if(responseDTO.isSuccess()) {
					System.err.println("inside the prepaid top up recharge");
					TransactionRequest newRequest = new TransactionRequest();
					newRequest.setSessionId(dto.getSessionId());
					newRequest.setAmount(dto.getAmount());
					newRequest.setTransactionRefNo("D");
					ResponseDTO responseDTO1 = transactionApi.validateTransaction(newRequest);
					if(responseDTO1.isValid()) {
						result = appTopupApi.prePaid(dto);
					}else {
						result.setCode("F00");
						result.setMessage(responseDTO1.getMessage());
					}
				}else{
					result.setCode("F00");
					result.setMessage(responseDTO.getMessage());
				}
				sendCoupons(dto.getSessionId(),dto.getAmount());
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
		return new ResponseEntity<PrepaidTopupResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/PostPaid", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<PostpaidTopupResponse> postPaid(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody PostpaidTopupRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		PostpaidTopupResponse result = new PostpaidTopupResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				ValidationRequest validationRequest = ConvertUtil.convertPostpaidTopupRequest(dto);
				ResponseDTO responseDTO = validationApi.validateTransaction(validationRequest);
				if(responseDTO.isSuccess()) {
					TransactionRequest newRequest = new TransactionRequest();
					newRequest.setSessionId(dto.getSessionId());
					newRequest.setAmount(dto.getAmount());
					newRequest.setTransactionRefNo("D");
					ResponseDTO responseDTO1 = transactionApi.validateTransaction(newRequest);
					if(responseDTO1.isValid()) {
						result = appTopupApi.postPaid(dto);
						sendCoupons(dto.getSessionId(), dto.getAmount());
					}else {
						result.setCode("F00");
						result.setMessage(responseDTO1.getMessage());
					}
				}else{
					result.setCode("F00");
					result.setMessage(responseDTO.getMessage());
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
		return new ResponseEntity<PostpaidTopupResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/DataCard", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<DataCardTopupResponse> dataCard(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody DataCardTopupRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		DataCardTopupResponse result = new DataCardTopupResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				ValidationRequest validationRequest = ConvertUtil.convertDataCardRequest(dto);
				ResponseDTO responseDTO = validationApi.validateTransaction(validationRequest);
				if(responseDTO.isSuccess()) {
					TransactionRequest newRequest = new TransactionRequest();
					newRequest.setSessionId(dto.getSessionId());
					newRequest.setAmount(dto.getAmount());
					newRequest.setTransactionRefNo("D");
					ResponseDTO responseDTO1 = transactionApi.validateTransaction(newRequest);
					if(responseDTO1.isValid()) {
						result = appTopupApi.dataCard(dto);
						sendCoupons(dto.getSessionId(), dto.getAmount());
					}else {
						result.setCode("F00");
						result.setMessage(responseDTO1.getMessage());
					}
				}else {
					result.setCode("F00");
					result.setMessage(responseDTO.getMessage());
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
		return new ResponseEntity<DataCardTopupResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/GetOperatorAndCircle", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<GetOperatorAndCircleResponse> getOperatorAndCircle(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody GetOperatorAndCircleRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		GetOperatorAndCircleResponse result = new GetOperatorAndCircleResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = appTopupApi.operatorAndcircle(dto);
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
		return new ResponseEntity<GetOperatorAndCircleResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/GetOperatorAndCircleForMobile", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<GetOperatorAndCircleForMobResponse> getOperatorAndCircleForMobile(
			@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
			@PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
			@RequestBody GetOperatorAndCircleForMobRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		GetOperatorAndCircleForMobResponse result = new GetOperatorAndCircleForMobResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = appTopupApi.operatorAndcircleForMob(dto);
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
		return new ResponseEntity<GetOperatorAndCircleForMobResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/GetPlans", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<BrowsePlansResponse> getPlans(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody BrowsePlansRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		BrowsePlansResponse result = new BrowsePlansResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				result = appTopupApi.getPlansForMobile(dto);
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

		return new ResponseEntity<BrowsePlansResponse>(result, HttpStatus.OK);
	}

	private void sendCoupons(String sessionId,String amount){
		UserDetailsResponse response = authenticationApi.getUserDetailsFromSession(sessionId);
		CarrotFryDTO carrotFryDTO = new CarrotFryDTO();
		carrotFryDTO.setAmount(amount);
		carrotFryDTO.setCity(" ");
		carrotFryDTO.setEmail(response.getEmail());
		carrotFryDTO.setMobileNo(response.getContactNo());
		couponApi.sendCarrotFryCoupons(carrotFryDTO);
	}

}
