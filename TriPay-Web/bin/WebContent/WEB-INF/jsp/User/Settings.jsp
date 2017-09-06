<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
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
<link href='<c:url value='/resources/css/font-family.min.css'/>'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="<c:url value='/resources/css/font-awesome.min.css'/>" type='text/css'>

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
	src="${pageContext.request.contextPath}/resources/js/userdetails.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/header.js"></script>
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
	background: url(../images/pq.gif) center no-repeat #fff;
}
</style>
<script src="<c:url value="/resources/js/spinner.js" />"></script>
	
</head>

<body
	onLoad="ActiveMenu('MobileTopup');ActiveSubmenu('${menu}');ActiveFadeIn('${menu}');SelectOperator();">
<!-- 	<div class="se-pre-con"></div> -->	
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
				<div class="box hidden-xs"></div>

				<ul class="nav nav-pills">
					<li class="active"><a data-toggle="pill" href="#home">Edit
							Profile</a></li>
					<li><a data-toggle="pill" href="#menu1">Change Password</a></li>
				</ul>

				<div class="tab-content" id="sendmoney">
					<div id="home" class="tab-pane fade in active">
						<form modelAttribute="editUser" method="post"
							action="${pageContext.request.contextPath}/User/EditProfile/Process">

							<div class="group_1">
								<input name="firstName" type="text" value="${user.userDetail.firstName}" required="required" />
								<span class="highlight"></span> <span class="bar"></span> <label>First
									Name</label>
								<p class="error">${error.firstName}</p>
							</div>
							<div class="group_1">
								<input name="lastName" type="text" value="${user.userDetail.lastName}" required="required" />
								<span class="highlight"></span> <span class="bar"></span> <label>Last
									Name</label>
								<p class="error">${error.lastName}</p>
							</div>
							<div class="group_1">
								<input type="text" name="address" value="${user.userDetail.address}" required="required" />

								<span class="highlight"></span> <span class="bar"></span> <label>Address</label>
								<p class="error">${error.address}</p>
							</div>

							<div class="group_1">
								<input type="email"  value="${user.userDetail.email}"  name="email" required="required" />

								<span class="highlight"></span> <span class="bar"></span> <label>Email</label>
								<p class="error">${error.email}</p>
							</div>

							<button type="submit" class="btn"
								style="width: 80%; background: #ff0000; color: #FFFFFF;">Update</button>

						</form>
					</div>
					
					<div id="menu1" class="tab-pane fade">
					<code>${message}</code>
						<form method="post"
							action="${pageContext.request.contextPath}/User/UpdatePassword/Process">
							${msg}
							<div class="group_1">
								<input type="password" name="oldPassword" required> <span
									class="highlight"></span> <span class="bar"></span> <label>Current
									Password</label>
								<p>${cherror.password}</p>
							</div>

							<div class="group_1">
								<input type="password" name="newPassword" required> <span
									class="highlight"></span> <span class="bar"></span> <label>New
									Password</label>
								<p>${cherror.newPassword}</p>
							</div>

							<div class="group_1">
								<input type="password" name="confirmPassword" required>
								<span class="highlight"></span> <span class="bar"></span> <label>Confirm
									New Password</label>
								<p>${cherror.confirmPassword}</p>
							</div>


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
									style="width:">
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
		src='<c:url value="/resources/js/wow.js"/>' /></script>
	<script>
		new WOW().init();
	</script>
<script
	src="<c:url value='/resources/js/bootstrap.js'/>"></script>

</body>
</html>



