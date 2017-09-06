package com.tripayweb.app.model.request;

import com.thirdparty.model.JSONWrapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class KycVerificationDTO extends SessionDTO implements JSONWrapper{

    private String accountNumber;
    private String mobileNumber;
    private String otp;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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
            json.put("sessionId",getSessionId());
            json.put("accountNumber",getAccountNumber());
            json.put("mobileNumber",getMobileNumber());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
