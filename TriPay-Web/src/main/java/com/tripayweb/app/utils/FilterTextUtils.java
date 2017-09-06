package com.tripayweb.app.utils;

import org.apache.commons.lang.StringEscapeUtils;

import com.tripayweb.app.model.request.AllTransactionRequest;
import com.tripayweb.app.model.request.AllUserRequest;
import com.tripayweb.app.model.request.BlockUnBlockUserRequest;
import com.tripayweb.app.model.request.BlockUserRequest;
import com.tripayweb.app.model.request.BrowsePlansRequest;
import com.tripayweb.app.model.request.ChangeMPINRequest;
import com.tripayweb.app.model.request.ChangePasswordRequest;
import com.tripayweb.app.model.request.ChangePasswordWithOtpRequest;
import com.tripayweb.app.model.request.DTHBillPaymentRequest;
import com.tripayweb.app.model.request.DataCardTopupRequest;
import com.tripayweb.app.model.request.DeleteMPINRequest;
import com.tripayweb.app.model.request.EditProfileRequest;
import com.tripayweb.app.model.request.ElectricityBillPaymentRequest;
import com.tripayweb.app.model.request.EmailLogRequest;
import com.tripayweb.app.model.request.ForgetPasswordUserRequest;
import com.tripayweb.app.model.request.GasBillPaymentRequest;
import com.tripayweb.app.model.request.GetOperatorAndCircleForMobRequest;
import com.tripayweb.app.model.request.RegistrationRequest;

public class FilterTextUtils {
	
	
	public static RegistrationRequest escapeUnwantedCharacters(RegistrationRequest dto){
		RegistrationRequest ndto = new RegistrationRequest();
		ndto.setFirstName(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getFirstName()))));
		ndto.setLastName(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getLastName()))));
		ndto.setContactNo(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getContactNo()))));
		ndto.setEmail(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getEmail()))));
//		ndto.setDateOfBirth(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getDateOfBirth()))));
		ndto.setGender(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getGender()))));
		return ndto;
	}
	
	public static void main(String[] args){
		String sql = "select * from User;";
		String javascript = " <script>Welcome</script>"; 
		System.out.println(StringEscapeUtils.escapeSql(sql));
		System.out.println(StringEscapeUtils.escapeJavaScript(javascript));
	}
	
	public static AllTransactionRequest escapeUnwantedCharacters(AllTransactionRequest dto){
		AllTransactionRequest ndto = new AllTransactionRequest();
//		ndto.setEndDate(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getEndDate()))));
//		ndto.setStartDate(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getStartDate()))));
		ndto.setPage(Integer.parseInt(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(String.valueOf(dto.getPage()))))));
		ndto.setSize(Integer.parseInt(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(String.valueOf(dto.getSize()))))));
		ndto.setSessionId(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getSessionId()))));
		return ndto;
	}
	
	public static AllUserRequest escapeUnwantedCharacters(AllUserRequest dto){
		AllUserRequest ndto = new AllUserRequest();
		ndto.setSessionId(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getSessionId()))));
		ndto.setMonthlyTransaction(Integer.parseInt(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(String.valueOf(dto.getMonthlyTransaction()))))));
		ndto.setTodayTransaction(Integer.parseInt(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(String.valueOf(dto.getTodayTransaction()))))));
		ndto.setOnlineUser(Integer.parseInt(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(String.valueOf(dto.getOnlineUser()))))));
		ndto.setPage(Integer.parseInt(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(String.valueOf(dto.getPage()))))));
		ndto.setSize(Integer.parseInt(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(String.valueOf(dto.getSize()))))));
		ndto.setTotalFemale(Integer.parseInt(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(String.valueOf(dto.getTotalFemale()))))));
		ndto.setTotalMale(Integer.parseInt(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(String.valueOf(dto.getTotalMale()))))));
		ndto.setTotalUser(Integer.parseInt(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(String.valueOf(dto.getTotalUser()))))));
		return ndto;
	}
	
	public static BlockUnBlockUserRequest escapeUnwantedCharacters(BlockUnBlockUserRequest dto){
		BlockUnBlockUserRequest ndto = new BlockUnBlockUserRequest();
		ndto.setSessionId(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getSessionId()))));
		ndto.setUsername(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getUsername()))));				
		return ndto;
	}
	
	public static BlockUserRequest escapeUnwantedCharacters(BlockUserRequest dto){
		BlockUserRequest ndto = new BlockUserRequest();
		ndto.setAdminSessionId(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getAdminSessionId()))));
		ndto.setUsername(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getUsername()))));						
		return ndto;
	}
	public static BrowsePlansRequest escapeUnwantedCharacters(BrowsePlansRequest dto){
		BrowsePlansRequest ndto  =  new BrowsePlansRequest();
		ndto.setSessionId(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getSessionId()))));
		ndto.setCircleCode(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getCircleCode()))));
		ndto.setOperatorCode(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getOperatorCode()))));
		return ndto;
	}
	
	public static ChangeMPINRequest escapeUnwantedCharacters(ChangeMPINRequest dto){
		ChangeMPINRequest ndto =  new ChangeMPINRequest();
		ndto.setSessionId(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getSessionId()))));
		ndto.setUsername(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getUsername()))));
		ndto.setOldMpin(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getOldMpin()))));
		ndto.setNewMpin(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getNewMpin()))));
		ndto.setConfirmMpin(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getConfirmMpin()))));
		return ndto;
	}
	
	public static ChangePasswordRequest escapeUnwantedCharacters(ChangePasswordRequest dto){
		ChangePasswordRequest ndto =  new ChangePasswordRequest();
		ndto.setSessionId(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getSessionId()))));
		ndto.setUsername(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getUsername()))));		
		return ndto;
	}
	
	public static ChangePasswordWithOtpRequest escapeUnwantedCharacters(ChangePasswordWithOtpRequest dto){
		ChangePasswordWithOtpRequest ndto = new ChangePasswordWithOtpRequest();
		ndto.setUsername(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getUsername()))));		
		ndto.setKey(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getKey()))));		
		return ndto;
	}
	
	public static DataCardTopupRequest escapeUnwantedCharacters(DataCardTopupRequest dto){
		DataCardTopupRequest ndto =  new DataCardTopupRequest();
		ndto.setSessionId(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getSessionId()))));
		ndto.setAmount(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getAmount()))));
		ndto.setArea(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getArea()))));
		ndto.setTopupType(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getTopupType()))));
		ndto.setServiceProvider(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getServiceProvider()))));
		ndto.setMobileNo(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getMobileNo()))));
		return ndto;
	}
	
	public static DeleteMPINRequest escapeUnwantedCharacters(DeleteMPINRequest dto){
		DeleteMPINRequest ndto = new DeleteMPINRequest();
		ndto.setSessionId(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getSessionId()))));
		ndto.setUsername(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getUsername()))));		
		ndto.setMpin(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getMpin()))));		
		return ndto;
	}
	
	public static DTHBillPaymentRequest escapeUnwantedCharacters(DTHBillPaymentRequest dto){
		DTHBillPaymentRequest ndto =  new DTHBillPaymentRequest();
		ndto.setSessionId(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getSessionId()))));
		ndto.setAmount(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getAmount()))));
		ndto.setServiceProvider(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getServiceProvider()))));
		ndto.setDthNo(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getDthNo()))));
		return ndto;
	}
	
	public static EditProfileRequest escapeUnwantedCharacters(EditProfileRequest dto){
		EditProfileRequest ndto =  new EditProfileRequest();
		ndto.setSessionId(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getSessionId()))));
		ndto.setAddress(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getAddress()))));
		ndto.setEmail(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getEmail()))));
		ndto.setFirstName(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getFirstName()))));
		ndto.setSessionId(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getLastName()))));
		return ndto;
	}
	
	public static ElectricityBillPaymentRequest escapeUnwantedCharacters(ElectricityBillPaymentRequest dto){
		ElectricityBillPaymentRequest ndto = new ElectricityBillPaymentRequest();
		ndto.setSessionId(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getSessionId()))));
		ndto.setAmount(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getAmount()))));
		ndto.setServiceProvider(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getServiceProvider()))));
		ndto.setAccountNumber(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getAccountNumber()))));
		ndto.setCycleNumber(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getCycleNumber()))));
		return ndto;
	}
	
	public static EmailLogRequest escapeUnwantedCharacters(EmailLogRequest dto){
		EmailLogRequest ndto = new EmailLogRequest();
//		ndto.setEndDate(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getEndDate()))));
//		ndto.setStartDate(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getStartDate()))));
		ndto.setPage(Integer.parseInt(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(String.valueOf(dto.getPage()))))));
		ndto.setSize(Integer.parseInt(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(String.valueOf(dto.getSize()))))));
		ndto.setSessionId(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getSessionId()))));
		return ndto;
	}
	
	public static ForgetPasswordUserRequest escapeUnwantedCharacters(ForgetPasswordUserRequest dto){
		ForgetPasswordUserRequest ndto = new ForgetPasswordUserRequest();
		ndto.setUsername(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getUsername()))));		
		return ndto;
	}
	
	public static GasBillPaymentRequest escapeUnwantedCharacters(GasBillPaymentRequest dto){
		GasBillPaymentRequest ndto = new GasBillPaymentRequest();
		ndto.setSessionId(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getSessionId()))));
		ndto.setAmount(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getAmount()))));
		ndto.setServiceProvider(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getServiceProvider()))));
		ndto.setAccountNumber(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getAccountNumber()))));
		return ndto;
	}
	
	public static GetOperatorAndCircleForMobRequest escapeUnwantedCharacters(GetOperatorAndCircleForMobRequest dto){
		GetOperatorAndCircleForMobRequest ndto = new GetOperatorAndCircleForMobRequest();
		ndto.setSessionId(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getSessionId()))));
		ndto.setMobileNumber(StringEscapeUtils.escapeHtml(StringEscapeUtils.escapeJavaScript(StringEscapeUtils.escapeSql(dto.getMobileNumber()))));
		return ndto;
	}
	
	 

}
