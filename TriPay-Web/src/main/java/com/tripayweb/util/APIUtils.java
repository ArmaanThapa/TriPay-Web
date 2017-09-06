package com.tripayweb.util;

import org.json.JSONException;
import org.json.JSONObject;

public class APIUtils {

	public static final String HOST = "http://172.16.3.96:9037/";
	public static final String END_POINT = "validateVPayQwikUser";
	public static final String URL = HOST+END_POINT;
	public static final String CHANNEL_ID = "MOB";
	public static final String USERNAME = "vpayqwik";
	public static final String PASSWORD = "vijayavpayQwikvalidation";
	public static final String TRANSACTION_ID = "42144534";

	public static JSONObject getFailedJSON() {
		JSONObject object = new JSONObject();
		try {
			object.put("code", "F00");
			object.put("message", "Service unavailable");
			object.put("status", "FAILED");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object;
	}

	public static JSONObject getCustomJSON(String code,String message){
		JSONObject json = new JSONObject();
		try{
			json.put("status","FAILED");
			json.put("code",code);
			json.put("message",message);
			json.put("details",message);
		}catch(JSONException ex){
			ex.printStackTrace();
		}
		return json;
	}
	
	
	//Mera Events URL 
	public static final int Test_ClientID = 328688312;
	public static final String Test_ClientSecret = "85CSJkcDVpHl0M7PVdXJ";
	
	public static final int ClientID = 407478312;
	public static final String ClientSecret = "U1lmWo5A5At4APS8YkQQ";
	
	public static final String BASE_STAGE = "https://stage.meraevents.com/";
	public static final String BASE_PRODUCTION = "https://www.meraevents.com/";
	
	public static final String AUTH_CODE = "web/api/v1/authorize/authorizationCode";
	public static final String ACCESS_TOKEN = "web/api/v1/authorize/getAccessToken";
	public static final String EVENT_LIST = "web/api/v1/events";
	public static final String EVENT_DETAILS = "web/api/v1/event";
	public static final String TICKET_DETAILS = "web/api/v1/ticket";
	public static final String GALLERY_DETAILS = "web/api/v1/gallery";
	public static final String CATEGORIES = "web/api/v1/categories";
	public static final String CITIES = "web/api/v1/cities";
	public static final String TICKET_CALCULATION = "web/api/v1/event/ticketCalculation";
	public static final String INITIATE_BOOKING = "web/api/v1/booking";
	public static final String ATTENDEE_FORM = "web/api/v1/booking/getCustomFields";
	public static final String SAVE_ATTENDEE = "web/api/v1/booking/saveAttendeeData";
	public static final String OFFLINE_BOOKING = "web/api/v1/booking/offline";
	
}
