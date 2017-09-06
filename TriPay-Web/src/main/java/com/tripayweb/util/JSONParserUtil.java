package com.tripayweb.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class JSONParserUtil {

	public static String getString(JSONObject object, String key) {
		String value = null;

		try {
			if (checkKey(object, key)) {
				value = (String) object.getString(key);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}
	public static Date getDate(JSONObject object, String key){
		Date value = null;
//
//		try {
//			if (checkKey(object, key)) {
////				value = (Date) object.ge(key);
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
		return value;

	}

	public static boolean getBoolean(JSONObject object, String key) {
		boolean value = false;

		try {
			if (checkKey(object, key)) {
				value = (boolean) object.getBoolean(key);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}

	
	public static double getDouble(JSONObject object, String key) {
		double value = 0;

		try {
			if (checkKey(object, key)) {
				value = (double) object.getDouble(key);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static long getLong(JSONObject object, String key) {
		long value = 0;

		try {
			if (checkKey(object, key)) {
				value = (long) object.getLong(key);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static JSONObject getObject(JSONObject object, String key) {
		JSONObject value = null;

		try {
			if (checkKey(object, key)) {
				value =  object.getJSONObject(key);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static JSONArray getArray(JSONObject object, String key) {
		JSONArray value = null;

		try {
			if (checkKey(object, key)) {
				value =  object.getJSONArray(key);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static int getInt(JSONObject object, String key) {
		int value = 0;
		try {
			value = (int) object.getInt(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static boolean checkKey(JSONObject object, String key) {
		return object.has(key);
	}

}
