package com.tripayweb.validation;

import com.tripayweb.app.model.request.AddMerchantRequest;
import com.tripayweb.app.model.request.MRegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.model.request.RegistrationRequest;
import com.tripayweb.model.error.RegisterError;
import com.tripayweb.util.LogCat;

public class RegisterValidation {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());


	public RegisterError checkError(RegistrationRequest registerRequest)
	{
		System.err.println("in the RegisterValidation ");
		
		
		RegisterError error = new RegisterError();
		boolean valid = true;
		
		if (CommonValidation.isNull(registerRequest.getFirstName()))
		{
			error.setFirstName("Please Enter the name");
			valid = false;
		}
		if (CommonValidation.isNull(registerRequest.getLocationCode()))
		{
			error.setPincode("Please enter pincode");
			valid = false;
		}
		else if(!(CommonValidation.checkLength6(registerRequest.getLocationCode())&& CommonValidation.isNumeric(registerRequest.getLocationCode()))){
			error.setPincode("locationCode should be 6 digits long and should contain only digits");
			valid=false;
		}
		
		if (CommonValidation.isNull(registerRequest.getPassword()))
		{
			error.setPassword("Please enter valid password");
			System.out.println("pasword null");
			valid = false;
		}
		
		
	else if(!CommonValidation.isValidPassword(registerRequest.getPassword()))
	{
			valid = false;
	}
		/*if (CommonValidation.isNull(registerRequest.getConfirmPassword())) 
		{
			error.setConfirmPassword("Please enter confirm Password");
			valid = false;		
		}
		else if(!(registerRequest.getPassword().equals(registerRequest.getConfirmPassword())))
		{
			valid = false;
		}*/

		if (CommonValidation.isNull(registerRequest.getContactNo()))
		{
			error.setContactNo("Please enter your ContactNo");
			valid = false;
		} 
		else if (!CommonValidation.checkLength10(registerRequest.getContactNo()))
		{
			error.setContactNo("mobile number must be 10-digit");
			valid = false;
		}
		else if(!CommonValidation.isNumeric(registerRequest.getContactNo())){
			valid =  false;
		}

		error.setValid(valid);
		return error;
	}

	public RegisterError checkMerchantRegistrationError(MRegistrationRequest request){

		RegisterError error  = new RegisterError();
		boolean valid =  true;
		if(CommonValidation.isNull(request.getFirstName())){
			error.setFirstName("Please enter First Name");
			valid = false;
		}

		if(CommonValidation.isNull(request.getEmail())){
			error.setEmail("Please enter email");
			valid = false;
		}else if(!CommonValidation.isValidMail(request.getEmail())){
			valid = false;
			error.setEmail("Enter valid email");
		}

		if(CommonValidation.isNull(request.getContactNo())){
			valid = false;
			error.setContactNo("Please enter Contact No");
		}else if(!CommonValidation.checkLength10(request.getContactNo())){
			valid = false;
			error.setContactNo("Enter valid contact no");
		}

		if(CommonValidation.isNull(request.getIpAddress())){
			valid = false;
			error.setIpAddress("Enter IP Address");
		}else if(!CommonValidation.isValidIP(request.getIpAddress())){
			valid = false;
			error.setIpAddress("Enter Valid IP Address");
		}

		if(CommonValidation.isNull(request.getSuccessURL())){
			valid = false;
			error.setSuccessURL("Enter Success URL for Payment");
		}else if(!CommonValidation.isValidURL(request.getSuccessURL())){
			valid = false;
			error.setSuccessURL("URL is not in valid format");
		}

		if(CommonValidation.isNull(request.getFailureURL())){
			valid = false;
			error.setFailureURL("Enter Failure URL for Payment");
		}else if(!CommonValidation.isValidURL(request.getFailureURL())){
			valid = false;
			error.setFailureURL("URL is not in valid format");
		}

		if(request.getMinAmount() <= 0){
			valid = false;
			error.setMinAmount("Enter Minimum Amount (> = 1)");
		}
		if(request.getMaxAmount() <= 0){
			valid = false;
			error.setMaxAmount("Enter Maximum Amount(> = 1)");
		}

		if(request.getValue() < 0){
			valid = false;
			error.setValue("Enter Charged Amount( >= 0)");
		}
		error.setValid(valid);
		return error;
	}
}
