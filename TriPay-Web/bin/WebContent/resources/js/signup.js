
/**
 * 
 */

$(document).ready(function(){
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var hash_key="hash";
	var default_hash="123456";
	var headers = {};
	headers[hash_key] = default_hash;
	headers[csrfHeader] = csrfToken;
	processMessage('${msg}');
	console.log("inside signup js");
    $("#registerButton").click(function() {
    	if($("#termsConditions").is(":checked")){ 
    	$("#registerButton").addClass("disabled");
    	$("#registerButton").html("Please wait ...");
    $("#error_first_name").html("");
	$("#error_last_name").html("");
	$("#error_password").html("");
	$("#error_confirm_password").html("");
	$("#error_email").html("");
	$("#error_contact_no").html("");
	$("#error_gender").html("");
	$("#error_dob").html("");
	var valid = true;
	var pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"; //pattern for email
	var passwordPattern = "[a-zA-z0-9]"; //pattern for password
	console.log("process registration");
	var firstName = $('#first_name').val();
	var lastName = $('#last_name').val() ;
	var password  = $('#password').val();
	var email = $('#email').val() ;
	var captchaResponse = $("#g-recaptcha-response").val();
	console.log(captchaResponse);
	var confirmPassword = $('#confirm_password').val();
	var dob = $('#dob').val();
	var contactNo = $('#contact_no').val() ;
	var gender = $("#gender").val() ;
	if(firstName.length <= 0){
		$("#error_first_name").html("enter first name");
		valid = false;
	}
	if(lastName.length <= 0) {
		$("#error_last_name").html("enter last name");
		valid = false;
	}
	if(password.length <= 0){
		$("#error_password").html("enter password");
		valid = false;
	}
	if(confirmPassword.length <=0) {
		$("#error_confirm_password").html("re enter your password");
		valid = false;
	}
	if(new Date(dob) >= new Date("1998-12-31")) {
		$("#error_dob").html("you must be at least 18 years old to sign up");
		valid = false;
	}
	if(gender.length <= 0) {
		$("#error_gender").html("select your gender");
		valid = false;
	}
	if(contactNo.length != 10) {
		$("#error_contact_no").html("enter valid mobile number");
		valid = false;
	}
	if(email.length <= 0) {
		$("#error_email").html("enter email");
		valid = false;
	}
	if(password != confirmPassword) {
		$("#error_confirm_password").html("Passwords Mis Match");
		valid = false;
	}
	if(!email.match(pattern)) {
		$("#error_email").html("enter valid email");
		valid = false;
	}
	if(password.length < 8){
		$("#error_password").html("password must be at least 8 characters long");
		valid = false;
	}
if(valid == false){
	$("#registerButton").removeClass("disabled");
	$("#registerButton").html("Submit");
}	
if(valid == true) {
	$.ajax({
		type : "POST",
		headers: headers,
		contentType : "application/json",
		url : "/Api/v1/User/Windows/en/WebRegistration/Process",
		dataType : 'json',
		data : JSON.stringify({
			"firstName" : "" + firstName+ "",
			"lastName" : "" + lastName + "",
			"password" : "" + password + "",
			"confirmPassword" : "" + confirmPassword + "",
			"contactNo" : "" + contactNo + "",
			"email" : "" + email + "",
			"gender" : "" + gender + "",
			"dateOfBirth" : "" + dob + "",
			"captchaResponse" : ""+captchaResponse+""
		}),
		success : function(response) {
			$("#registerButton").removeClass("disabled");
			$("#registerButton").html("Submit");
			console.log(response);
			if (response.code.includes("S00")) {
				console.log("success");
				$("#regMessage").modal("show");
				$("#reg_otp_username").val($('#contact_no').val());
				$("#regMessage_success").html("Registration Successful OTP sent to "+ $('#contact_no').val()+" and verification mail sent to "+ $('#email').val());
			} 
		}
	});

	
}
	
}
    });

$("#register_resend_otp").click(function() {
	console.log("inside resend mobile OTP");
	$("#register_resend_otp").html("Please Wait...");
	$("#register_resend_otp").addClass("disabled");
	var recaptcha_response = $("#g-recaptcha-response-1").val();
	$.ajax({
		type : "POST",
		headers :headers,
		contentType : "application/json",
		url : "/Api/v1/User/Windows/en/WebRegistration/ResendMobileOTP",
		dataType : 'json',
		data : JSON.stringify({
			"mobileNumber" : "" + $('#reg_otp_username').val() + "",
			"captchaResponse":""+recaptcha_response+""
		}),
		success : function(response) {
			$("#register_resend_otp").html("Resend OTP");
			$("#register_resend_otp").removeClass("disabled");
			console.log(response);
			if (response.code.includes("S00")) {
				console.log("success");
				console.log(response.details);
				$("#regMessage_success").html(response.details);
				$("#fpOTP_message").html(response.details);
			} 
		}
	});	
});

$("#forgot_password_request").click(function(){
	
	console.log("Inside Process Forgot Password");
	var oldVal = $("#fp_submit").val();
	$("#forgot_password_request").addClass("disabled");
	$("#forgot_password_request").val("Please wait ....");
	var username = $('#fp_username').val();
	var recaptcha_response = $('#g-recaptcha-response-2');
	var valid = true;
	if(username.length != 10){
		valid = false;
		$("#fp_error_username").html("Enter Valid Mobile Number");
	}
	if(valid == false){
		$("#forgot_password_request").removeClass("disabled");
		$("#forgot_password_request").val("Continue");
	}
    if(valid == true){
	console.log("request is true")
	$.ajax({
		type : "POST",
		headers : headers,
		contentType : "application/json",
		url : "/ForgotPassword",
		data : JSON.stringify({
			"username" : "" + $('#fp_username').val() + "",
			"captchaResponse" : ""+recaptcha_response+""
		}),
		dataType : 'json',
		success : function(response) {
			console.log("Response " + JSON.stringify(response));
			$("#forgot_password_request").val(oldVal)
			$("#forgot_password_request").removeClass("disabled");
			console.log("Reponse Is ::");

			if (response.code.includes("S00")) {
				$("#forgotPassword").modal("hide");
				$("#fpOTP").modal("show");
				$("#fpusername_forgot").val($('#fp_username').val());
				$("#fpOTP_message").html(response.details);

			} else {
				console.log(response.details);
				$("#fp_message").append(response.details);
			}
		}
	});
	}
	
});

$("#forgot_password_resend_otp").click(function() {
	
	console.log("inside resend mobile OTP forgot Password");
	console.log($('#fpusername_forgot').val());
	var recaptcha_response = $("#g-recaptcha-response-3").val();
	$.ajax({
		type : "POST",
		headers : headers,
		contentType : "application/json",
		url : "ForgotPassword",
		dataType : 'json',
		data : JSON.stringify({
			"username":""+$('#fpusername_forgot').val()+"",
			"captchaResponse" : ""+recaptcha_response+""

		}),
		success : function(response) {
			console.log(response);
			if (response.code.includes("S00")) {
				console.log("success");
				console.log(response.details);
				$("#fpOTP_message").html(response.details);
			} 
		}
	});


	
});

$("#register_verify_mobile").click(function() {
	console.log("inside verify mobile OTP");
	$.ajax({
		type : "POST",
		headers : headers,
		contentType : "application/json",
		url : "/Api/v1/User/Windows/en/Registration/MobileOTP",
		dataType : 'json',
		data : JSON.stringify({
			"mobileNumber" : "" + $('#reg_otp_username').val() + "",
			"key" : "" + $('#verify_reg_otp_key').val() + ""
		}),
		success : function(response) {
			console.log(response);
			if (response.code.includes("S00")) {
				$("#first_name").val("");
				$("#last_name").val("");
				$("#password").val("");
				$("#confirm_password").val("");
				$("#email").val("");
				$("#contact_no").val("");
				$("#gender").val("");
				$("#dob").val("");
				console.log("success");
				$("#regMessage").modal('hide');
				$("#verifiedMessage").modal('show');
				$("#success_verification_message").html(response.details+" Please login to continue");
				console.log(response.details);
			} else {
				$("#regMessage_success").html(response.details);				
			}
		}
	});

});

$("#process_forgot_password_request").click(function() {
	
	console.log("inside forgot password OTP");
	var key = $("#fpOTP_key").val();
	var newPassword = $('#fpnewPassword_key').val();
	var confirmPassword =  $('#fpconfirmPassword_key').val() ;
	console.log(key);
	console.log(newPassword);
	console.log(confirmPassword);
	console.log($('#fpusername_forgot').val());
	if(newPassword === confirmPassword) {
	$.ajax({
		type : "POST",
		headers : headers,
		contentType : "application/json",
		url : "/Api/v1/User/Windows/en/Login/ChangePasswordWithOTP",
		dataType : 'json',
		data : JSON.stringify({
			"username" : "" + $('#fpusername_forgot').val() + "",
			"newPassword" : "" + newPassword + "",
			"confirmPassword" : "" + confirmPassword + "",
			"key" : "" + key + ""
		}),
		success : function(response) {
			console.log(response);
			if (response.code.includes("S00")) {
				console.log("success");
				$("#fpOTP").modal("hide");
				$("#successNotification").modal("show");
				$("#success_alert").html(response.details);
			} else {
				$("#fpusername_forgot").val(response.details.username);
				$("#fpOTP_message").html(response.details.key);
			}
		}
	});
	}else {
		$('#fpnewPassword_key').val("Please enter same password in both fields") 
	}

});

$("#termsConditions").click(function() {
	console.log("inside signup.js file");
	var isChecked = $("#termsConditions").is(":checked");
	console.log(isChecked);
	if (isChecked) {
		console.log(isChecked);
		$("#registerButton").removeClass("disabled");
	} else {
		$("#registerButton").addClass("disabled");
	}

	
});

});

