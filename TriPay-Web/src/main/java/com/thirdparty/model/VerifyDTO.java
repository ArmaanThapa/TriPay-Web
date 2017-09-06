package com.thirdparty.model;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class VerifyDTO implements JSONWrapper{
    private String contactNo;
    private String otp;

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "VerifyDTO{" +
                "contactNo='" + contactNo + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("mobileNumber", getContactNo());
            json.put("key", getOtp());
            return json;
        }catch(JSONException e){
            return null;
        }
    }

}
