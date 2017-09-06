/**
f * 
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
	
	$("#ife_submit").click(function(){
		$("#ife_submit").addClass("disabled");
		$("#ife_submit").html("Please Wait..");
		$("#error_ife_mail").html("");
		$("#error_ife_receiver").html("");
		var valid = true;
		var mail = $("#ife_email").val();
		var pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
		var receiver = $("#ife_receiver").val();
		var recaptcha_response = $("#g-recaptcha-response").val();
		if(!(mail.match(pattern))){
			$("#error_ife_mail").html("enter valid email");
			valid = false;
		}
		if(receiver == "" || receiver == null){
			$("#error_ife_receiver").html("enter receiver's name");
			valid = false;
		}
		if(valid == false){
			$("#ife_submit").removeClass("disabled");
			$("#ife_submit").html("Invite");
		}
		if(valid == true){
			
			$.ajax({
				type : "POST",
				headers: headers,
				contentType : "application/x-www-form-urlencoded",
				url : "/User/Invite/Email",
				data : {
					email:mail,
					receiversName:receiver,
					captchaResponse: recaptcha_response
				},
				success : function(response) {
					$("#ife_submit").removeClass("disabled");
					$("#ife_submit").html("Invite");
					console.log(response.response);
						var parsedResponse = JSON.parse(response.response);
						console.log(parsedResponse.details);
						if(parsedResponse.code == "F00"){
						    $("#error_ife_title").html(parsedResponse.details);
						}
						if(parsedResponse.code == "S00"){
						    $("#success_ife_bill").html(parsedResponse.details);							
							$("#ife_email").val("");
							$("#ife_receiver").val("");

						}
				}
			});

			
		}
		
		
	});
	
	
	// From Mobile 
	
	
	$("#ifm_submit").click(function(){
		$("#ifm_submit").addClass("disabled");
		$("#ifm_submit").html("");
		$("#error_ifm_mail").html("");
		$("#error_ifm_receiver").html("");
		var valid = true;
		var mobile = $("#ifm_mobile").val();
		var receiver = $("#ifm_receiver").val();
		var recaptcha_response = $("#g-recaptcha-response-1").val();
		if(mobile.length != 10){
			$("#error_ifm_mobile").html("enter valid mobile number");
			valid = false;
		}
		if(receiver == "" || receiver == null){
			$("#error_ifm_receiver").html("enter receiver's name");
			valid = false;
		}
		if(valid == false){
			$("#ifm_submit").removeClass("disabled");
			$("#ifm_submit").html("Invite");
		}
		if(valid == true){
			
			$.ajax({
				type : "POST",
				headers : headers,
				contentType : "application/x-www-form-urlencoded",
				url : "/User/Invite/Mobile",
				data : {
					mobileNo:mobile,
					receiversName:receiver,
					captchaResponse : recaptcha_response
				},
				success : function(response) {
					$("#ifm_submit").removeClass("disabled");
					$("#ifm_submit").html("Invite");
					console.log(response.response);
						var parsedResponse = JSON.parse(response.response);
						console.log(parsedResponse.details);
						if(parsedResponse.code == "F00"){
						    $("#error_ifm_title").html(parsedResponse.details);
						}
						if(parsedResponse.code == "S00"){
						    $("#success_ifm_bill").html(parsedResponse.details);							
							$("#ifm_mobile").val("");
							$("#ifm_receiver").val("");

						}
				}
			});

			
		}
		
		
	});
	
});