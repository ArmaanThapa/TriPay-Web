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

	$("#smm_submit").click(function(){
		$("#smm_submit").addClass("disabled");
		$("#smm_submit").html("Please wait..");
		var valid = true;
		$("#error_smm_mobile").html("");
		$("#error_smm_amount").html("");
		var mobile = $("#smm_mobile").val();
		var amount = $("#smm_amount").val();
		if(mobile.length != 10){
			valid = false;
			$("#error_smm_mobile").html("Please enter valid mobile number");
		}
		if(amount < 10){
			valid = false;
			$("#error_smm_amount").html("Amount must be greater than or equal to 10");
		}
		if(valid == false){
			$("#smm_submit").removeClass("disabled");
			$("#smm_submit").html("Send");			
		}
		if(valid == true){
			console.log("inside smm ajax")
			$.ajax({
				type : "POST",
				headers : headers,
				contentType : "application/x-www-form-urlencoded",
				url : "/User/SendMoney/Mobile",
				data : {
					mobileNumber:mobile,
					amount:amount
				},
				success : function(response) {
					$("#smm_submit").removeClass("disabled");
					$("#smm_submit").html("Send");
						console.log(response);
						var parsedResponse = JSON.parse(response.response);
						console.log(parsedResponse.details);
						if(parsedResponse.code == "F00"){
						    $("#error_mobile_sm").html(parsedResponse.details);
						}
						if(parsedResponse.code == "S00"){
							$("#account_balance").html(parsedResponse.balance);
							$("#success_mobile_sm").html(parsedResponse.details);
							$("#smm_mobile").val("");
							$("#smm_amount").val("");
						}
				}
			});

		}
	});
	
});
