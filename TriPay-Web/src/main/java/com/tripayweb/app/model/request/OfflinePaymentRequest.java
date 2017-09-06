package com.tripayweb.app.model.request;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.thirdparty.model.JSONWrapper;

public class OfflinePaymentRequest extends SessionDTO implements JSONWrapper{

	private String mobileNumber;
	private double amount;
	private String otp;
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
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
		try {
			json.put("sessionId", getSessionId());
			json.put("mobileNo", getMobileNumber());
			json.put("amount", getAmount());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
	
}
