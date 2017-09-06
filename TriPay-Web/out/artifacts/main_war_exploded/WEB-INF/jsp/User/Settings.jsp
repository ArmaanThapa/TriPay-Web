<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
<meta charset="utf-8">
<sec:csrfMetaTags/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>VPayQwik | Settings</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">
	<link href='<c:url value='/resources/css/font-family.css'/>'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="<c:url value='/resources/css/font-awesome.css'/>" type='text/css'>

<!-- Optional theme -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap-theme.min.css'/>" type='text/css'>

<link href="<c:url value="/resources/css/css_style.css"/>"
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.css'/>" type='text/css'>
<script
	src="<c:url value='/resources/js/jquery.js'/>"></script>
<script
	src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/userdetails.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/header.js"/>"></script>
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>'
	type="image/png" />
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

<body>
	<div class="se-pre-con"></div>
    <jsp:include page="/WEB-INF/jsp/User/Header.jsp" />
	<jsp:include page="/WEB-INF/jsp/User/Menu.jsp" />

	<!-----------------end navbar---------------------->

	<!------------- end main-------------------->

	<div class="background"></div>
	<!---blue box---->

	<div class="container" id="money_box">
		<div class="row">
			<div class="col-md-4" id="Prepaid">
				<!---form---->
				<div class="box hidden-xs">
				</div>
				<ul class="nav nav-pills">
					<li class="active"><a data-toggle="pill" href="#home"> <i class="fa fa-pencil-square"></i> Edit
							Profile</a></li>
					<li><a data-toggle="pill" href="#menu1"><i class="fa fa-link"></i> Change Password</a></li>
				</ul>
				<div class="tab-content" id="sendmoney">
					<div id="home" class="tab-pane fade in active"><br>
						<form modelAttribute="editUser" method="post"
							action="${pageContext.request.contextPath}/User/EditProfile/Process">

							<div class="group_1">
								<input name="firstName" type="text" id ="first_name_ep" required="required" />
								<span class="highlight"></span> <span class="bar"></span> <label>First
									Name</label>
								<p class="error" id="error_first_name"></p>
							</div>
							<div class="group_1">
								<input name="lastName" type="text" id="last_name_ep" required="required" />
								<span class="highlight"></span> <span class="bar"></span> <label>Last
									Name</label>
								<p class="error" id="error_last_name"></p>
							</div>
							<div class="group_1">
								<input type="text" name="address"  id="address" required="required"/>

								<span class="highlight"></span> <span class="bar"></span> <label>Address</label>
								<p class="error" id="error_address"></p>
							</div>

							<div class="group_1" id="email_ep">

								<input type="email" name="email"  id="email" required="required"/>
								<span class="highlight"></span> <span class="bar"></span> <label>Email</label>
								<p class="error" id="error_email"></p>
							</div>
							<sec:csrfInput/>
							<button type="submit" class="btn" id="esubmit"
								style="width: 80%; background: #ff0000; color: #FFFFFF;">Update</button>

						</form>
					</div>
					
					<div id="menu1" class="tab-pane fade"><br>
					<code>${message}</code>
						<form method="post"
							action="${pageContext.request.contextPath}/User/UpdatePassword/Process">
							${msg}

							<div class="group_1">
								<input type="password" id="current" name="oldPassword"  minlength="6" maxlength="6" required>
								<i class="fa fa-eye-slash" id="pwd_eye" style="float: right;margin-top: -25px;margin-right: 50px;"></i>
								<span class="highlight"></span> <span class="bar"></span> <label>Current
									Password</label>
								<p>${cherror.password}</p>
							</div>

							<div class="group_1">
								<input type="password" id="new" name="newPassword" minlength="6" maxlength="6" required><i class="fa fa-eye-slash" id="new_pwd_eye" style="float: right;margin-top: -25px;margin-right: 50px;"></i> <span
									class="highlight"></span> <span class="bar"></span> <label>New
									Password</label>
								<p>${cherror.newPassword}</p>
							</div>

							<div class="group_1">
								<input type="password" id="confirm_new" name="confirmPassword" minlength="6" maxlength="6" required>
								<i class="fa fa-eye-slash" id="cnf_pwd_eye" style="float: right;margin-top: -25px;margin-right: 50px;"></i>
								<span class="highlight"></span> <span class="bar"></span> <label>Confirm
									New Password</label>
								<p>${cherror.confirmPassword}</p>
							</div>

								<sec:csrfInput/>
							<button type="submit" class="btn"
								style="width: 80%; background: #ff0000; color: #FFFFFF;">Update Password</button>
						</form>
					</div>
				</div>

			</div>
			<!----end col-md-4-->

			<div class="col-md-8 hidden-xs">
				<div class="slider" style="margin-right: -15px; margin-left: -15px;">
					<div class="carousel slide hidden-xs" data-ride="carousel"
						id="mycarousel">
						<ol class="carousel-indicators">
							<li class="active" data-slide-to="0" data-target="#mycarousel"></li>
							<li data-slide-to="1" data-target="#mycarousel"></li>
						</ol>

						<div class="carousel-inner">

							<div class="item active" id="slide1">
								<img src='<c:url value="/resources/images/slider_5.jpg"/>' />
							</div>
							<!---end item---->

							<div class="item">

								<img src='<c:url value="/resources/images/slider_6.jpg"/>'
									/>
							</div>
							<!---end item---->
						</div>
						<!--end carousel inner------>

					</div>
					<!---end caeousel slider---->
				</div>
				<!---end slider----->
			</div>
		</div>
		<!-----end col-md-8-->
	</div>
	<!---end row-->
	<!----end container-->
	<jsp:include page="/WEB-INF/jsp/User/Footer.jsp" />
	<script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>
	<script type="text/javascript"
		src='<c:url value="/resources/js/wow.js"/>' />
	<script>
		new WOW().init();
	</script>
<script
	src="<c:url value='/resources/js/bootstrap.js'/>"></script>

</body>
</html>



