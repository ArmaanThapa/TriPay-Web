package com.tripayweb.app.model.request;

import com.thirdparty.model.JSONWrapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class VNetRequest extends SessionDTO implements JSONWrapper{
    private String amount;
    private String returnURL;
    private final String serviceCode = "LMB";

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("sessionId",getSessionId());
            json.put("serviceCode",getServiceCode());
            json.put("amount",getAmount());
            json.put("returnURL",getReturnURL());
        }catch(JSONException ex){
            ex.printStackTrace();
        }
            return json;
    }
}
