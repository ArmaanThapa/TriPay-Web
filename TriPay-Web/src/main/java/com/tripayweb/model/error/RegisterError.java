package com.tripayweb.model.error;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class RegisterError {
	private boolean valid;
	
	private String firstName;

	private String middleName;

	private String lastName;

	private String password;

	private String confirmPassword;

	private String email;
	
	private String pincode;

	private String contactNo;
	
	private String captchaResponse;

	private String ipAddress;
	private String minAmount;
	private String maxAmount;
	private String value;
	private String failureURL;
	private String successURL;

	public String getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(String minAmount) {
		this.minAmount = minAmount;
	}

	public String getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getFailureURL() {
		return failureURL;
	}

	public void setFailureURL(String failureURL) {
		this.failureURL = failureURL;
	}

	public String getSuccessURL() {
		return successURL;
	}

	public void setSuccessURL(String successURL) {
		this.successURL = successURL;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCaptchaResponse() {
		return captchaResponse;
	}

	public void setCaptchaResponse(String captchaResponse) {
		this.captchaResponse = captchaResponse;
	}

	@Override
	public String toString() {
		return "RegisterError [valid=" + valid + ", firstName=" + getFirstName() + ", middleName=" + getMiddleName()
				+ ", lastName=" + getLastName() + ", password=" + getPassword() + ", confirmPassword=" + getConfirmPassword()
				+ ", email=" + getEmail() + ", contactNo=" + getContactNo() + "]";
	}

	public JSONObject toJSON(){
		JSONObject jsonObject = new JSONObject();
		try{
			jsonObject.put("valid",valid);
			jsonObject.put("firstName",firstName);
			jsonObject.put("lastName",lastName);
			jsonObject.put("middleName",middleName);
			jsonObject.put("password",password);
			jsonObject.put("confirmPassword",confirmPassword);
			jsonObject.put("email",email);
			jsonObject.put("contactNo",contactNo);
			return jsonObject;
		}catch(JSONException ex){
				ex.printStackTrace();
				return jsonObject;
		}
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	
	

}
