package com.tripayweb.controller.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.instantpay.api.IValidationApi;
import com.instantpay.model.request.ValidationRequest;
import com.tripayweb.app.api.ITransactionApi;
import com.tripayweb.app.model.request.*;
import com.tripayweb.app.model.response.*;
import com.tripayweb.model.web.CarrotFryDTO;
import com.tripayweb.util.APIUtils;
import com.tripayweb.util.ConvertUtil;
import com.thirdparty.model.ResponseDTO;
import org.json.JSONObject;
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
import com.tripayweb.app.api.IBillPaymentApi;
import com.tripayweb.app.model.Role;
import com.tripayweb.model.error.DTHBillPaymentError;
import com.tripayweb.model.error.ElectricityBillPaymentError;
import com.tripayweb.model.error.GasBillPaymentError;
import com.tripayweb.model.error.InsuranceBillPaymentError;
import com.tripayweb.model.error.LandlineBillPaymentError;
import com.tripayweb.util.Authorities;
import com.tripayweb.util.LogCat;
import com.tripayweb.validation.DTHBillPaymentValidation;
import com.tripayweb.validation.ElectricityBillPaymentValidation;
import com.tripayweb.validation.GasBillPaymentValidation;
import com.tripayweb.validation.InsuranceBillPaymentValidation;
import com.tripayweb.validation.LandlineBillPaymentValidation;

import static com.tripayweb.util.ConvertUtil.convertDTHBillPaymentRequest;

@Controller
@RequestMapping("/User/BillPayment")
public class BillPaymentController implements MessageSourceAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageSource messageSource;

	private final IBillPaymentApi billPaymentApi;
	private final IAuthenticationApi authenticationApi;
	private DTHBillPaymentValidation dTHBillPaymentValidation;
	private LandlineBillPaymentValidation landlineBillPaymentValidation;
	private ElectricityBillPaymentValidation electricityBillPaymentValidation;
	private GasBillPaymentValidation gasBillPaymentValidation;
	private InsuranceBillPaymentValidation insuranceBillPaymentValidation;
	private final ICouponApi couponApi;
	private final IValidationApi validationApi;
	private final ITransactionApi transactionApi;
	public BillPaymentController(IBillPaymentApi billPaymentApi, IAuthenticationApi authenticationApi,
			DTHBillPaymentValidation dTHBillPaymentValidation,
			LandlineBillPaymentValidation landlineBillPaymentValidation,
			ElectricityBillPaymentValidation electricityBillPaymentValidation,
			GasBillPaymentValidation gasBillPaymentValidation,
			InsuranceBillPaymentValidation insuranceBillPaymentValidation, ICouponApi couponApi,IValidationApi validationApi,ITransactionApi transactionApi) {
		this.billPaymentApi = billPaymentApi;
		this.authenticationApi = authenticationApi;
		this.dTHBillPaymentValidation = dTHBillPaymentValidation;
		this.landlineBillPaymentValidation = landlineBillPaymentValidation;
		this.electricityBillPaymentValidation = electricityBillPaymentValidation;
		this.gasBillPaymentValidation = gasBillPaymentValidation;
		this.insuranceBillPaymentValidation = insuranceBillPaymentValidation;
		this.couponApi = couponApi;
		this.validationApi = validationApi;
		this.transactionApi = transactionApi;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/ProcessDTH", method = RequestMethod.POST)
	ResponseEntity<DTHBillPaymentResponse> dthBill(@ModelAttribute("paydth") DTHBillPaymentRequest dto,
			HttpServletRequest request, ModelMap modelMap, HttpServletResponse response, HttpSession session) {
		LogCat.print("Process DTH");
		DTHBillPaymentResponse result = new DTHBillPaymentResponse();
		String sessionId = (String) session.getAttribute("sessionId");
		DTHBillPaymentError error = dTHBillPaymentValidation.checkError(dto);
		if (error.isValid()) {
			if (sessionId != null && sessionId.length() != 0) {
				String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
				if (authority != null) {
					if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
						dto.setSessionId((String) session.getAttribute("sessionId"));
						ValidationRequest validationRequest = ConvertUtil.convertDTHBillPaymentRequest(dto);
						ResponseDTO responseDTO = validationApi.validateTransaction(validationRequest);
						if(responseDTO.isSuccess()) {
							TransactionRequest transactionRequest = new TransactionRequest();
							transactionRequest.setSessionId(dto.getSessionId());
							transactionRequest.setAmount(dto.getAmount());
							transactionRequest.setTransactionRefNo("D");
							ResponseDTO responseDTO1 = transactionApi.validateTransaction(transactionRequest);
							if(responseDTO1.isValid()) {
								result = billPaymentApi.dthBill(dto);
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
						return new ResponseEntity<DTHBillPaymentResponse>(result, HttpStatus.OK);
					}
				}
			}
		} else {
			result.setCode("F00");
			result.setMessage("Bad Request");
		}
		return new ResponseEntity<DTHBillPaymentResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/ProcessLandline", method = RequestMethod.POST)
	ResponseEntity<LandlineBillPaymentResponse> landline(@ModelAttribute("paylandline") LandlineBillPaymentRequest dto,
			HttpServletRequest request, ModelMap modelMap, HttpServletResponse response, HttpSession session) {
		LogCat.print("Process Landline ::"+dto);
		LandlineBillPaymentResponse result = new LandlineBillPaymentResponse();
		String sessionId = (String) session.getAttribute("sessionId");

		LandlineBillPaymentError error = landlineBillPaymentValidation.checkError(dto);
		if (error.isValid()) {
			if (sessionId != null && sessionId.length() != 0) {
				String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
				if (authority != null) {
					if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
						dto.setSessionId(sessionId);
						ValidationRequest validationRequest = ConvertUtil.convertLandlineBillPaymentRequest(dto);
						ResponseDTO responseDTO = validationApi.validateTransaction(validationRequest);
						if(responseDTO.isSuccess()) {
							TransactionRequest transactionRequest = new TransactionRequest();
							transactionRequest.setSessionId(dto.getSessionId());
							transactionRequest.setAmount(dto.getAmount());
							transactionRequest.setTransactionRefNo("D");
							ResponseDTO responseDTO1 = transactionApi.validateTransaction(transactionRequest);
							if(responseDTO1.isValid()) {
								result = billPaymentApi.landline(dto);
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
						return new ResponseEntity<LandlineBillPaymentResponse>(result, HttpStatus.OK);
					}
				}
			}
		} else {
			result.setCode("F00");
			result.setMessage("Bad Request");
		}
		return new ResponseEntity<LandlineBillPaymentResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/ProcessElectricity", method = RequestMethod.POST)
	ResponseEntity<ElectricityBillPaymentResponse> electricBill(
			@ModelAttribute("payelectricity") ElectricityBillPaymentRequest electricity, HttpServletRequest request,
			ModelMap modelMap, HttpServletResponse response, HttpSession session) {
		LogCat.print("Process Electricity");
		ElectricityBillPaymentResponse result = new ElectricityBillPaymentResponse();
		String sessionId = (String) session.getAttribute("sessionId");
		ElectricityBillPaymentError error = electricityBillPaymentValidation.checkError(electricity);
		if (true) {
			if (sessionId != null && sessionId.length() != 0) {
				String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
				if (authority != null) {
					if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
						electricity.setSessionId(sessionId);
						ValidationRequest validationRequest = ConvertUtil.convertElectricityBillPaymentRequest(electricity);
						ResponseDTO responseDTO = validationApi.validateTransaction(validationRequest);
						if(responseDTO.isSuccess()) {
							TransactionRequest transactionRequest = new TransactionRequest();
							transactionRequest.setSessionId(electricity.getSessionId());
							transactionRequest.setAmount(electricity.getAmount());
							transactionRequest.setTransactionRefNo("D");
							ResponseDTO responseDTO1 = transactionApi.validateTransaction(transactionRequest);
							if(responseDTO1.isValid()) {
								result = billPaymentApi.electricBill(electricity);
								CarrotFryDTO carrotFryDTO = new CarrotFryDTO();
								carrotFryDTO.setAmount(electricity.getAmount());
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
						return new ResponseEntity<ElectricityBillPaymentResponse>(result, HttpStatus.OK);
					}
				}
			}
		} else {
			result.setCode("F00");
			result.setMessage("Bad Request");
		}
		return new ResponseEntity<ElectricityBillPaymentResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/ProcessGas", method = RequestMethod.POST)
	ResponseEntity<GasBillPaymentResponse> gasBill(@ModelAttribute("paygas") GasBillPaymentRequest gas,
			HttpServletRequest request, ModelMap modelMap, HttpServletResponse response, HttpSession session) {
		LogCat.print("Process Gas");
		GasBillPaymentResponse result = new GasBillPaymentResponse();
		String sessionId = (String) session.getAttribute("sessionId");
		GasBillPaymentError error = gasBillPaymentValidation.checkError(gas);
		if (error.isValid()) {
			if (sessionId != null && sessionId.length() != 0) {
				String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
				if (authority != null) {
					if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
						gas.setSessionId(sessionId);
						ValidationRequest validationRequest = ConvertUtil.convertGasBillPaymentRequest(gas);
						ResponseDTO responseDTO = validationApi.validateTransaction(validationRequest);
						if(responseDTO.isSuccess()) {
							TransactionRequest transactionRequest = new TransactionRequest();
							transactionRequest.setSessionId(gas.getSessionId());
							transactionRequest.setAmount(gas.getAmount());
							transactionRequest.setTransactionRefNo("D");
							ResponseDTO responseDTO1 = transactionApi.validateTransaction(transactionRequest);
							if(responseDTO1.isValid()) {

								result = billPaymentApi.gasBill(gas);
								CarrotFryDTO carrotFryDTO = new CarrotFryDTO();
								carrotFryDTO.setAmount(gas.getAmount());
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
						return new ResponseEntity<GasBillPaymentResponse>(result, HttpStatus.OK);
					}
				}
			}
		} else {
			result.setCode("F00");
			result.setMessage("Bad Request");
		}
		return new ResponseEntity<GasBillPaymentResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/ProcessInsurance", method = RequestMethod.POST)
	ResponseEntity<InsuranceBillPaymentResponse> insuranceBill(
			@ModelAttribute("payinsurance") InsuranceBillPaymentRequest insurance, HttpServletRequest request,
			ModelMap modelMap, HttpServletResponse response, HttpSession session) {
		LogCat.print("Process Insurance");
		String sessionId = (String) session.getAttribute("sessionId");
		InsuranceBillPaymentResponse result = new InsuranceBillPaymentResponse();
		InsuranceBillPaymentError error = insuranceBillPaymentValidation.checkError(insurance);
		if (error.isValid()) {
			if (sessionId != null && sessionId.length() != 0) {
				String authority = authenticationApi.getAuthorityFromSession(sessionId, Role.USER);
				if (authority != null) {
					if (authority.contains(Authorities.USER) && authority.contains(Authorities.AUTHENTICATED)) {
						insurance.setSessionId(sessionId);
						ValidationRequest validationRequest = ConvertUtil.convertInsuranceBillPaymentRequest(insurance);
						ResponseDTO responseDTO = validationApi.validateTransaction(validationRequest);
						if(responseDTO.isSuccess()){
							TransactionRequest transactionRequest = new TransactionRequest();
							transactionRequest.setSessionId(insurance.getSessionId());
							transactionRequest.setAmount(insurance.getAmount());
							transactionRequest.setTransactionRefNo("D");
							ResponseDTO responseDTO1 = transactionApi.validateTransaction(transactionRequest);
							if(responseDTO1.isValid()) {
								result = billPaymentApi.insuranceBill(insurance);
								CarrotFryDTO carrotFryDTO = new CarrotFryDTO();
								carrotFryDTO.setAmount(insurance.getAmount());
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
						return new ResponseEntity<InsuranceBillPaymentResponse>(result, HttpStatus.OK);
					}
				}
			}
		} else {
			result.setCode("F00");
			result.setMessage("Bad Request");
		}
		return new ResponseEntity<InsuranceBillPaymentResponse>(result, HttpStatus.OK);
	}
	
}
