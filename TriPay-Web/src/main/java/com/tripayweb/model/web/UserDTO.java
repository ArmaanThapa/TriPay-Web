package com.tripayweb.model.web;

import com.tripayweb.model.web.Status;
import com.tripayweb.model.web.UserType;

public class UserDTO {

	private String username;

	private String firstName;

	private String middleName;

	private String lastName;

	private String address;

	private String contactNo;

	private UserType userType;

	private String authority;

	private Status emailStatus;

	private Status mobileStatus;

	private String email;

	private String image;

	private String balance;

	private String accountType;

	private double dailyTransaction;

	private double monthlyTransaction;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Status getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(Status emailStatus) {
		this.emailStatus = emailStatus;
	}

	public Status getMobileStatus() {
		return mobileStatus;
	}

	public void setMobileStatus(Status mobileStatus) {
		this.mobileStatus = mobileStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getDailyTransaction() {
		return dailyTransaction;
	}

	public void setDailyTransaction(double dailyTransaction) {
		this.dailyTransaction = dailyTransaction;
	}

	public double getMonthlyTransaction() {
		return monthlyTransaction;
	}

	public void setMonthlyTransaction(double monthlyTransaction) {
		this.monthlyTransaction = monthlyTransaction;
	}

	 
}
