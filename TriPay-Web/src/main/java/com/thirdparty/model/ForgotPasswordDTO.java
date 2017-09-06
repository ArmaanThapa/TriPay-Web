package com.thirdparty.model;


import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ForgotPasswordDTO implements JSONWrapper{
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("username",getUsername());
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
