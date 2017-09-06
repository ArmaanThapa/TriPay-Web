package com.tripayweb.api.constants;

public class APIConstants {

	public static interface URL {

//		public static final String BASE_URL="http://172.16.3.10:8089/";
//		public static final String BASE_URL="http://192.168.1.6:8080/VPayQwik-App/";
//		public static final String BASE_URL="http://fgmtest.firstglobalmoney.com:8035/PayQwikApp/";

//		public static final String BASE_URL="http://66.207.206.54:8034/";
		public static final String BASE_URL="http://172.16.3.10/"; // DON'T CHANGE IT
//		public static final String PROJECT_KEY = "";
		public static final String EBS_VERIFICATION_URL = "https://api.secure.ebs.in/api/1_0";
		public static final String LOGIN_URL = BASE_URL+"Api/v1/User/Windows/en/Login";
		public static final String ADMIN_LOGIN_URL = BASE_URL+"Api/v1/Admin/Windows/en/Login";
		public static final String MOBILE_LOGIN_URL = BASE_URL+"Api/v1/User/Mobile/en/Login";
		public static final String REGISTER_URL = BASE_URL+"Api/v1/User/Windows/en/Register";
		public static final String PREPAID_TOPUP_URL = BASE_URL+"Api/v1/User/Windows/en/MobileTopup/ProcessPrepaid";
		public static final String POSTPAID_TOPUP_URL = BASE_URL+"Api/v1/User/Windows/en/MobileTopup/ProcessPostpaid";
		public static final String DATACARD_TOPUP_URL = BASE_URL+"Api/v1/User/Windows/en/MobileTopup/ProcessDataCard";
		public static final String DTH_PAYMENT_URL = BASE_URL+"Api/v1/User/Windows/en/BillPay/ProcessDTH";
		public static final String LANDLINE_PAYMENT_URL = BASE_URL+"Api/v1/User/Windows/en/BillPay/ProcessLandline";
		public static final String ELECTRICITY_PAYMENT_URL = BASE_URL+"Api/v1/User/Windows/en/BillPay/ProcessElectricity";
		public static final String GAS_PAYMENT_URL = BASE_URL+"Api/v1/User/Windows/en/BillPay/ProcessGas";
		public static final String INSURANCE_PAYMENT_URL = BASE_URL+"Api/v1/User/Windows/en/BillPay/ProcessInsurance";
		public static final String SEND_MONEY_MOBILE_URL = BASE_URL+"Api/v1/User/Windows/en/SendMoney/Mobile";
		public static final String PAT_AT_STORE_URL = BASE_URL+"Api/v1/User/Windows/en/PayAtStore/Process";
		public static final String AUTHENTICATION_URL = BASE_URL+"Api/v1/User/Windows/en/Authenticate/SessionId";
		public static final String TELCO_URL = BASE_URL+"Api/v1/User/Windows/en/Plans/GetTelco";
		public static final String FORGOT_PASSWORD_REQUEST= BASE_URL+"Api/v1/User/Windows/en/ForgotPassword";
		public static final String EBS_VERIFICATION = "https://api.secure.ebs.in/api/1_0";
		public static final String DUMMY_HASH = "#(*@&(*#(#*&)@(&)$)#(&)@()@!()#&(#^($$(*$(*#(@*)(@*)(!)&#((#*(@*@)&##";
	}
}
