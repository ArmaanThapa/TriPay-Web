package com.thirdparty.model;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class AuthenticateDTO extends AbstractDTO implements JSONWrapper{
    private String ipAddress;
    private String amount;
    private String hash;
    private String additionalInfo;

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("transactionID",getTransactionID());
            jsonObject.put("ipAddress",getIpAddress());
            jsonObject.put("id",getId());
            jsonObject.put("hash",getHash());
            jsonObject.put("amount",getAmount());
            return jsonObject;
        } catch (JSONException e) {
            return null;
        }

    }
}
