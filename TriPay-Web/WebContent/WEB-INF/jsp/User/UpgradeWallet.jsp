<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>TriPay | Upgrade Wallet</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/font-awesome.min.css"/>">

<link href='<c:url value='/resources/css/font-family.css'/>'
	rel='stylesheet' type='text/css'>


<link rel="stylesheet"
	href="<c:url value='/resources/css/font-awesome.min.css'/>"
	type='text/css'>

<!-- Optional theme -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap-theme.min.css'/>"
	type='text/css'>

<link href="<c:url value="/resources/css/css_style.css"/>"
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.min.css'/>"
	type='text/css'>
<script src="<c:url value='/resources/js/jquery.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/userdetails.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/header.js"/>"></script>
<link rel="icon" href='<c:url value="/resources/images/favicontripay.png"/>'
	type="image/png" />

<link href='<c:url value='/resources/css/font-family.css'/>'
	rel='stylesheet' type='text/css'>


<link href='<c:url value="/resources/css/css_style.css"/>'
	rel='stylesheet' type='text/css'>
<style>
.no-js #loader {
	display: none;
}

.js #loader {
	display: block;
	position: absolute;
	left: 100px;
	top: 0;
}

.se-pre-con {
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
	height: 100%;
	z-index: 9999;
	background: url(/images/pq_large.gif) center no-repeat #fff;
}
</style>
<script src="<c:url value='/resources/js/modernizr.js'/>"></script>

<script type="text/javascript">
	$(window).load(function() {
		$(".se-pre-con").fadeOut("slow");
	});
</script>

</head>

<body onLoad="vals()">
	<div class="se-pre-con"></div>
	<jsp:include page="/WEB-INF/jsp/User/Header.jsp" />
	<jsp:include page="/WEB-INF/jsp/User/Menu.jsp" />

	<!-----------------end navbar---------------------->

	<!------------- end main-------------------->

	<div class="background"></div>
	<!---blue box---->


	<div class="container" id="box">
		<div id="row">
			<div class="col-md-12 " id="receipts">

				<div class="container" style="margin-top: 8px;">
					<div class="col-md-4 col-md-offset-3">
						<form id="openmodel" name="form1" modelAttribute="upgradeWallet"
							method="post"
							action="${pageContext.request.contextPath}/User/KycRequest/process">

							<div class="group_1">
								<input type="number" name="mobileNumber" id="contact_no"
									autocomplete="off" minlength="10" maxlength="10" required
									onKeyPress="return numbersonly(this, event)"> <span
									class="highlight"></span><span class="bar"></span> <label>Mobile
									Number</label>
								<p id="error_contact_no" class="error"></p>

							</div>

							<div class="group_1">
								<input type="number" name="accountNumber" id="accountNumber"
									minlength="15" maxlength="15" required autocomplete="off"
									onKeyPress="return numbersonly(this, event)"> <span
									class="highlight"></span><span class="bar"></span><label>Account
									Number</label>
								<p id="error_accountNo" class="error"></p>
							</div>
							<sec:csrfInput />
							<button type="submit" class="btn"
								style="margin-top: 12px; margin-left: 20px; background: #ec2029; color: #FFFFFF;">Submit</button>
						</form>
					</div>
				</div>


				<!-- Model for  kyc web
	 -->

				<div>
					<input type="hidden" value="${resp}" id="openmodel"
						name="openmodel"> <input type="hidden"
						value="${mobileNumber}" id="mobileNumber" name="mobileNumber">
					<input type="hidden" value="${sentOtpResp}" id="sentOtpResp"
						name="sentOtpResp">



					<div class="modal fade" role="dialog" data-keyboard="false"
						id="OTP">
						<div class="modal-dialog modal-sm">
							<div class="modal-content text-center">
								<button type="button" class="close">&times;</button>
								<div class="icon">
									<i class="fa fa-unlock-alt fa-2x" aria-hidden="true"></i>
								</div>
								<h4>Enter Your OTP</h4>
								<hr style="margin-top: 20px;">

								<div class="group_1">
									<input type="hidden" name="username" id="change_username"
										class="form-control input-sm" value="" required />
								</div>
								<div class="group_1">
									<input type="text" name=key id="OTP_key" maxlength="6"
										pattern="[0-9]{6,}" required
										onKeyPress="return numbersonly(this, event)" /> <span
										class="highlight"></span><label>OTP</label><br> <br>



									<button class="btn  btn-md  btncu"
										style="float: left; background: red; color: white"
										type="button" style="margin-right: 5px" onclick="enterotp()">Continue</button>

									<button type="submit" class="btn" id="kycOtp"
										style="width: 20%; background: #ff0000; margin-top: 10px; color: #FFFFFF;">Resend</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script>
		function vals() {

			var res = document.getElementById("openmodel").value;

			if (res.includes("S00")) {
				$("#OTP").modal();
				//alert(message)

				//document.getElementById("change_username").value = mobile;

			} else {

			}

		}

		function enterotp() {
			var otp = document.getElementById("OTP_key").value;
			$.ajax({
				type : "GET",
				url : "/User/OTP/kyc/Verify",
				data : "key=" + otp,
				success : function(response) {
					console.log(response);
					console.log(otp);
					if (response.includes("S00")) {
						$("#OTP").modal('hide');
						window.location = "/User/Home";
					} else {
						alert("Otp Mismatch")
					}
				}
			});

		}

		$("#kycOtp").click(function() {
			alert("checking resend otp ................")
			console.log("inside resend mobile OTP");
			$("#otpspinser").show();
			$.ajax({
				type : "POST",
				headers : {
					"hash" : "123456"
				},
				contentType : "application/json",
				url : "/User/Kyc/resendOtp",
				dataType : 'json',
				data : JSON.stringify({
					"mobileNumber" : "" + $('#post_mobile').val() + ""
				}),
				success : function(response) {
					console.log(response);
					if (response.code.includes("S00")) {
						$("#otpspinser").hide();
						console.log("success");
						console.log(response.details);
						$("#regMessage_success").html(response.details);
						$("#fpOTP_message").html(response.details);
					} else {
						$("#otpspinser").hide();
					}
				}
			});
		});
	</script>







	<!-------END BOX----------->

	<!---end row-->
	<!----end container-->
	<jsp:include page="/WEB-INF/jsp/User/Footer.jsp" />

</body>
</html>



