package com.tripayweb.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.api.IUserApi;

public class TransactionValidation {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final IUserApi userApi;

	public TransactionValidation(IUserApi userApi) {
		this.userApi = userApi;
	}

//	public TransactionError validate(String amount, String senderUsername, String receiverUsername) {
//		TransactionError error = new TransactionError();
//		boolean valid = true;

		// TODO @Vyas
		// TODO 1. Transaction Amount is less than current balance
//		User senderUser = userApi.findByUserName(senderUsername);
//		User receiverUser = userApi.findByUserName(receiverUsername);
//		LogCat.print("Sender Number :: " + senderUser.getUsername());
//		double balanceLimit = 0;
//		if (receiverUser != null) {
//			LogCat.print("Receiver Number :: " + receiverUser.getUsername());
//			LogCat.print(" ::" + receiverUser.getAccountDetail());
//			balanceLimit = receiverUser.getAccountDetail().getAccountType().getBalanceLimit();
//		}
//		double transactionAmount = Double.parseDouble(amount);
//
//		double dailyTransactionLimit = senderUser.getAccountDetail().getAccountType().getDailyLimit();
//		double monthlyTransactionLimit = senderUser.getAccountDetail().getAccountType().getMonthlyLimit();
//
//		double totalDailyTransaction = userApi.dailyTransactionTotal(senderUser);
//		double totalMonthlyTransaction = userApi.monthlyTransactionTotal(senderUser);
//		LogCat.print("daily total " + totalDailyTransaction);
//		LogCat.print("monthly total " + totalMonthlyTransaction);
//
//		// if (transactionAmount > senderUser.getAccountDetail().getBalance()) {
//		if (!CommonValidation.balanceCheck(senderUser.getAccountDetail().getBalance(), transactionAmount)) {
//			// TODO message
//			error.setMessage(
//					"Insufficient Balance. Your current balance is Rs." + senderUser.getAccountDetail().getBalance());
//			valid = false;
//		}
//		// TODO 2. Sender Daily Transaction Limit is to mark
//		// else if (dailyTransactionLimit <= (totalDailyTransaction +
//		// transactionAmount)) {
//		else if (!CommonValidation.dailyLimitCheck(dailyTransactionLimit, totalDailyTransaction, transactionAmount)) {
//			// TODO message
//			error.setMessage("Daily Transaction Limit Exceeded. Your daily transaction limit is Rs. "
//					+ dailyTransactionLimit + " and you already made total transaction of Rs." + totalDailyTransaction);
//			valid = false;
//		}
//		// TODO 3. Sender Monthly Transaction Limit is to mark
//		// else if (monthlyTransactionLimit <= (totalMonthlyTransaction +
//		// transactionAmount)) {
//		else if (!CommonValidation.monthlyLimitCheck(monthlyTransactionLimit, totalMonthlyTransaction,
//				transactionAmount)) {
//			// TODO message
//			error.setMessage("Monthly Transaction Limit Exceeded. Your monthly transaction limit is Rs."
//					+ monthlyTransactionLimit + " and you already made total transaction of Rs."
//					+ totalMonthlyTransaction);
//			valid = false;
//		}
//		// TODO 4. Receiver Account Balance Limit it to mark
//		// else if (balanceLimit <=
//		// (receiverUser.getAccountDetail().getBalance() + transactionAmount)) {
//
//		else if ((receiverUser != null) && !(CommonValidation.receiverBalanceLimit(balanceLimit,
//				receiverUser.getAccountDetail().getBalance(), transactionAmount))) {
//			// TODO message
//			error.setMessage(
//					"Balance Limit Exceeded. " + receiverUser.getUsername() + " balance limit is Rs." + balanceLimit);
//			valid = false;
//		}
		// TODO Return error message and valid status
//		error.setValid(valid);
//		return error;
//	}
//
//	public TransactionError validate(String amount, String senderUsername) {
//		TransactionError error = new TransactionError();
//		boolean valid = true;

		// TODO @Vyas
		// TODO 1. Transaction Amount is less than current balance
//		User senderUser = userApi.findByUserName(senderUsername);
//		LogCat.print("Sender Number :: " + senderUser.getUsername());
//
//		double transactionAmount = Double.parseDouble(amount);
//
//		double dailyTransactionLimit = senderUser.getAccountDetail().getAccountType().getDailyLimit();
//		double monthlyTransactionLimit = senderUser.getAccountDetail().getAccountType().getMonthlyLimit();
//
//		double totalDailyTransaction = userApi.dailyTransactionTotal(senderUser);
//		double totalMonthlyTransaction = userApi.monthlyTransactionTotal(senderUser);
//		LogCat.print("daily total " + totalDailyTransaction);
//		LogCat.print("monthly total " + totalMonthlyTransaction);
//
//		// if (transactionAmount > senderUser.getAccountDetail().getBalance()) {
//		if (!CommonValidation.balanceCheck(senderUser.getAccountDetail().getBalance(), transactionAmount)) {
//			// TODO message
//			LogCat.print("Insufficient Balance");
//			error.setMessage(
//					"Insufficient Balance. Your current balance is Rs." + senderUser.getAccountDetail().getBalance());
//			valid = false;
//		}
//		// TODO 2. Sender Daily Transaction Limit is to mark
//		// else if (dailyTransactionLimit <= (totalDailyTransaction +
//		// transactionAmount)) {
//		else if (!CommonValidation.dailyLimitCheck(dailyTransactionLimit, totalDailyTransaction, transactionAmount)) {
//			// TODO message
//			LogCat.print("Daily Transaction limit exceeded");
//			error.setMessage("Daily Transaction Limit Exceeded. Your daily transaction limit is Rs. "
//					+ dailyTransactionLimit + " and you already made total transaction of Rs." + totalDailyTransaction);
//			valid = false;
//		}
//		// TODO 3. Sender Monthly Transaction Limit is to mark
//		// else if (monthlyTransactionLimit <= (totalMonthlyTransaction +
//		// transactionAmount)) {
//		else if (!CommonValidation.monthlyLimitCheck(monthlyTransactionLimit, totalMonthlyTransaction,
//				transactionAmount)) {
//			// TODO message
//			LogCat.print("Monthly limit exceeded");
//			error.setMessage("Monthly Transaction Limit Exceeded. Your monthly transaction limit is Rs."
//					+ monthlyTransactionLimit + " and you already made total transaction of Rs."
//					+ totalMonthlyTransaction);
//			valid = false;
//		}
//		// TODO Return error message and valid status
//		error.setValid(valid);
//		return error;
//	}
}
