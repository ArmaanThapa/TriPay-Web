<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
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
<title>Welcome to VPayQwik</title>
<!-- Latest compiled and minified CSS -->
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>'
	type="image/png" />

<!-- Optional theme -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap-theme.min.css'/>" type='text/css'>

<link href="<c:url value="/resources/css/style_main.css"/>"
	rel='stylesheet' type='text/css'>
<link href="<c:url value="/resources/css/css_style.css"/>"
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.min.css'/>" type='text/css'>
<script
	src="<c:url value='/resources/js/jquery.js'/>"></script>
<script
	src="<c:url value='/resources/js/bootstrap.js'/>"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/header.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/signup.js"></script>
<script src="https://www.google.com/recaptcha/api.js?onload=myCallBack&render=explicit"  async defer></script>
   <script>
      var recaptcha1;
      var recaptcha2;
      var recaptcha3;
      var recaptcha4;
      var myCallBack = function() {
    	  console.log("callback called");
        recaptcha1 = grecaptcha.render('recaptcha1', {
          'sitekey' : '6Let5SETAAAAAJ_G4499RIEKYc85RODmHdLV7xD3', //Replace this with your Site key
          'theme' : 'light'
        });
        
        recaptcha2 = grecaptcha.render('recaptcha2', {
          'sitekey' : '6Let5SETAAAAAJ_G4499RIEKYc85RODmHdLV7xD3', //Replace this with your Site key
          'theme' : 'light'
        });
        
        recaptcha3 = grecaptcha.render('recaptcha3', {
            'sitekey' : '6Let5SETAAAAAJ_G4499RIEKYc85RODmHdLV7xD3', //Replace this with your Site key
            'theme' : 'light'
          });
        
        recaptcha4 = grecaptcha.render('recaptcha4', {
            'sitekey' : '6Let5SETAAAAAJ_G4499RIEKYc85RODmHdLV7xD3', //Replace this with your Site key
            'theme' : 'light'
          });
      };
    </script>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top"
		style="background: white; min-height: 80px; border-radius: 0; border-color: white; margin-bottom: 15px;">
		<div class="topline"></div>
		<div class="container" id="header">
<c:if test="${error ne null}">
<div class="alert alert-danger col-md-12"><center>${error}</center></div>
</c:if>	
<c:if test="${msge ne null}">
<div class="alert alert-success col-md-12"><center>${msge}</center></div>
</c:if>	
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><img
					src="resources/images/vijayalogo.png" alt="" style="width: 250px"></a>
			</div>
			
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="container" style="margin-top: 8px;">
				<ul class="nav navbar-nav navbar-right">
					<p>${msg}</p>
					<p></p>
					<form id="login"
						action="<c:url value="/User/Login" />"
						method="post">
						<div class="group">
							<input type="text" name="username" autocomplete="off"
								maxlength="10" required> <span class="highlight"></span>
							<span class="bar"></span> <label>Mobile No</label>
						</div>

						<div class="group">
							<input type="password" name="password" autocomplete="off"
								maxlength="50" required> <span class="highlight"></span>
							<span class="bar"></span> <label>Password</label> 
							<sec:csrfInput/>
							<input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}"/>
							<a href="#"
								data-toggle="modal" data-target="#forgotPassword">Forgot
								Password? </a>
						</div>
						<button type="submit" class="btn"
							style="margin-top: 12px; margin-left: 20px; background: #ec2029; color: #FFFFFF; border-radius: 0">Submit</button>
					</form>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<!-----------------end navbar---------------------->

	<div class="container">
		<div class="col-xs-12 col-sm-6  col-md-8 " style="height: 10em"></div>

		<!-------signup-------->
		<div class="col-xs-12 col-sm-6  col-md-4 "
			style="background: #FFFFFF; height: 680px; margin-bottom: 80px; margin-top: 9%;"
			id="signup">

			<h3 style="color: #7e7e7e;">Sign Up</h3>
			<hr style="border: solid .1px #949494;">

			<form id="signup" action="#" method="post">
				<div class="group">
					<input type="text" name="firstName" id="first_name" required>
					<p class="error" id="error_first_name" class="error"></p>
					<span class="highlight"></span> <label>First Name</label>
				</div>

				<div class="group">
					<input type="text" name="lastName" id="last_name" required>
					<p class="error" id="error_last_name" class="error"></p>
					<span class="highlight"></span> <label>Last Name</label>
				</div>

				<div class="group">
					<input type=password name="password" id="password" required>
					<p class="error" id="error_password" class="error"></p>
					<span class="highlight"></span> <label>Password</label>
				</div>

				<div class="group">
					<input type="password" name="confirmPassword" id="confirm_password"
						required>
					<p class="error" id="error_confirm_password" class="error"></p>
					<span class="highlight"></span> <label>Confirm Password</label>
				</div>

				<div class="group">
					<input type="text" name="contactNo" id="contact_no" required>
					<span class="highlight"></span>
					<p class="error" id="error_contact_no" class="error"></p>
					<label>Mobile Number</label>
				</div>

				<div class="group" style="margin-bottom: 30px;">
					<input type="email" name="email" id="email" required> <span
						class="highlight"></span>
					<p class="error" id="error_email" class="error"></p>
					<label>Email Address</label>
				</div>


				<div class="group">
					<select name="gender" id="gender" class="form-control"
						style="border-radius: 0; width: 86%; border: transparent; border-bottom: gray; border-style: solid; border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; margin-bottom: 10px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
						<option value="M">Male</option>
						<option value="F">Female</option>

					</select>
					<p class="error" id="error_gender" class="error"></p>
				</div>

				<div class="group" style="margin-top: -10px;">
					<input id="dob" type="date" max="1999-12-31">
					<p class="error" id="error_dob" class="error"></p>

				</div><br>
		<div class="group" style="margin-top: -10px;">
 				<div id="recaptcha1"></div>	</div>	
				<input type="checkbox" class="check" value="termsConditions"
					id="termsConditions"
					style="width: 10%; text-align: left; margin-top: 20px; float: left; margin-right:;">
				<p style="font-size: 12px; margin-top: 12px; margin-bottom: -22px;">
					By clicking submit I hereby agree all the <a
						href="<c:url  value='/Terms&Conditions'/>">terms and
						conditions</a>.
				</p>
				<br>
				<button type="button" class="btn disabled" id="registerButton"
					style="margin-top: 12px; margin-bottom: 2%; margin-left: 8%; background: #ec2029; color: #FFFFFF; width: 80%;">Submit</button>
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
					<i class="fa fa-unlock-alt fa-2x" aria-hidden="true"></i>
				</div>
				<h4>OTP Verification</h4>
				<hr style="margin-top: 20px;">

				<div class="group_1">
					<input type="text" name="key" id="verify_reg_otp_key"
						required="required"> <span class="highlight"></span> <label>OTP</label>
				</div>
				<div class="group_1">
					<input type="hidden" name="mobileNumber"
						class="form-control input-sm" id="reg_otp_username"
						required="required" />
				</div>
				<div class="col-md-6"
					style="float: none; margin-left: auto; margin-right: auto;">
					<button class="btn btn-md btn-block btncu"
						style="margin-bottom: 5px" id="register_verify_mobile">Verify
						Mobile</button>
				</div>
				<div class="form-group col-md-6"
					style="float: none; margin-left: auto; margin-right: auto;">
					<center><div id="recaptcha2"></div></center>					
					<button class="btn btn-md btn-block btncu" id="register_resend_otp"
						style="margin-bottom: 5px">Resend OTP</button>
				</div>
			</div>
			<div class="modal-footer">
				<center id="regMessage_success"></center>
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


				<div class="group_1">
					<input type="text" name="username" id="fp_username"
						required="required" autofocus> <span class="highlight"></span>
					<label>Mobile Number</label>
					<p class="error" id="fp_error_username"></p>
				</div>
		<center><div id="recaptcha3"></div>	<p class="error" id="fp_error_captcha"></p></center>	
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
				<input type="hidden" name="mobileNumber" id="fpusername_forgot"
					class="form-control input-sm" value="" />

				<div class="group_1">
					<input type="text" name=key id="fpOTP_key" autofocus /> <span
						class="highlight"></span><label>OTP</label>
				</div>
				<div class="group_1">
					<input type="password" name=key id="fpnewPassword_key" /> <span
						class="highlight"></span><label>New Password</label>
				</div>
				<div class="group_1">
					<input type="password" name=key id="fpconfirmPassword_key" /> <span
						class="highlight"></span><label>Confirm New Password</label>
				</div>

				<button class="btn  btn-md  btncu" style="float: left" type="button"
					style="margin-bottom: 5px" id="process_forgot_password_request">Continue</button>

				<div class="form-group col-md-6"
					style="float: none; margin-left: auto;">
				 <center><div id="recaptcha4"></div></center>
					<button type="button" class="btn  btn-md btn-block btncu"
						id="forgot_password_resend_otp" style="margin-bottom: 5px">Resend
						OTP</button>
				</div>
			</div>
			<div class="modal-footer">
				<center id="fpOTP_message"></center>
			</div>
		</div>
	</div>
	</div>



	<div class="navbar navbar-fixed-bottom" id="footer">
		<div class="container-fluid">
			<a href="AboutUs">About Us</a> <a href="PartnersWithUs">Partner
				with us</a> <a href="Terms&Conditions">Terms & Conditions</a> <a
				href="#">Customer service</a> <a href="Grievance">Grievance
				policy</a> <a href="#">Recharge Partners</a>
			<div class="span pull-left"">
				<a href="#"><img src="resources/images/vijaya_logo.png"
					style="height: 30px;"></a>
			</div>
			<div class="span pull-right">
				<p>© Copyright MSewa Software Pvt. Ltd.</p>
			</div>
		</div>
	</div>

	<!-- Latest compiled and minified JavaScript -->
</body>
</html>



<!-- ========================================================================================================================== -->