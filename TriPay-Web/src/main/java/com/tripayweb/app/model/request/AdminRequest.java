package com.tripayweb.app.model.request;

import org.json.JSONArray;

public class AdminRequest {

	private int totalUser;
	private int onlineUser;
	private int todayTransaction;
	private int monthlyTransaction;
	private int totalMale;
	private int totalFemale;


	private JSONArray jsonArray;

	public int getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(int totalUser) {
		this.totalUser = totalUser;
	}

	public int getOnlineUser() {
		return onlineUser;
	}

	public void setOnlineUser(int onlineUser) {
		this.onlineUser = onlineUser;
	}

	public int getTodayTransaction() {
		return todayTransaction;
	}

	public void setTodayTransaction(int todayTransaction) {
		this.todayTransaction = todayTransaction;
	}

	public int getMonthlyTransaction() {
		return monthlyTransaction;
	}

	public void setMonthlyTransaction(int monthlyTransaction) {
		this.monthlyTransaction = monthlyTransaction;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public int getTotalMale() {
		return totalMale;
	}

	public void setTotalMale(int totalMale) {
		this.totalMale = totalMale;
	}

	public int getTotalFemale() {
		return totalFemale;
	}

	public void setTotalFemale(int totalFemale) {
		this.totalFemale = totalFemale;
	}
	
}
