package com.tripayweb.app.model;

public class SchoolPayment 
{
	private String firstName;
	private String LastName;
	private String rollNumber;
	private String className;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String toString() {
		return "SchoolPayment [firstName=" + firstName + ", LastName=" + LastName + ", rollNumber=" + rollNumber
				+ ", className=" + className + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName()
				+ ", getRollNumber()=" + getRollNumber() + ", getClassName()=" + getClassName() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
