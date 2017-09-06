package com.thirdparty.model;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class StatusCheckDTO implements JSONWrapper{

    private String merchantRefNo;
    private String secretKey;
    private long merchantId;

    public String getMerchantRefNo() {
        return merchantRefNo;
    }

    public void setMerchantRefNo(String merchantRefNo) {
        this.merchantRefNo = merchantRefNo;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("merchantRefNo",getMerchantRefNo());
            jsonObject.put("secretKey",getSecretKey());
            jsonObject.put("merchantId",getMerchantId());
        }catch(JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;
    }
}
