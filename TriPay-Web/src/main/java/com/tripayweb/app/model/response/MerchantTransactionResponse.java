package com.tripayweb.app.model.response;

public class MerchantTransactionResponse {
	private boolean success;
	private String code;
	private String balance;
	private String username;
	private String email;
	private String contactNo;
	private String authority;
	private String transactionRefNo;
	private double currentBalance;
	private long dateOfTransaction;
	private String serviceType;
	private boolean debit;
	private double amount;
	private String description;
	private String message;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getTransactionRefNo() {
		return transactionRefNo;
	}
	public void setTransactionRefNo(String transactionRefNo) {
		this.transactionRefNo = transactionRefNo;
	}
	public double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	public long getDateOfTransaction() {
		return dateOfTransaction;
	}
	public void setDateOfTransaction(long dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public boolean isDebit() {
		return debit;
	}
	public void setDebit(boolean debit) {
		this.debit = debit;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "MerchantTransactionResponse [success=" + success + ", code=" + code + ", balance=" + balance
				+ ", username=" + username + ", email=" + email + ", contactNo=" + contactNo + ", authority="
				+ authority + ", transactionRefNo=" + transactionRefNo + ", currentBalance=" + currentBalance
				+ ", dateOfTransaction=" + dateOfTransaction + ", serviceType=" + serviceType + ", debit=" + debit
				+ ", amount=" + amount + ", description=" + description + ", message=" + message + ", status=" + status
				+ "]";
	}
	
	
	

}
