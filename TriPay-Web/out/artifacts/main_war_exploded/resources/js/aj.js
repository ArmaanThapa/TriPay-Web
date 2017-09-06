/**
 * 
 */
//For Resend EmailOTP
$(document).ready(function() {
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var headers = {};
	headers[csrfHeader] = csrfToken;
	$('#re_send_otp').click(function(){
		$("#re_send_otp").addClass("disabled");
		$.ajax({
			type : "POST",
			headers: headers,
			url : "/User/ReSendEmailOTP",
			contentType : "application/json",
			datatype : 'json',
			
			success : function(response) {
				console.log(response);
				$("#re_send_otp").removeClass("disabled");
				$("#alert_verification_message").html('You again received a verification on you registered Email Id');
			}
			
		})
	})
})


/**
 * For Redeem Coupon Code
 **/

$(document).ready(function(){
	$("#redeem_button").click(function() {
		$("#redeem_button").addClass("disabled");
		$("#redeem_button").html("Please Wait...");
		var code =$("#couponNumber").val();
		$.ajax({
			type : "POST",
			url : "/User/RedeemCoupon",
			contentType : "application/x-www-form-urlencoded",
			data : {
				promoCode : code
			},
				success : function(response) {
					$("#redeem_button").removeClass("disabled");
					$("#redeem_button").html("Pay");

						var parsedResponse = JSON.parse(response.response);
						 $("#redeemId").html(parsedResponse.message)
				}
			});
	
	});

})