/**
 * 
 */

$(document).ready(function() {
	$('#re_send_otp').click(function(){
		$("#re_send_otp").addClass("disabled");
		
		$.ajax({
			type : "GET",
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