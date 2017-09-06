package com.tripayweb.model.app.response;

import com.tripayweb.model.web.Status;

public class MTransactionResponseDTO {

	private long id;
	private String date;
	private String transactionRefNo;
	private double amount;
	private double currentBalance;
	private boolean debit;
	private String description;
	private String contactNo;
	private String email;
	private Status status;
	
	public long getId() {
	return id;
	}
	public void setId(long id) {
	this.id = id;
	}
	public String getDate() {
	return date;
	}
	public void setDate(String date) {
	this.date = date;
	}
	public String getTransactionRefNo() {
	return transactionRefNo;
	}
	public void setTransactionRefNo(String transactionRefNo) {
	this.transactionRefNo = transactionRefNo;
	}
	public double getAmount() {
	return amount;
	}
	public void setAmount(double amount) {
	this.amount = amount;
	}
	public double getCurrentBalance() {
	return currentBalance;
	}
	public void setCurrentBalance(double currentBalance) {
	this.currentBalance = currentBalance;
	}
	public boolean isDebit() {
	return debit;
	}
	public void setDebit(boolean debit) {
	this.debit = debit;
	}
	public String getDescription() {
	return description;
	}
	public void setDescription(String description) {
	this.description = description;
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
	
	public Status getStatus() {
	return status;
	}
	public void setStatus(Status status) {
	this.status = status;
	}
}
