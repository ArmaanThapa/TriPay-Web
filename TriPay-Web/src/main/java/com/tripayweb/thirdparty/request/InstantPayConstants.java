package com.tripayweb.thirdparty.request;

public class InstantPayConstants {

	private static final String URL = "https://www.instantpay.in/ws/api/";
//	private static final String URL = "http://180.179.213.127/ws/api/";
	public static final String URL_TRANSACTION = URL + "transaction";
	public static final String URL_BALANCE = URL + "checkwallet";
	public static final String URL_STATUS = URL + "getMIS";
	public static final String URL_SERVICE = URL + "serviceproviders";
	public static final String URL_VALIDATION = URL + "transaction";

//	public static final String TOKEN = "936267b2a4cc1fef34d7396376ca21c0"; // 66.207.206.54 - MSEWA
//	public static final String TOKEN = "67af5efa626db09dc6771c596d8428b4"; // 172.16.3.10 - VIJAYA BANK APP
//	public static final String TOKEN = "5cb1d335e315f5bd99754e50ea2b5d59"; // 172.16.7.29 - VIJAYA BANK WEB
//	public static final String REQUEST_FORMAT = "json";
//	public static final String AGENT_ID = "565Y10837"; // MSEWA Dealer code
//	public static final String OUTLET_ID = "10019339"; // MSEWA Outlet ID for 66.207.206.54
	
	public static final String API_KEY_TOKEN = "token";
	public static final String API_KEY_MODE = "mode";
	public static final String API_KEY_SPKEY = "spkey";
	public static final String API_KEY_AGENTID = "agentid";
	public static final String API_KEY_ACCOUNT = "account";
	public static final String API_KEY_AMOUNT = "amount";
	public static final String API_KEY_OPTIONAL1 = "optional1";
	public static final String API_KEY_OPTIONAL2 = "optional2";
	public static final String API_KEY_OPTIONAL3 = "optional3";
	public static final String API_KEY_OPTIONAL4 = "optional4";
	public static final String API_KEY_OPTIONAL5 = "optional5";
	public static final String API_KEY_FORMAT = "format";
	public static final String API_KEY_TYPE = "type";

	public static final String USERNAME = "instantpay@payqwik.in";

}
