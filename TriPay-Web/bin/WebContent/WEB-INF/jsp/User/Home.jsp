<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage="" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<sec:csrfMetaTags/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>VPayQwik | Dashboard</title>

<!-- Optional theme -->

<script
	src="<c:url value='/resources/js/jquery.js'/>"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap-theme.min.css'/>" type='text/css'>

<link href="<c:url value="/resources/css/css_style.css"/>"
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.min.css'/>" type='text/css'>
<script
	src="<c:url value='/resources/js/bootstrap.js'/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/aj.js"/>" ></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/header.js"></script>
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>'
	type="image/png" />
<script
	src="<c:url value='/resources/js/modernizr.js'/>"></script>
<script src="<c:url value='/resources/js/spinner.js'/>"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/userdetails.js"></script>
<style>
#icon span{

	color: #17bcc8;
	margin-right: 8px;
	}

</style>
</head>

<%-- <body
	onLoad="ActiveMenu('MobileTopup');ActiveSubmenu('${menu}');ActiveFadeIn('${menu}');"> --%>
	<body>
	<!-- <div class="se-pre-con"></div> -->

	<jsp:include page="/WEB-INF/jsp/User/Header.jsp" />
	<jsp:include page="/WEB-INF/jsp/User/Menu.jsp" />

	<!-----------------end navbar---------------------->

	<!------------- end main-------------------->

	<div class="background"></div>
	<!---blue box---->
	<div class="container" id="load">
		<div class="row"">
			<div class="container" id="Account">
				<div class="row" id="foto">
					<div class="col-md-6 text-center">
						<div><img id="image" src=""></div>
						<!-- Your foto -->
						<div class="foto">
						
							<a id="profilePic" href="#" data-toggle="modal"
								data-target="#profilePicture" > 
									<img id="display_pic" width="230px" height="230px" />
							</a>
							<div id="profilePicture" class="modal fade" role="dialog">
								<div class="modal-dialog modal-sm">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h5 class="modal-title">Upload Picture</h5>
										</div>
										<div class="modal-body">
											<form method="post"
												action="${pageContext.request.contextPath}/User/UploadPicture/"
												enctype="multipart/form-data"   class="form form-inline">
												
												<input type="file" class="form-control" accept="image/*"
													style="width: 86%; background: #EDEDED;"
													 name="profilePicture" required/>
												<sec:csrfInput/>
												<button type="submit" class="btn btn-primary">Upload</button>

											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- end your foto -->
						<hr style="width: 50%; margin-top: 5px;">
						<h1 class="title">Welcome</h1>
						<!-- Your Profession -->
						<h3 class="sub-title" id="display_first_last_name"></h3>
					</div>

					<div class="col-md-5">
						<div>
							<div>
								<h4 class="">PROFILE DETAILS</h4>
								<p class="float">Mobile Number :
								<p id="user_mobile"></p>
								</p>
								<p class="float">Email :
								<p id="user_email"></p>
								</p>

								<h4 class="">ACCOUNT DETAILS</h4>
								<p class="float">Account Number :
								<p id="account_number"></p>
								</p>
								<p class="float">Balance : Rs 
								<p id="user_balance"></p>
								</p>
								<h4 class="">ACCOUNT TYPE</h4>
								<p class="float">Type :
								<p id="account_type"></p>
								</p>
								<p class="float">Daily Transaction Limit : Rs.
								<p id="daily_limit"></p>
								</p>
								<p class="float">Monthly Transaction Limit : Rs.
								<p id="monthly_limit"></p>
								</p>
							</div>
						</div>

					</div>
					<!---end col-md-5-->
				</div>
				<!---end foto-->
			</div>
			<!--div account-->
		</div>
		<!---end row --->
	</div>
	<!---- end aboutus-->
	<div id="verification_alert" role="dialog" class="modal fade">
		<div class="modal-dialog dialog-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h5><center><b><u>Email Verification</u></b></center></h5>
				</div>
				<div class="modal-body">
					<center id="alert_verification_message"
						class="alert alert-danger"></center>
						<center><h6><form action="#" method="post">
<button type="button" class="btn" id="re_send_otp" 
		style="width: 20%; background: #ff0000; margin-top: 10px; color: #FFFFFF;">Resend</button></form></h6></center>
				</div>
			</div>
		</div>
	</div>



	<jsp:include page="/WEB-INF/jsp/User/Footer.jsp" />

	<script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>
	<script type="text/javascript"
		src='<c:url value="/resources/js/wow.js"/>' /></script>
	<script>
		new WOW().init();
	</script>
	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>
</body>
</html>



