package com.tripayweb.app.model.request;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class VersionRequest {
    private String versionCode;
    private String subVersionCode;

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getSubVersionCode() {
        return subVersionCode;
    }

    public void setSubVersionCode(String subVersionCode) {
        this.subVersionCode = subVersionCode;
    }

    public JSONObject toJSON(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("versionCode",versionCode);
            jsonObject.put("subVersionCode",subVersionCode);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
