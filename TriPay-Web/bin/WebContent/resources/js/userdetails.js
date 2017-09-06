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
	
	$.ajax({
		type : "GET",
		url : "/User/GetUserDetails",
		headers: headers,
		contentType : "application/json",
		datatype : 'json',
		success : function(response) {
			var u = response;
			console.log(response);
			eStatus = u.emailStatus;
			var firstNameLength = $("#firstName").length;
			var accountBalanceLength = $("#account_balance").length;
			var userMobileLength = $("#user_mobile").length;
			var userAdressLength = $("#user_address").length;
			var  accountNumberLength = $("#account_number").length;
			var accountTypeLength = $("#account_type").length;
			var accountDesLength = $("#account_type_description").length;
			var dailyLimitLength = $("#daily_limit").length;
			var monthlyLimitLength = $("#monthly_limit").length;
			var emailLength = $("#user_email").length;
			$("#small_display_pic").attr('src',function(){
				if(u.image == "" || u.image == null || u.image == " "){
					return "http://66.207.206.54:8034/resources/images/sample.jpg"
				}else{
					return "http://66.207.206.54:8035"+u.image;
				}
			});
			$("#display_pic").attr('src',function(){
				if(u.image == "" || u.image == null || u.image == " " ){
					return "http://66.207.206.54:8034/resources/images/sample.jpg"
				}else{
					return "http://66.207.206.54:8035"+u.image;
				}
			});
			
			$("#display_first_last_name").html(u.firstName+" "+u.lastName);
			if(firstNameLength){
				$("#first_name").html(u.firstName);
				$("#display_first_last_name").html(u.firstName+" "+u.lastName);
			}
		
			if(accountBalanceLength){
				$("#account_balance").html(u.balance);
				$("#user_balance").html(u.balance);
			}
			if(emailLength){
				$("#user_email").html(u.email);
			}
			if(userMobileLength){
			$("#user_mobile").html(u.contactNo);
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
				$("#alert_verification_message").html("Your email id is not verified. You've received verification email on "+u.email+". Please click on the link to verify your email.");
				$("#verification_alert").modal('show');
				$("#dth_submit").addClass("disabled");
				$("#landline_submit").addClass("disabled");
				$("#gas_submit").addClass("disabled");
				$("#ecity_submit").addClass("disabled");
				$("#ins_submit").addClass("disabled");
				//for topup
				$("#pre_submit").addClass("disabled");
				$("#post_submit").addClass("disabled");
				$("#dc_submit").addClass("disabled");
				
				$("#smm_submit").addClass("disabled");
				$("#lm_submit").addClass("disabled");
				
				
			}
			if(u.emailStatus == "Active"){
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
	

});