<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<sec:csrfMetaTags/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-site-verification" content="Gqjxvc7DzSWRpY3nEn9I-hXs1znjtPi7prk3P7PF7Fo" >
<title>Welcome to TriPay</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">

<link rel="icon" href='<c:url value="/resources/images/favicontripay.png"/>'
	  type="image/png" />

<script
		src="<c:url value='/resources/js/jquery.js'/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/datepicker.css"/>">
<script src="<c:url value="/resources/js/datepicker.js"/>"></script>
<script>
	$(function() {
		$( "#dob" ).datepicker({
			format:"yyyy-mm-dd"
		});
	});
</script>
<!-- Optional theme -->
<link rel="stylesheet"
	  href="<c:url value='/resources/css/bootstrap-theme.min.css'/>" type='text/css'>
<link href="<c:url value="/resources/css/style_main.css"/>"
	  rel='stylesheet' type='text/css'>

<link rel="stylesheet"
	  href="<c:url value='/resources/css/bootstrap.min.css'/>" type='text/css'>
<%--
	<link href="<c:url value="/resources/css/css_style.css"/>"
		  rel='stylesheet' type='text/css'>
--%>
<script
		src="<c:url value='/resources/js/bootstrap.js'/>"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/header.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/signup.js"></script>

<style>
	/* .no-js #loader {
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
	.short{
		font-weight:normal;
		color:#FF0000;
		font-size:13px;
	}
	.weak{
		font-weight:normal;
		color:orange;
		font-size:13px;
	}
	.good{
		font-weight:normal;
		color:#2D98F3;
		font-size:13px;
	}
	.strong{
		font-weight:normal;
		color: limegreen;
		font-size:13px;
	} */
</style>
<script src="<c:url value='/resources/js/modernizr.js'/>"></script>

<script type="text/javascript">
	$(window).load(function() {
		$(".se-pre-con").fadeOut("slow");
	});
</script>

</head>
<body style="overflow-x: hidden;">
<div>
	<div class="se-pre-con"></div>
	<!----slider----->
	<div id="myCarousel" class="carousel slide" style="margin-top: -14px; position: fixed; z-index: -1; height: 100%; width: 100%;">
		<!-- Indicators -->

		<!-- Wrapper for Slides -->
		<div class="carousel-inner">
			<div class="item active">
				<!-- Set the first background image using inline CSS below. -->
				<div class="fill" style="background-image:url('/TriPay-web/resources/images/icon_tripay.jpg');"></div>
			</div>
		</div>

		<!-- Controls -->
	</div>

	<!-- /.container -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Script to Activate the Carousel -->
	<script>
		$('.carousel').carousel({
			interval: 5000 //changes the speed
		})
	</script>
	<!-------end slider---------->

	<nav class="navbar navbar-default" style="background: white; min-height: 80px; border-radius: 0; border-color: white; margin-bottom: 10px;">
		<div class="topline"></div>
		<c:if test="${error ne null}">
			<div class="alert alert-danger col-md-12"><center><c:out value="${error}" escapeXml="true" default=""/></center></div>
		</c:if>
		<c:if test="${msg ne null}">
			<div class="alert alert-success col-md-12"><center><c:out value="${msg}" escapeXml="true" default=""/></center></div>
		</c:if>
		<div class="container-fluid" id="header">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><img
						src="/TriPay-web/resources/images/tripaylogo.png" alt="" style="width: 200px"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="container" style="margin-top: 8px;">
				<ul class="nav navbar-nav navbar-right">
					<form id="login"
						  action="<c:url value="/User/Login" />"
						  method="post">
						<div class="group">
							<input type="text" name="username" autocomplete="off"
								   maxlength="10" required> <span class="highlight"></span>
							<span class="bar"></span> <label>Mobile No</label>
						</div>

						<div class="group">
							<input type="password" name="password" id="password_login" autocomplete="off" maxlength="11"
								  required><span class="highlight"></span>
							<span class="bar"></span> <label>Password</label><i class="fa fa-eye-slash" id="login_pwd_eye" style="float: right;margin-top: -25px;"></i>
							<sec:csrfInput/>
							<a href="#" data-toggle="modal" data-target="#forgotPassword" id="forgot_password_modal" style="color:black;">Forgot
								Password? </a>
						</div>
						<button type="submit" class="btn"
								style="margin-top: 12px; margin-left: 20px; background: #ec2029; color: #FFFFFF; border-radius: 0">Login <i class="fa fa-sign-in fa-1x"></i></button>
					</form>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<!-----------------end navbar---------------------->

	<div class="container">
		<div class="col-xs-12 col-sm-6  col-md-8" ></div>

		<!-------signup-------->
		<div class="col-xs-12 col-sm-6 col-md-4"
			 style="background: #FFFFFF;margin-bottom: 40px;border-radius: 8px;-webkit-box-shadow: -1px 0px 19px -1px rgba(0,0,0,0.75);-moz-box-shadow: -1px 0px 19px -1px rgba(0,0,0,0.75);box-shadow: -1px 0px 19px -1px rgba(0,0,0,0.75);" id="signup">
			<h3 style="color: #7e7e7e; font-family: 'Ubuntu', sans-serif;"><center>Register Here !</center></h3>
			<hr style=" border: solid .1px #d6d5d5;margin-top: auto;">
			<p class="error" id="error_contact_no" class="error"></p>
			<form action="#" method="post">
				<div class="group">
					<input type="text" class="numeric" name="contactNo" id="contact_no" minlength="10" maxlength="10" required>
					<span class="highlight"></span>
					<p class="error" id="error_contact_no" class="error"></p>
					<label>Mobile Number</label>
				</div>
				<div class="group" style="margin-bottom: 18px;">
					<input type=password name="password" id="password" minlength="6" maxlength="12" required>
					<i class="fa fa-eye-slash" id="pwd_eye" style="float: right;margin-top: -25px;margin-right: 50px;"></i>
					<p class="error" id="error_password" class="error"></p>
					<!-- <p id="strength_password" ></p> -->
					<span class="highlight"></span> <label>Password</label>

				</div>
				<div class="group">
					<input type="text" name="firstName" id="first_name" required>
					<p class="error" id="error_first_name" class="error"></p>
					<span class="highlight"></span> <label>Name</label>
				</div>
				<div class="group">
					<input type="email" name="email" id="email" required> <span
						class="highlight"></span>
					<p class="error" id="error_email" class="error"></p>
					<label>Email Address</label>
				</div>
				<div class="group">
					<input type="text" name="locationCode" id="pincode" minlength="6" maxlength="6" required> <span
						class="highlight"></span>
					<p class="error" id="error_pincode" class="error"></p>
					<label>Pincode</label>
				</div>
				<div class="group">
					<input id="dob" type="text"  required>
					<p class="error" id="error_dob" class="error"></p>
					<label>Date Of Birth</label>
				</div><!-- <div style="z-index: 99; position: absolute; margin-left: 78%;"><a class="captcha_link"><span class="glyphicon glyphicon-refresh" aria-hidden="true" style="margin-left: 80%;"></span></a></div><br> -->
				<%-- <div class="group" style="margin-top: 0px;">
					<img src="<c:url value="/Captcha"  />"  class="img-responsive captcha_image" style=" margin-top: -15px; position: absolute; width: 130px;"/>
				</div> --%>
				<!-- <div class="group" style="margin-top: 20px;">
					<input id="g-recaptcha-response" type="text" required/>
					<p class="error" id="error_captcha" class="error"></p>
					<label>Enter text shown in image</label>
				</div> -->
				<input type="checkbox" class="check" value="termsConditions" id="termsConditions"
					   style="width: 10%; text-align: left; margin-top: 20px; float: left;">
				<p style="font-size: 12px; margin-top: 12px; margin-bottom: -22px;">
					By clicking submit I hereby agree all the <a
						href="<c:url  value='/Terms&Conditions'/>">terms and
					conditions</a>.
				</p>

				<br>
				<button type="button" class="btn disabled" id="registerButton" style="margin-top: 12px; margin-bottom: 8%; margin-left: 8%; background: #ec2029; color: #FFFFFF; width: 80%;">Sign Up</button>
			</form>
		</div>
		<!----end col----->
	</div>
	<!-------end container------------>
	<!-----modal after Registration Successful -->
	<div id="regMessage" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content text-center">
				<button type="button" data-dismiss="modal" class="close">&times;</button>
				<div class="icon">
					<span class="fa fa-unlock-alt fa-2x" aria-hidden="true"></span>
				</div>
				<h4>OTP Verification</h4>
				<hr style="margin-top: 20px;">

				<div class="group_1">
					<input type="text" name="key" id="verify_reg_otp_key" maxlength="6" placeholder="OTP"
						   required="required"> <span class="highlight"></span>
				</div>
				<div class="group_1">
					<input type="hidden" name="mobileNumber"
						   class="form-control input-sm" id="reg_otp_username"
						   required="required" />
				</div><br><br>
				<div class="group_1">					
					<button class="btn btn-md btncu"
							style="margin-bottom: 5px; margin-left: -10px; margin-right:10px;" id="register_verify_mobile">Verify
						Mobile</button>
						&nbsp&nbsp&nbsp
						<button class="btn btn-md btncu" id="register_resend_otp"
							style="margin-bottom: 5px;margin-left: -20px;">Resend OTP</button>
	
				</div>
		<%-- 		<div class="group_1">
					<div style="z-index: 99; position: absolute; margin-left: 78%;"><a href="#" class="captcha_link"><span class="glyphicon glyphicon-refresh" aria-hidden="true" style="margin-left: 80%;"></span></a></div><br>

					<img src="<c:url value="/Captcha"/>" class="captcha_image" height="50"/>
				</div>
 --%<%-- >				<div class="group_1">
					<input id="g-recaptcha-response-1" type="text" required style="margin-top: -25px;"/>
					<p class="error" id="error_captcha1" class="error"></p>
					<label>Enter text shown in image</label>
				</div>
	 --%><!-- 			<div class="group_1">
					<button class="btn btn-md btncu" id="register_resend_otp"
							style="margin-bottom: 5px;margin-left: -20px;">Resend OTP</button>
				</div>
 -->
				<div class="modal-footer">
					<div class="alert alert-success" id="regMessage_success"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal after successful verification -->
	<div id="verifiedMessage" role="dialog" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h5>Verification Successful</h5>
				</div>
				<div class="modal-body">
					<center id="success_verification_message"
							class="alert alert-success"></center>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal after error verification -->
	<div id="errorMessage" role="dialog" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<center id="error_message"
							class="alert alert-danger"></center>
				</div>
			</div>
		</div>
	</div>


	<!-- Modal for forgot password -->
	<div class="modal fade" role="dialog" id="forgotPassword">
		<div class="modal-dialog modal-sm">
			<div class="modal-content text-center" style="width: 120%;">
				<button type="button" data-dismiss="modal" class="close">&times;</button>
				<div class="icon">
					<i class="fa fa-unlock-alt fa-2x" aria-hidden="true"></i>
				</div>
				<h4>Forgot Password</h4>
				<hr style="margin-top: 20px;">

				<form style="    margin-left: 30px;">
					<div class="group_1">
						<input type="text" name="username" id="fp_username" maxlength="10" placeholder="Mobile Number"
							   required="required" autofocus>
						<p class="error" id="fp_error_username"></p></div>
					<!-- <div style="z-index: 99; position: absolute; margin-left: 78%;"><a class="captcha_link"><span class="glyphicon glyphicon-refresh" aria-hidden="true" style="margin-left: 80%;"></span></a></div><br>


					<img src="/Captcha" height="80" class="captcha_image"/> <br/> -->	
					<!-- <div class="group_1">
					<input id="g-recaptcha-response-2" placeholder="Enter text shown in image" type="text" required/>
					<p class="error" id="error_captcha2" class="error"></p>
				</div> -->
				</form>
				<div class="col-md-6"
					 style="float: none; margin-left: auto; margin-right: auto;">
					<button class="btn btn-sm btn-block btncu" type="button" style="margin-bottom: 20px; margin-top: 10px;"
							id="forgot_password_request">Continue</button>
				</div>

			</div>
		</div>
	</div>


	<div id="successNotification" role="dialog" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h5></h5>
				</div>
				<div class="modal-body">
					<center id="success_alert"
							class="alert alert-success"></center>
				</div>
			</div>
		</div>
	</div>



	<!-- modal OTP for forget password -->
	<div class="modal fade" role="dialog" id="fpOTP">
		<div class="modal-dialog modal-sm">
			<div class="modal-content text-center">
				<button type="button" data-dismiss="modal" class="close">&times;</button>
				<div class="icon">
					<i class="fa fa-unlock-alt fa-2x" aria-hidden="true"></i>
				</div>
				<h4>Change Password</h4>
				<hr style="margin-top: 20px;">
				<input type="hidden" name="mobileNumber" id="fpusername_forgot" maxlength="11"
					   class="form-control input-sm" value="" required/>

				<div class="group_1" style="margin-left: 20px;">
					<input type="text" name=key id="fpOTP_key" placeholder="OTP" maxlength="6" required/>
				</div>
				<div class="group_1" style="margin-left: 20px;">
					<input type="password" name=key id="fpnewPassword_key" minlength="6" maxlength="12"  placeholder="New Password" required/>
				<p class="error" id="error_fpPassword" class="error"></p>
				</div>
				<div class="group_1" style="margin-left: 20px;">
					<input type="password" name=key id="fpconfirmPassword_key"  minlength="6" maxlength="11"  placeholder="Confirm Password" required/>
				</div><br/>
				<center id="fpOTP_message"></center>
				<div class="group_1">
					<center><button class="btn  btn-md  btncu" style="float: left" type="button" id="process_forgot_password_request">Continue</button></center><br>
				
				</div><br><br>
<!-- 				<br><div style="z-index: 99; position: absolute; margin-left: 78%;"><a class="captcha_link"><span class="glyphicon glyphicon-refresh" aria-hidden="true" style="margin-left: 80%;"></span></a></div><br>

				<img src="/Captcha" height="50" class="captcha_image"/> <br/>

				<div class="group_1" style="margin-left: 20px;">
					<input id="g-recaptcha-response-3" type="text" placeholder="Enter text shown in image" required/>
					<p class="error" id="error_captcha3" class="error"></p>
				</div>

				<div class="form-group col-md-6"
					 style="float: none;">
					<button type="button" class="btn  btn-md btn-block btncu"
							id="forgot_password_resend_otp" style="margin-bottom: 5px">Resend
						OTP</button>
				</div>
 -->			</div>
	<!-- 		<div class="modal-footer">
				
			</div>
	 -->	</div>
	</div>
</div>



<div class="navbar navbar-fixed-bottom" id="footer">
	<div class="container-fluid">
	<%-- 	<a href="AboutUs">About Us</a> <a href="PartnersWithUs">Partner
		with us</a> <a href="<c:url value='/Campaign'/>"> Campaign</a> <a href="Terms&Conditions">Terms & Conditions</a>  <a href="Grievance">Grievance
		policy</a>
		<div class="span pull-left">
			<a href="#"><img src="resources/images/vijaya_logo.png"
							 style="height: 30px;"></a>
		</div>
		<div class="span pull-right">
			<p> &copy; Copyright MSewa Software Pvt. Ltd.</p>
		</div> --%>
	</div>
</div>
<!-- Latest compiled and minified JavaScript -->
</body>
</html>