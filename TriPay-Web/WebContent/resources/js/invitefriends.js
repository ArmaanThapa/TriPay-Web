/**
f * 
 */
$(document).ready(function(){
	var spinnerUrl = "Please wait <img src='/resources/images/spinner.gif'  height='20' width='20'>"
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
		$("#ife_submit").html(spinnerUrl);
		$("#error_ife_mail").html("");
		$("#error_ife_receiver").html("");
        $("#error_ife").html("");
        $("#success_ife").html("");
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
				url : "/TriPay-web/User/Invite/Email",
				data : {
					email:mail,
					receiversName:receiver,
					captchaResponse: recaptcha_response
				},
				success : function(response) {
					$(".captcha_link").trigger("click");
                    console.log(response);
					$("#ife_submit").removeClass("disabled");
					$("#ife_submit").html("Invite");



						if(response.code == "F00"){
						    $("#error_ife").html(response.message);
						}
						if(response.code == "S00"){
						    $("#success_ife").html(response.message);
							$("#ife_email").val("");
							$("#ife_receiver").val("");
                            $("#g-recaptcha-response").val("")

						}
				}
			});

			
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
});