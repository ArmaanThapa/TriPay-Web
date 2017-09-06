/**
 * 
 */
var eStatus = "";

$(document).ready(function(){
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var headers = {};
	headers[csrfHeader]=csrfToken;

	$(".numeric").keydown(function(event){
        if(event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 || event.keyCode == 13 || (event.keyCode == 65 && event.ctrlKey == true) || (event.keyCode >= 35 && event.keyCode<=39)){
            return;
        }else{
            if(event.shiftKey || (event.keyCode < 48 || event.keyCode >57) && (event.keyCode < 96 || event.keyCode > 105)) {
                event.preventDefault();
            }
        }
    });

	$.ajax({
		type : "GET",
		url : "/TriPay-web/User/GetUserDetails",
		headers: headers,
		contentType : "application/json",
		datatype : 'json',
		success : function(response) {
			var u = response;
			console.log(response);
			eStatus = u.emailStatus;
			var usertype=u.flag;
			console.log(usertype);
			if(response.flag){
				$("#hidethis1").detach();
				$("#hidethis2").detach();
			}
			var firstNameLength = $("#firstName").length;
			var lastNameLength = $("#lastName").length;
			var accountBalanceLength = $("#account_balance").length;
			var userMobileLength = $("#user_mobile").length;
			var userAdressLength = $("#user_address").length;
			var  accountNumberLength = $("#account_number").length;
			var accountTypeLength = $("#account_type").length;
			var accountDesLength = $("#account_type_description").length;
			var dailyLimitLength = $("#daily_limit").length;
			var monthlyLimitLength = $("#monthly_limit").length;
			var emailLength = $("#user_email").length;
			$("#user_points").html(u.points);
			$("#small_display_pic").attr('src',function(){
				if(u.image == "" || u.image == null || u.image == " "){
					return "/TriPay-web/resources/images/sample.jpg"
				}else{
					return "/TriPay-web"+u.image;
				}
			});
			$("#display_pic").attr('src',function(){
				if(u.image == "" || u.image == null || u.image == " " ){
					return "/TriPay-web/resources/images/sample.jpg"
				}else{
					return "/TriPay-web"+u.image;
				}
			});
            $("#first_name_ep").val(u.firstName);
			$("#loadmoney_username").val(u.firstName+" "+u.lastName);
			$("#loadmoney_phone").val(u.contactNo);
			$("#loadmoney_email").val(u.email);
            $("#last_name_ep").val(u.lastName);
            $("#email").val(u.email);
            $("#address").val(u.address);
			$("#display_first_last_name").html(u.firstName+" "+u.lastName);
			$("#bt_account_number").val(u.accountNumber);
			$("#bt_email").val(u.email);
			$("#bt_mobile_number").val(u.contactNo);
            
			if(firstNameLength){
				$("#first_name").html(u.firstName);
			}
			if(accountBalanceLength){
				$("#account_balance").html(u.balance);
				$("#user_balance").html(u.balance);
			}
			if(emailLength){
				$("#user_email").html(u.email);
			}
			if(userMobileLength){
			$("#user_mobile").html(u.contactNo+ " <span class='glyphicon glyphicon-ok-sign' style='color:#17bcc8;'></span>");
			}
			if(userAdressLength){
			$("#user_address").html(u.address);

			}
			if(accountNumberLength){
			$("#account_number").html(u.accountNumber);
			}
			if(accountTypeLength){
			$("#account_type").html(u.userType);
			}
			if(accountDesLength){
			$("#account_type_description").html();
			}
			if(dailyLimitLength){
			$("#daily_limit").html(u.dailyTransaction);
			}
			if(monthlyLimitLength){
			$("#monthly_limit").html(u.monthlyTransaction);
			}
			
			if(u.emailStatus == "Inactive"){
				$("#alert_verification_message").html("Your email id is not verified. You've received verification email on "+u.email+". If you want to change your mail go to settings and update");
				$("#verification_alert").modal('show');
				$("#dth_submit").attr("disabled", "disabled");
				$("#landline_submit").attr("disabled", "disabled");
				$("#gas_submit").attr("disabled", "disabled");
				$("#ecity_submit").attr("disabled", "disabled");
				$("#ins_submit").attr("disabled", "disabled");
				//for topup
				$("#pre_submit").attr("disabled", "disabled");
				$("#post_submit").attr("disabled", "disabled");
				$("#dc_submit").attr("disabled", "disabled");
				
				$("#smm_submit").attr("disabled", "disabled");
				$("#lm_submit").attr("disabled", "disabled");
			//	$("#email_ep").show();
				
			}
			if(u.emailStatus == "Active"){
				$("#user_email").append(" <span class='glyphicon glyphicon-ok-sign' style='color:#17bcc8;'></span>");
			//	$("#email_ep").hide();
				$("#dth_submit").removeClass("disabled");
				$("#landline_submit").removeClass("disabled");
				$("#gas_submit").removeClass("disabled");
				$("#ecity_submit").removeClass("disabled");
				$("#ins_submit").removeClass("disabled");
				//for topup
				$("#pre_submit").removeClass("disabled");
				$("#post_submit").removeClass("disabled");
				$("#dc_submit").removeClass("disabled");
				
				$("#smm_submit").removeClass("disabled");
				$("#lm_submit").removeClass("disabled");
				
			}
		},
	});

	$("#pwd_eye").click(function(){
		var value = $("#current").attr("type");
		if(value == "password") {
			$(this).attr("class", "fa fa-eye");
			$("#current").attr("type", "text");
		}
		if(value == "text"){
			$(this).attr("class", "fa fa-eye-slash");
			$("#current").attr("type", "password");
		}

	});

	$("#new_pwd_eye").click(function(){
		var value = $("#new").attr("type");
		if(value == "password") {
			$(this).attr("class", "fa fa-eye");
			$("#new").attr("type", "text");
		}
		if(value == "text"){
			$(this).attr("class", "fa fa-eye-slash");
			$("#new").attr("type", "password");
		}

	});

	$("#cnf_pwd_eye").click(function(){
		var value = $("#confirm_new").attr("type");
		if(value == "password") {
			$(this).attr("class", "fa fa-eye");
			$("#confirm_new").attr("type", "text");
		}
		if(value == "text"){
			$(this).attr("class", "fa fa-eye-slash");
			$("#confirm_new").attr("type", "password");
		}

	});

});