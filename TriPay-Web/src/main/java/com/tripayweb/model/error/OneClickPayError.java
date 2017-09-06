package com.tripayweb.model.error;

public class OneClickPayError 
{
	private boolean valid;
private boolean isNull;
private String message;
private boolean isAlphanumeric;
private boolean containsAlphabets;
private boolean isNumeric;
private boolean isValidLoadMoneyTransaction;
private boolean isValidTransactionId;
public boolean isNull() {
	return isNull;
}
public void setNull(boolean isNull) {
	this.isNull = isNull;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public boolean isAlphanumeric() {
	return isAlphanumeric;
}
public void setAlphanumeric(boolean isAlphanumeric) {
	this.isAlphanumeric = isAlphanumeric;
}
public boolean isContainsAlphabets() {
	return containsAlphabets;
}
public void setContainsAlphabets(boolean containsAlphabets) {
	this.containsAlphabets = containsAlphabets;
}
public boolean isNumeric() {
	return isNumeric;
}
public void setNumeric(boolean isNumeric) {
	this.isNumeric = isNumeric;
}
public boolean isValidLoadMoneyTransaction() {
	return isValidLoadMoneyTransaction;
}
public void setValidLoadMoneyTransaction(boolean isValidLoadMoneyTransaction) {
	this.isValidLoadMoneyTransaction = isValidLoadMoneyTransaction;
}
public boolean isValidTransactionId() {
	return isValidTransactionId;
}
public void setValidTransactionId(boolean isValidTransactionId) {
	this.isValidTransactionId = isValidTransactionId;
}
public boolean isValid() {
	return valid;
}
public void setValid(boolean valid) {
	this.valid = valid;
}




}
