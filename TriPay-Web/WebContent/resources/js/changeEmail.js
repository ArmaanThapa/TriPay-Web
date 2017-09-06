/**
f * 
 */

$(document).ready(function(){
	console.log("AJAY");
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var hash_key="hash";
	var default_hash="123456";
	var headers = {};
	headers[hash_key] = default_hash;
	headers[csrfHeader] = csrfToken;
	
	$("#ch_email").click(function(){
		console.log("AJAYA");
		$("#ch_email").addClass("disabled");
		console.log("1");
		$("#ch_email").html("Please Wait..");
		console.log("2");
		$("#error_ch_email").html("");
		console.log("3");
		$("#error_ife_receiver").html("");
		console.log("4");
        $("#error_ife").html("");
        console.log("5");
        $("#success_ife").html("");
        console.log("6");
		var valid = true;
		console.log("7");
		var mail = $("#ch_email").val();
		console.log("8");
		var pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
			console.log("9");
		var receiver = $("#ife_receiver").val();
		console.log("10");
		
		if(!(mail.match(pattern))){
			console.log("11");
			$("#error_ife_mail").html("enter valid email");
			console.log("12");
			valid = false;
			console.log(valid);
		}
		
		if(valid == false){
			$("#ch_email").removeClass("disabled");
			$("#ch_email").html("Invite");
		}
		if(valid == true){
			
			$.ajax({
				type : "POST",
				headers: headers,
				contentType : "application/x-www-form-urlencoded",
				url : "/TriPay-web/User/Invite/Email",
				data : {
					email:mail,
					receiversName:receiver,
					captchaResponse: recaptcha_response
				},
				success : function(response) {
					$(".captcha_link").trigger("click");
                    console.log(response);
					$("#ch_email").removeClass("disabled");
					$("#ch_email").html("Invite");

						if(response.code == "F00"){
						    $("#error_ife").html(response.message);
						}
						if(response.code == "S00"){
						    $("#success_ife").html(response.message);
							$("#ch_email").val("");
							$("#ife_receiver").val("");
                            $("#g-recaptcha-response").val("")

						}
				}
			});
			
		}
		
	});
	
	});