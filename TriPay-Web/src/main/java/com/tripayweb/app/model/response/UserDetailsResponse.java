package com.tripayweb.app.model.response;

public class UserDetailsResponse {

	private boolean success;
	private String code;
	private String message;
	private String status;
	private String details;
	private String response;
	private String userDetail;
	private String accountDetail;
	private int points;
	private String username;
	private String firstName;
	private String middleName;
	private String lastName;
	private String address;
	private String contactNo;
	private String userType;
	private String authority;
	private String emailStatus;
	private String mobileStatus;
	private String email;
	private String image;
	private String sessionId;
	private double balance;
	private String accountType;
	private double dailyTransaction;
	private double monthlyTransaction;
	private long accountNumber;
	private String desc;
	private boolean flag;

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(String userDetail) {
		this.userDetail = userDetail;
	}

	public String getAccountDetail() {
		return accountDetail;
	}

	public void setAccountDetail(String accountDetail) {
		this.accountDetail = accountDetail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}

	public String getMobileStatus() {
		return mobileStatus;
	}

	public void setMobileStatus(String mobileStatus) {
		this.mobileStatus = mobileStatus;
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

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
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

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "UserDetailsResponse [success=" + success + ", code=" + code + ", message=" + message + ", status="
				+ status + ", details=" + details + ", response=" + response + ", userDetail=" + userDetail
				+ ", accountDetail=" + accountDetail + ", points=" + points + ", username=" + username + ", firstName="
				+ firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", address=" + address
				+ ", contactNo=" + contactNo + ", userType=" + userType + ", authority=" + authority + ", emailStatus="
				+ emailStatus + ", mobileStatus=" + mobileStatus + ", email=" + email + ", image=" + image
				+ ", sessionId=" + sessionId + ", balance=" + balance + ", accountType=" + accountType
				+ ", dailyTransaction=" + dailyTransaction + ", monthlyTransaction=" + monthlyTransaction
				+ ", accountNumber=" + accountNumber + ", desc=" + desc + ", getPoints()=" + getPoints()
				+ ", isSuccess()=" + isSuccess() + ", getCode()=" + getCode() + ", getMessage()=" + getMessage()
				+ ", getStatus()=" + getStatus() + ", getDetails()=" + getDetails() + ", getResponse()=" + getResponse()
				+ ", getUserDetail()=" + getUserDetail() + ", getAccountDetail()=" + getAccountDetail()
				+ ", getUsername()=" + getUsername() + ", getFirstName()=" + getFirstName() + ", getMiddleName()="
				+ getMiddleName() + ", getLastName()=" + getLastName() + ", getAddress()=" + getAddress()
				+ ", getContactNo()=" + getContactNo() + ", getUserType()=" + getUserType() + ", getAuthority()="
				+ getAuthority() + ", getEmailStatus()=" + getEmailStatus() + ", getMobileStatus()=" + getMobileStatus()
				+ ", getEmail()=" + getEmail() + ", getImage()=" + getImage() + ", getSessionId()=" + getSessionId()
				+ ", getBalance()=" + getBalance() + ", getAccountType()=" + getAccountType()
				+ ", getDailyTransaction()=" + getDailyTransaction() + ", getMonthlyTransaction()="
				+ getMonthlyTransaction() + ", getAccountNumber()=" + getAccountNumber() + ", getDesc()=" + getDesc()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}	

}
