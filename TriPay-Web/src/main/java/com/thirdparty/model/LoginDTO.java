package com.thirdparty.model;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class LoginDTO extends AbstractDTO implements JSONWrapper{

    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try{
            json.put("username",getUsername());
            json.put("password",getPassword());
            return json;
        }catch(JSONException e){
            return null;
        }
    }
}
