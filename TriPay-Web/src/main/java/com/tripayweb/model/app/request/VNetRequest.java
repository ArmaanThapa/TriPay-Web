package com.tripayweb.model.app.request;

import com.thirdparty.model.JSONWrapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class VNetRequest implements JSONWrapper{

    private String amount;
    private String sessionId;
    private String returnURL;
    private String serviceCode;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getReturnURL() {
        return returnURL;
    }

    public void setReturnURL(String returnURL) {
        this.returnURL = returnURL;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("sessionId", getSessionId());
            json.put("amount", getAmount());
            json.put("serviceCode", getServiceCode());
            json.put("returnURL", getReturnURL());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
