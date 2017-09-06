
/**
 * 
 */

$(document).ready(function(){
	var spinnerUrl = "Please wait <img src='/resources/images/spinner.gif' height='20' width='20'>"
    $(".img_show").hide();
	$("#show_img").click(function () {
		$(".img_show").show(1000);
	});
   
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
    $("#pwd_eye").click(function(){
		var value = $("#password").attr("type");
		if(value == "password") {
			$(this).attr("class", "fa fa-eye");
			$("#password").attr("type", "text");
		}
		if(value == "text"){
			$(this).attr("class", "fa fa-eye-slash");
			$("#password").attr("type", "password");
		}

	});

	$("#cfm_pwd_eye").click(function(){
		var value = $("#confirm_password").attr("type");
		if(value == "password") {
			$(this).attr("class", "fa fa-eye");
			$("#confirm_password").attr("type", "text");
		}
		if(value == "text"){
			$(this).attr("class", "fa fa-eye-slash");
			$("#confirm_password").attr("type", "password");
		}

	});

	$("#login_pwd_eye").click(function(){
		var value = $("#password_login").attr("type");
		if(value == "password") {
			$(this).attr("class", "fa fa-eye");
			$("#password_login").attr("type", "text");
		}
		if(value == "text"){
			$(this).attr("class", "fa fa-eye-slash");
			$("#password_login").attr("type", "password");
		}

	});

	$('#password').keyup(function() {
		$('#strength_password').html(checkStrength($('#password').val()))
	});

	$("#registerButton").click(function() {
    	if($("#termsConditions").is(":checked")){
    	
    $("#error_first_name").html("");
	$("#error_last_name").html("");
	$("#error_password").html("");
	$("#error_confirm_password").html("");
	$("#error_email").html("");
	$("#error_locationCode").html("");
	$("#error_contact_no").html("");
	$("#error_gender").html("");
	$("#error_dob").html("");
			$("#error_captcha").html("");
	var valid = true;
	var pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"; //pattern for email
	var passwordPattern = "[a-zA-z0-9]"; //pattern for password
	console.log("process registration");
	var firstName = $('#first_name').val();
	var password  = $('#password').val();
	var email = $('#email').val() ;
	var captchaResponse = $("#g-recaptcha-response").val();
	console.log(captchaResponse);
	var dob = $('#dob').val();
	var locationCode= $('#pincode').val();
	var contactNo = $('#contact_no').val() ;

	if(contactNo.length != 10) {
		$("#error_contact_no").html("enter valid mobile number");
		valid = false;
	}

	if(password.length <= 0){
				$("#error_password").html("enter password");
				valid = false;
	}
	if(firstName.length <= 0){
				$("#error_first_name").html("enter name");
				valid = false;
	}
	if(email.length > 0) {
		if(!email.match(pattern)) {
			$("#error_email").html("enter valid email");
			valid = false;
		}
	}
	if(new Date(dob) >= new Date("1998-12-31")) {
				$("#error_dob").html("you must be at least 18 years old to sign up");
				valid = false;
	}
	if(password.length != 9){
		$("#error_password").html("password must be 6 characters long");
		valid = false;
	}
	if(locationCode.length !=6){
		$("#error_pincode").html("locationCode must be 6 characters long only");
	}

if(valid == true) {
	$("#registerButton").addClass("disabled");
	$("#registerButton").html(spinnerUrl);
	console.log("INSIDE SIGNUP.js");
	$.ajax({
		type : "POST",
		headers: headers,
		contentType : "application/json",
		url : "/Api/v1/User/Windows/en/WebRegistration/Process",
		dataType : 'json',
		data : JSON.stringify({
			"firstName" : "" + firstName+ "",
			"lastName" : " ",
			"password" : "" + password + "",
			"confirmPassword" : "" + password + "",
			"contactNo" : "" + contactNo + "",
			"email" : "" + email + "",
			"locationCode" : "" + locationCode + "",
			"gender" : "NA",
			"dateOfBirth" : "" + dob + "",
			"captchaResponse" : ""+captchaResponse+""
		}),
		success : function(response) {
			console.log("-----------SIGN UP RESPONSE---------");
			$( ".captcha_link" ).trigger( "click" );
			$("#registerButton").removeClass("disabled");
			$("#registerButton").html("Sign Up");
			console.log(response);
			if (response.code.includes("S00")) {
				console.log("success");
				$("#regMessage").modal("show");
				$("#reg_otp_username").val($('#contact_no').val());
				$("#regMessage_success").html("Registration Successful OTP sent to "+ $('#contact_no').val()+" and verification mail sent to "+ $('#email').val());
			}
			if(response.code.includes("F00")){
				if(response.details == null) {
						$("#error_captcha").html(response.message);
				}else{
					var msg=[];
					msg  = response.details.split("|");
					console.log("message 0"+msg[0]);
					console.log("message 1"+msg[1]);
					if(msg[1].includes("User Already Exist"))
					{
						$("#error_contact_no").html(msg[1]);
					}
					if(msg[2].includes("Email already exists")){
						$("#error_email").html(msg[2]);
					}
					$("#form_error").html(msg);
				}
			}

		}
	});


}

}
    });


$("#register_resend_otp").click(function() {

	console.log("inside resend mobile OTP");
	$("#register_resend_otp").html(spinnerUrl);
	$("#register_resend_otp").addClass("disabled");
	var recaptcha_response = $("#g-recaptcha-response-1").val();
    console.log("recaptcha response"+recaptcha_response);
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
			$( ".captcha_link" ).trigger( "click" );
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
	$("#forgot_password_request").val(spinnerUrl);
	var username = $('#fp_username').val();
	var recaptcha_response = $("#g-recaptcha-response-2").val();
	console.log("recaptcha response is"+recaptcha_response);
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
			$( ".captcha_link" ).trigger( "click" );
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
	$( ".captcha_link" ).trigger( "click" );
	console.log("inside resend mobile OTP forgot Password");
	console.log($('#fpusername_forgot').val());
	var recaptcha_response = $("#g-recaptcha-response-3").val();
	$.ajax({
		type : "POST",
		headers : headers,
		contentType : "application/json",
		url : "/ForgotPassword",
		dataType : 'json',
		data : JSON.stringify({
			"username":""+$('#fpusername_forgot').val()+"",
			"captchaResponse" : ""+recaptcha_response+""

		}),
		success : function(response) {
			$( ".captcha_link" ).trigger( "click" );
			console.log(response);
			if (response.code.includes("S00")) {
				console.log("success");
				console.log(response.details);
				$("#fpOTP_message").html(response.details);
			} else{

			}
		}
	});


	
});

$("#register_verify_mobile").click(function() {
	$( ".captcha_link" ).trigger( "click" );
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
			$( ".captcha_link" ).trigger( "click" );
			console.log(response);
			if (response.code.includes("S00")) {
				$("#first_name").val("");
				$("#last_name").val("");
				$("#password").val("");
				$("#confirm_password").val("");
				$("#email").val("");
				$("#contact_no").val("");
				$("#gender").val("");
				$("#locationCode").val("");
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
	$( ".captcha_link" ).trigger( "click" );
	console.log("inside forgot password OTP");
	var key = $("#fpOTP_key").val();
	var newPassword = $("#fpnewPassword_key").val();
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
		url : "/ChangePasswordWithOTP",
		dataType : 'json',
		data : JSON.stringify({
			"username" : "" + $('#fpusername_forgot').val() + "",
			"newPassword" : "" + newPassword + "",
			"confirmPassword" : "" + confirmPassword + "",
			"key" : "" + key + ""
		}),
		success : function(response) {
			$( ".captcha_link" ).trigger( "click" );
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
		$('#fpnewPassword_key').val("Please enter same password in both fields");

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

	$(".captcha_link").click(function(){
		var $elem = $('.glyphicon-refresh');
		var angle=360;
		$({deg: 0}).animate({deg: angle}, {
			duration: 1000,
			step: function(now) {
				$elem.css({
					transform: 'rotate(' + now + 'deg)'
				});
			}
		});
		$(".captcha_image").attr("src","/Captcha");
	});

	$(".numeric").keydown(function(event){
		if(event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 || event.keyCode == 13 || (event.keyCode == 65 && event.ctrlKey == true) || (event.keyCode >= 35 && event.keyCode<=39)){
			return;
		}else{
			if(event.shiftKey || (event.keyCode < 48 || event.keyCode >57) && (event.keyCode < 96 || event.keyCode > 105)) {
				event.preventDefault();
			}
		}
	});

	$("#forgot_password_modal").click(function(){
		$(".captcha_link").trigger("click");
	})


	function checkStrength(password) {
		var strength = 0
		if (password.length < 6) {
			$('#strength_password').removeClass()
			$('#strength_password').addClass('short')
			return 'Too short'
		}
		if (password.length > 7) strength += 1
		// If password contains both lower and uppercase characters, increase strength value.
		if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)) strength += 1
		// If it has numbers and characters, increase strength value.
		if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)) strength += 1
		// If it has one special character, increase strength value.
		if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1
		// If it has two special characters, increase strength value.
		if (password.match(/(.*[!,%,&,@,#,$,^,*,?,_,~].*[!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1
		// Calculated strength value, we can return messages
		// If value is less than 2
		if (strength < 2) {
			$('#strength_password').removeClass()
			$('#strength_password').addClass('weak')
			return 'Weak'
		} else if (strength == 2) {
			$('#strength_password').removeClass()
			$('#strength_password').addClass('good')
			return 'Good'
		} else {
			$('#strength_password').removeClass()
			$('#strength_password').addClass('strong')
			return 'Strong'
		}
	}
});

