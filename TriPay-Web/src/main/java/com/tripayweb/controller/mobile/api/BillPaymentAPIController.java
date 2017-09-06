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

import com.tripayweb.app.api.IBillPaymentApi;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Role;
import com.tripayweb.util.APIUtils;

@Controller
@RequestMapping("/Api/{version}/{role}/{device}/{language}/BillPayment")
public class BillPaymentAPIController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;

	private final IBillPaymentApi billPaymentApi;
	private final IAuthenticationApi authenticationApi;
	private final ICouponApi couponApi;
	private final IValidationApi validationApi;
	private final ITransactionApi transactionApi;
	public BillPaymentAPIController(IBillPaymentApi billPaymentApi,IAuthenticationApi authenticationApi,ICouponApi couponApi,IValidationApi validationApi,ITransactionApi transactionApi) {
		this.billPaymentApi = billPaymentApi;
		this.authenticationApi = authenticationApi;
		this.couponApi = couponApi;
		this.validationApi = validationApi;
		this.transactionApi = transactionApi;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/Electricity", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<ElectricityBillPaymentResponse> processElectricityBillPayment(
			@PathVariable(value = "version") String version, @PathVariable(value = "role") String role,
			@PathVariable(value = "device") String device, @PathVariable(value = "language") String language,
			@RequestBody ElectricityBillPaymentRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		ElectricityBillPaymentResponse result = new ElectricityBillPaymentResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				ValidationRequest validationRequest = ConvertUtil.convertElectricityBillPaymentRequest(dto);
				ResponseDTO responseDTO = validationApi.validateTransaction(validationRequest);
				if(responseDTO.isSuccess()) {
					TransactionRequest newRequest = new TransactionRequest();
					newRequest.setSessionId(dto.getSessionId());
					newRequest.setAmount(dto.getAmount());
					newRequest.setTransactionRefNo("D");
					ResponseDTO responseDTO1 = transactionApi.validateTransaction(newRequest);
					if(responseDTO1.isValid()) {
						result = billPaymentApi.electricBill(dto);
//					sendCoupons(dto.getSessionId(), dto.getAmount());
					}else {
						result.setCode("F00");
						result.setMessage(responseDTO1.getMessage());
					}
				}else {
					result.setSuccess(false);
					result.setCode("F00");
					System.err.println(responseDTO.getMessage());
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
		return new ResponseEntity<ElectricityBillPaymentResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/Gas", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<GasBillPaymentResponse> processGasBillPayment(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody GasBillPaymentRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		GasBillPaymentResponse result = new GasBillPaymentResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				ValidationRequest validationRequest = ConvertUtil.convertGasBillPaymentRequest(dto);
				ResponseDTO responseDTO = validationApi.validateTransaction(validationRequest);
				if(responseDTO.isSuccess()) {
					TransactionRequest newRequest = new TransactionRequest();
					newRequest.setSessionId(dto.getSessionId());
					newRequest.setAmount(dto.getAmount());
					newRequest.setTransactionRefNo("D");
					ResponseDTO responseDTO1 = transactionApi.validateTransaction(newRequest);
					if(responseDTO1.isValid()) {

						result = billPaymentApi.gasBill(dto);
//					sendCoupons(dto.getSessionId(), dto.getAmount());
					}else {
						result.setCode("F00");
						result.setMessage(responseDTO1.getMessage());
					}
				}else {
					result.setSuccess(false);
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
		return new ResponseEntity<GasBillPaymentResponse>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/Insurance", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<InsuranceBillPaymentResponse> processInsuranceBillPayment(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody InsuranceBillPaymentRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		InsuranceBillPaymentResponse result = new InsuranceBillPaymentResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				ValidationRequest validationRequest = ConvertUtil.convertInsuranceBillPaymentRequest(dto);
				ResponseDTO responseDTO = validationApi.validateTransaction(validationRequest);
				if(responseDTO.isSuccess()) {
					TransactionRequest newRequest = new TransactionRequest();
					newRequest.setSessionId(dto.getSessionId());
					newRequest.setAmount(dto.getAmount());
					newRequest.setTransactionRefNo("D");
					ResponseDTO responseDTO1 = transactionApi.validateTransaction(newRequest);
					if(responseDTO1.isValid()) {
						result = billPaymentApi.insuranceBill(dto);
//					sendCoupons(dto.getSessionId(), dto.getAmount());
					}else{
						result.setCode("F00");
						result.setMessage(responseDTO1.getMessage());
					}
				}else{
					result.setSuccess(false);
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
		return new ResponseEntity<InsuranceBillPaymentResponse>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/Landline", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<LandlineBillPaymentResponse> processLandlineBillPayment(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody LandlineBillPaymentRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		LandlineBillPaymentResponse result = new LandlineBillPaymentResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				ValidationRequest validationRequest = ConvertUtil.convertLandlineBillPaymentRequest(dto);
				ResponseDTO responseDTO = validationApi.validateTransaction(validationRequest);
				if(responseDTO.isSuccess()) {
					TransactionRequest newRequest = new TransactionRequest();
					newRequest.setSessionId(dto.getSessionId());
					newRequest.setAmount(dto.getAmount());
					newRequest.setTransactionRefNo("D");
					ResponseDTO responseDTO1 = transactionApi.validateTransaction(newRequest);
					if(responseDTO1.isValid()) {
						result = billPaymentApi.landline(dto);
//					sendCoupons(dto.getSessionId(), dto.getAmount());
					}else {
						result.setCode("F00");
						result.setMessage(responseDTO1.getMessage());
					}
				}else {
					result.setSuccess(false);
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
		return new ResponseEntity<LandlineBillPaymentResponse>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/DTH", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<DTHBillPaymentResponse> processDTHBillPayment(@PathVariable(value = "version") String version,
			@PathVariable(value = "role") String role, @PathVariable(value = "device") String device,
			@PathVariable(value = "language") String language, @RequestBody DTHBillPaymentRequest dto,
			@RequestHeader(value = "hash", required = false) String hash, HttpServletRequest request,
			HttpServletResponse response) {
		DTHBillPaymentResponse result = new DTHBillPaymentResponse();
		if (role.equalsIgnoreCase(Role.USER.getValue())) {
			if (device.equalsIgnoreCase(Device.ANDROID.getValue()) || device.equalsIgnoreCase(Device.WINDOWS.getValue())
					|| device.equalsIgnoreCase(Device.IOS.getValue())) {
				ValidationRequest validationRequest = ConvertUtil.convertDTHBillPaymentRequest(dto);
				ResponseDTO responseDTO = validationApi.validateTransaction(validationRequest);
				if(responseDTO.isSuccess()) {
					TransactionRequest newRequest = new TransactionRequest();
					newRequest.setSessionId(dto.getSessionId());
					newRequest.setAmount(dto.getAmount());
					newRequest.setTransactionRefNo("D");
					ResponseDTO responseDTO1 = transactionApi.validateTransaction(newRequest);
					if(responseDTO1.isValid()) {
						result = billPaymentApi.dthBill(dto);
//					sendCoupons(dto.getSessionId(), dto.getAmount());
					}else {
						result.setCode("F00");
						result.setMessage(responseDTO1.getMessage());
					}
				}else{
					result.setSuccess(false);
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
		return new ResponseEntity<DTHBillPaymentResponse>(result, HttpStatus.OK);
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
