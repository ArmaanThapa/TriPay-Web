package com.tripayweb.model.app.request;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginRequest {
	
	private String userName;
	private String password;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public JSONObject toJSON() {
		try {
		JSONObject jobj = new JSONObject();
			jobj.put("userName", getUserName());
			jobj.put("password", getPassword());
			return jobj;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
