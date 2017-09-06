package com.tripayweb.model.error;

public class BusDetailsError {

	private boolean valid;
	private String source;
	private String destinaion;
	private String journeyDate;
	private String tripType;
	private String userType;
	private String returnDate;
	private String operatorId;
	private String busType;
	private String busTimings;
	
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestinaion() {
		return destinaion;
	}
	public void setDestinaion(String destinaion) {
		this.destinaion = destinaion;
	}
	public String getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}
	public String getTripType() {
		return tripType;
	}
	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getBusTimings() {
		return busTimings;
	}
	public void setBusTimings(String busTimings) {
		this.busTimings = busTimings;
	}
	
}
