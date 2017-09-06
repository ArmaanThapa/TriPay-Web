package com.tripayweb.model.error;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.thirdparty.model.JSONWrapper;

public class OfflinePaymentError implements JSONWrapper{

	private boolean valid;
	private String mobileNumber;
	private String amount;
	private String otp;
	
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}

	 @Override
	    public JSONObject toJSON() {
	        JSONObject json = new JSONObject();
	        try{
	            json.put("valid",isValid());
	            json.put("mobileNumber",getMobileNumber());
	            json.put("amount",getAmount());
	            
	        }catch(JSONException ex){
	            ex.printStackTrace();
	        }
	        return json;
	    }
	
}
