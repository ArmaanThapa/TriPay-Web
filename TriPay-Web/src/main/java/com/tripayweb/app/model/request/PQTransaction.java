package com.tripayweb.app.model.request;

public class PQTransaction {

	private double amount;

	private boolean debit;

	private boolean favourite;

	private double currentBalance;

	private String description;

	private String date;
	
	private String TransactionRefNo;
	

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isDebit() {
		return debit;
	}

	public void setDebit(boolean debit) {
		this.debit = debit;
	}

	public boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTransactionRefNo() {
		return TransactionRefNo;
	}

	public void setTransactionRefNo(String transactionRefNo) {
		TransactionRefNo = transactionRefNo;
	}
	
}
