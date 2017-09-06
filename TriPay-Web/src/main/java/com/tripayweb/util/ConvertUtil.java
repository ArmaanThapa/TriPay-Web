package com.tripayweb.util;

import com.instantpay.model.request.Format;
import com.instantpay.model.request.Mode;
import com.instantpay.model.request.ValidationRequest;
import com.instantpay.util.InstantPayConstants;
import com.tripayweb.app.model.MerchantDTO;
import com.tripayweb.app.model.request.*;
import com.tripayweb.app.model.response.EBSRedirectResponse;
import com.tripayweb.model.web.WEBSRedirectResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ConvertUtil {

	private ConvertUtil() {

	}

	public static EBSRedirectResponse convertFromWEBS(WEBSRedirectResponse response){
		EBSRedirectResponse redirectResponse = new EBSRedirectResponse();
		redirectResponse.setAccountId(response.getAccountId());
		redirectResponse.setAmount(response.getAmount());
		redirectResponse.setBillingAddress(response.getBillingAddress());
		redirectResponse.setBillingCity(response.getBillingCity());
		redirectResponse.setBillingCountry(response.getBillingCountry());
		redirectResponse.setBillingEmail(response.getBillingEmail());
		redirectResponse.setBillingName(response.getBillingName());
		redirectResponse.setBillingPostalCode(response.getBillingPostalCode());
		redirectResponse.setDateCreated(response.getDateCreated());
		redirectResponse.setMerchantRefNo(response.getMerchantRefNo());
		redirectResponse.setIsFlagged(response.getIsFlagged());
		redirectResponse.setMode(response.getMode());
		redirectResponse.setPaymentId(response.getPaymentId());
		redirectResponse.setDeliveryName(response.getDeliveryName());
		redirectResponse.setDeliveryAddress(response.getDeliveryAddress());
		redirectResponse.setDeliveryPhone(response.getDeliveryPhone());
		redirectResponse.setDeliveryPostalCode(response.getDeliveryPostalCode());
		return redirectResponse;
	}

//	public static List<UserSessionDTO> convertSessionList(List<UserSession> userSession) {
//		List<UserSessionDTO> dtoList = new ArrayList<UserSessionDTO>();
//		for (UserSession session : userSession) {
//			dtoList.add(convertSession(session));
//		}
//		return dtoList;
//	}

//	public static UserSessionDTO convertSession(UserSession session) {
//		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
//		UserSessionDTO dto = new UserSessionDTO();
//		dto.setUsername(session.getUser().getUsername());
//		dto.setId(session.getId());
//		dto.setSessionId(session.getSessionId());
//		dto.setUserId(session.getUser().getId());
//		dto.setUserType(session.getUser().getUserType());
//		dto.setLastRequest(time.format(session.getLastRequest()));
//		return dto;
//	}

//	public static List<TelcoOperatorDTO> convertTelcoOperatorList(List<TelcoOperator> telcoOperators) {
//		List<TelcoOperatorDTO> dtoList = new ArrayList<TelcoOperatorDTO>();
//		for (TelcoOperator telcoOperator : telcoOperators) {
//			dtoList.add(convertTelcoOperator(telcoOperator));
//		}
//		return dtoList;
//	}

//	public static TelcoOperatorDTO convertTelcoOperator(TelcoOperator telcoOperator) {
//		TelcoOperatorDTO dto = new TelcoOperatorDTO();
//		dto.setCode(telcoOperator.getCode());
//		dto.setServiceCode(telcoOperator.getServiceCode());
//		dto.setName(telcoOperator.getName());
//		return dto;
//	}

//	public static List<TelcoCircleDTO> convertTelcoCircleList(List<TelcoCircle> telcoCircles) {
//		List<TelcoCircleDTO> dtoList = new ArrayList<>();
//		for (TelcoCircle telcoCircle : telcoCircles) {
//			dtoList.add(convertTelcoCircle(telcoCircle));
//		}
//		return dtoList;
//
//	}

//	public static TelcoCircleDTO convertTelcoCircle(TelcoCircle telcoCircle) {
//		TelcoCircleDTO dto = new TelcoCircleDTO();
//		dto.setCode(telcoCircle.getCode());
//		dto.setName(telcoCircle.getName());
//		return dto;
//	}

//	public static List<TelcoPlansDTO> convertTelcoPlansList(List<TelcoPlans> telcoPlans) {
//		List<TelcoPlansDTO> dtoList = new ArrayList<>();
//		for (TelcoPlans telcoPlan : telcoPlans) {
//			dtoList.add(convertTelcoPlans(telcoPlan));
//		}
//		return dtoList;
//
//	}

//	public static TelcoPlansDTO convertTelcoPlans(TelcoPlans telcoPlans) {
//		TelcoPlansDTO dto = new TelcoPlansDTO();
//		dto.setAmount(telcoPlans.getAmount());
//		dto.setDescription(telcoPlans.getDescription());
//		dto.setOperatorCode(telcoPlans.getOperatorCode());
//		dto.setPlanName(telcoPlans.getPlanName());
//		dto.setPlanType(telcoPlans.getPlanType());
//		dto.setSmsDaakCode(telcoPlans.getSmsDaakCode());
//		dto.setState(telcoPlans.getState());
//		dto.setValidity(telcoPlans.getValidity());
//		return dto;
//	}

//	public static List<UserDTO> convertUserList(List<User> user) {
//		List<UserDTO> dtoList = new ArrayList<UserDTO>();
//		for (User u : user) {
//			dtoList.add(convertUser(u));
//		}
//		return dtoList;
//	}

//	public static UserDTO convertUser(User u) {
//		UserDTO dto = new UserDTO();
//		dto.setAddress((u.getUserDetail().getAddress() == null) ? "" : u.getUserDetail().getAddress());
//		dto.setContactNo(u.getUserDetail().getContactNo());
//		dto.setFirstName(u.getUserDetail().getFirstName());
//		dto.setMiddleName((u.getUserDetail().getMiddleName() == null) ? "" : u.getUserDetail().getMiddleName());
//		dto.setLastName(u.getUserDetail().getLastName());
//		dto.setEmail(u.getUserDetail().getEmail());
//		dto.setMobileStatus(u.getMobileStatus());
//		dto.setEmailStatus(u.getEmailStatus());
//		dto.setUserType(u.getUserType());
//		dto.setUsername(u.getUsername());
//		dto.setAuthority(u.getAuthority());
//		dto.setImage((u.getUserDetail().getImage()==null)?"":u.getUserDetail().getImage());
//		return dto;
//	}

//	public static RegisterDTO convertUser(MerchantDTO merchantDTO) {
//		RegisterDTO reg = new RegisterDTO();
//		reg.setAddress(merchantDTO.getAddress());
//		reg.setAuthority(merchantDTO.getAuthority());
//		reg.setPassword(merchantDTO.getMpassword());
//		reg.setConfirmPassword(merchantDTO.getMpassword());
//		reg.setContactNo(merchantDTO.getContactNo());
//		reg.setEmail(merchantDTO.getEmail());
//		reg.setFirstName(merchantDTO.getmName());
//		reg.setUsername(merchantDTO.getContactNo());
//		reg.setStatus(Status.Inactive);
//		reg.setUserType(UserType.Merchant);
//		reg.setLastName(merchantDTO.getmName());
//		return reg;
//	}

	public static ValidationRequest convertPrepaidTopupRequest(PrepaidTopupRequest request){
		ValidationRequest response = new ValidationRequest();
		response.setAmount(request.getAmount());
		response.setMode(Mode.VALIDATE);
		response.setAccount(request.getMobileNo());
		response.setAgentId(System.currentTimeMillis()+"");
		response.setFormat(Format.JSON);
		response.setSpKey(convertServiceProvider(request.getServiceProvider()));
		response.setToken(InstantPayConstants.TOKEN);
		if(request.getServiceProvider().equalsIgnoreCase("VATP")){
			response.setOptional5(InstantPayConstants.OUTLET_ID);
		}
		return response;
	}


	public static List<MerchantDTO> convertFromArray(JSONArray array){
		List<MerchantDTO> dtoList = new ArrayList<>();
		try {
			if (array != null) {
				for(int i=0 ; i < array.length() ; i++){
					JSONObject o = array.getJSONObject(i);
					dtoList.add(getFromJSON(o));
				}
			}
		}catch(Exception ex){

		}
		return dtoList;
	}

	public static MerchantDTO getFromJSON(JSONObject o) {
		MerchantDTO dto = new MerchantDTO();
		try {
			if (o != null){
				dto.setId(JSONParserUtil.getLong(o,"id"));
				dto.setName(JSONParserUtil.getString(o,"name"));
				dto.setImage(JSONParserUtil.getString(o,"image"));
				dto.setEmail(JSONParserUtil.getString(o,"email"));
				dto.setContactNo(JSONParserUtil.getString(o,"contactNo"));
			}
		}catch(NullPointerException ex){
			ex.printStackTrace();
		}
		return dto;
	}

	public static ValidationRequest convertPostpaidTopupRequest(PostpaidTopupRequest request){
		ValidationRequest response = new ValidationRequest();
		response.setAmount(request.getAmount());
		response.setMode(Mode.VALIDATE);
		response.setAccount(request.getMobileNo());
		response.setAgentId(System.currentTimeMillis()+"");
		response.setFormat(Format.JSON);
		response.setSpKey(convertServiceProvider(request.getServiceProvider()));
		response.setToken(InstantPayConstants.TOKEN);
		if(request.getServiceProvider().equalsIgnoreCase("VATP")){
			response.setOptional5(InstantPayConstants.OUTLET_ID);
		}
		return response;
	}

	public static ValidationRequest convertDataCardRequest(DataCardTopupRequest request){
		ValidationRequest response = new ValidationRequest();
		response.setAmount(request.getAmount());
		response.setMode(Mode.VALIDATE);
		response.setAccount(request.getMobileNo());
		response.setAgentId(System.currentTimeMillis()+"");
		response.setFormat(Format.JSON);
		response.setSpKey(convertServiceProvider(request.getServiceProvider()));
		response.setToken(InstantPayConstants.TOKEN);
		if(request.getServiceProvider().equalsIgnoreCase("VATP")){
			response.setOptional5(InstantPayConstants.OUTLET_ID);
		}
		return response;
	}

	public static ValidationRequest convertDTHBillPaymentRequest(DTHBillPaymentRequest request){
		ValidationRequest response = new ValidationRequest();
		response.setAmount(request.getAmount());
		response.setMode(Mode.VALIDATE);
		response.setAccount(request.getDthNo());
		response.setAgentId(System.currentTimeMillis()+"");
		response.setFormat(Format.JSON);
		response.setSpKey(convertServiceProvider(request.getServiceProvider()));
		response.setToken(InstantPayConstants.TOKEN);
		return response;
	}


	public static ValidationRequest convertLandlineBillPaymentRequest(LandlineBillPaymentRequest request){
		ValidationRequest response = new ValidationRequest();
		response.setAmount(request.getAmount());
		response.setMode(Mode.VALIDATE);
		response.setAccount(request.getLandlineNumber());
		response.setOptional1(request.getStdCode());
		if (request.getServiceProvider().equalsIgnoreCase("VBGL")) {
			response.setOptional2(request.getAccountNumber());
			response.setOptional3("LLI");
		}
		response.setAgentId(System.currentTimeMillis()+"");
		response.setFormat(Format.JSON);
		response.setSpKey(convertServiceProvider(request.getServiceProvider()));
		response.setToken(InstantPayConstants.TOKEN);
		return response;
	}

	public static ValidationRequest convertElectricityBillPaymentRequest(ElectricityBillPaymentRequest request){
		ValidationRequest response = new ValidationRequest();
		response.setAccount(request.getAccountNumber());
		response.setAmount(request.getAmount());
		response.setSpKey(convertServiceProvider(request.getServiceProvider()));
		if (request.getServiceProvider().equalsIgnoreCase("VREE")) {
			response.setOptional1(request.getCycleNumber());
		}
		response.setToken(InstantPayConstants.TOKEN);
		response.setAgentId(System.currentTimeMillis()+"");
		response.setFormat(Format.JSON);
		return response;
	}

	public static ValidationRequest convertGasBillPaymentRequest(GasBillPaymentRequest request){
		ValidationRequest response = new ValidationRequest();
		response.setAccount(request.getAccountNumber());
		response.setAmount(request.getAmount());
		response.setSpKey(convertServiceProvider(request.getServiceProvider()));
		response.setToken(InstantPayConstants.TOKEN);
		response.setAgentId(System.currentTimeMillis()+"");
		response.setFormat(Format.JSON);
		return response;
	}

	public static ValidationRequest convertInsuranceBillPaymentRequest(InsuranceBillPaymentRequest request){
		ValidationRequest response = new ValidationRequest();
		response.setAccount(request.getPolicyNumber());
		response.setAmount(request.getAmount());
		response.setSpKey(convertServiceProvider(request.getServiceProvider()));
		response.setOptional1(request.getPolicyDate());
		response.setToken(InstantPayConstants.TOKEN);
		response.setAgentId(System.currentTimeMillis()+"");
		response.setFormat(Format.JSON);
		return response;
	}


	public static String convertServiceProvider(String serviceProvider){
		return serviceProvider.substring(1);
	}
}
