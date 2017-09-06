package com.tripayweb.model.web;

import java.util.List;

import com.tripayweb.app.model.request.AdminUserDetails;
import com.tripayweb.app.model.request.PQTransaction;

public class TransactionReportDTO {

	/*
	 * private List<PQTransaction> transactions;
	 * 
	 * public List<PQTransaction> getTransactions() { return transactions; }
	 * 
	 * public void setTransactions(List<PQTransaction> transactions) {
	 * this.transactions = transactions; }
	 */

	private List<AdminUserDetails> transactions;

	public List<AdminUserDetails> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<AdminUserDetails> transactions) {
		this.transactions = transactions;
	}

}