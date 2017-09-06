package com.tripayweb.app.model.request;

import com.thirdparty.model.JSONWrapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;

public class PromoCodeRequest extends SessionDTO implements JSONWrapper {

	/*
	{
	"sessionId":"AE96DCC1A45A702A46BA5349089E2ADF",
	"promoCode":"VPQDTH2",
	"terms" : "100" ,
	"description" : "Promo Coded Added for Testing",
	"startDate": "2016-10-03",
	"endDate": "2016-10-31",
	"value": 10,
	"fixed": false,
	"services":["VVTV","VSTV","VATV","VTTV"]
}
	 */
	private String promoCode;
	private String terms;
	private String startDate;
	private String endDate;
	private double value;
	private boolean fixed;
	private String status;
	private String description;
	private ArrayList<String> services;
	private long serviceTypeId;

	public long getServiceTypeId() {
		return serviceTypeId;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public void setServiceTypeId(long serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setServices(ArrayList<String> services) {
		this.services = services;
	}

	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<String> getServices() {
		return services;
	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try{
			json.put("sessionId",getSessionId());
			json.put("promoCode",getPromoCode());
			json.put("startDate",getStartDate());
			json.put("fixed",isFixed());
			json.put("endDate",getEndDate());
			json.put("value",getValue());
			json.put("description",getDescription());
			json.put("services",getServices());
			json.put("terms",getTerms());
		}catch(JSONException e){
			e.printStackTrace();
		}
		return json;
	}
}
