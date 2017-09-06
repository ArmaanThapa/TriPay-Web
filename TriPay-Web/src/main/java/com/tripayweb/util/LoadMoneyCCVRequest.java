package com.tripayweb.util;

import com.ccavenue.security.AesCryptUtil;

public class LoadMoneyCCVRequest {
	
		public static final String LOAD_MONEY_REQUEST	= "";

		public static void main(String...a){
			System.out.println(LoadMoneyCCVRequest.request("4573906234956398", "9535418692", 10.0));
			
		}
		public static String request(String transactionId, String mobile, Double amount){
		  String key = "promo_code=&integration_type=iframe_normal&billing_state=Tamilnadu&order_id="
				    + transactionId + "&delivery_name=PayQwik&billing_email=care@payqwik.in&customer_identifier="
				    + mobile + "&currency=INR&delivery_state=Tamilnadu&delivery_tel="
				    + mobile + "&amount=" + amount
				    + "&billing_country=India&delivery_country=India&" + "&billing_tel=" + mobile
				    + "&delivery_city=Chennai&merchant_id=47281&redirect_url=https://service.transactionanalysts.com:449/PAYQWIK_API/CCWeb.aspx&cancel_url=https://service.transactionanalysts.com:449/PAYQWIK_API/CCWeb.aspx&billing_name=PayQwik&delivery_address=India Land Building&delivery_zip=600058&billing_zip=600058&sessionId=&billing_city=Chennai&merchant_param1="
				    + mobile + "&language=EN&merchant_param5=&mobileNumber=" + mobile
				    + "&merchant_param4=" + mobile+"@gmail.com" + "&merchant_param3="
				      + "&merchant_param2=" + "&billing_address=India Land Building";
		  
		  		AesCryptUtil aes = new AesCryptUtil("612606343A883D9197BA54AF0197E3D9");
		  		String encRequest = aes.encrypt(key);
		  return encRequest;
	  }
}
