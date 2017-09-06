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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>VPayQwik | Login</title>
<!-- Latest compiled and minified CSS -->
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>'
	type="image/png" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.2/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">

<%-- <link href="<c:url value="/resources/css/style_main.css"/>"
	rel='stylesheet' type='text/css'> --%>
<link href="<c:url value="/resources/css/css_style.css"/>"
	rel='stylesheet' type='text/css'>
<link href="<c:url value="/resources/css/admin.css"/>" rel='stylesheet'
	type='text/css'>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/header.js"></script>
<body onload="processMessage('${msg}');">
	<div class="oneset">
		<nav class="navbar navbar-default"
			style="background: white; min-height: 80px; border-radius: 0; border-color: white; margin-bottom: 15px;">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="#"><img
						src="${pageContext.request.contextPath}/resources/images/vijayalogo.png"
						alt="" style="width: 250px"></a> <c:out value="${UserName}" default=""/> 
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="container" style="margin-top: 8px;">
					<ul class="nav navbar-nav navbar-right">
						<form id="login"
							action="${pageContext.request.contextPath}/Admin/Home"
							method="post">
							<div class="group">
								<input type="text" name="username" autocomplete="off"> <span
									class="highlight"></span> <span class="bar"></span> <label>Username
								</label>
							</div>

							<div class="group">
								<input type="password" name="password" autocomplete="off"
									maxlength="50" required> <span class="highlight"></span>
								<span class="bar"></span> <label>Password</label> 
								<sec:csrfInput/>
								<a href="#"
									data-toggle="modal" style="color: white;"
									data-target="#forgotPassword">Forgot Password? </a>
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

		
</div>

<div class="navbar navbar-fixed-bottom" id="footer" style="background: rgba(255, 255, 255, 0.44)">
		<div class="container-fluid">			
			<div class="span pull-left"">
				<a href="#"><img
							src="${pageContext.request.contextPath}/resources/images/vijayafooter2.jpg"
							style="height: 30px; margin-top: 10px;"></a></a>
			</div>
			<div class="span pull-right">
			<p><img
							src="${pageContext.request.contextPath}/resources/images/msewafooter.png"
							style="height: 40px; margin-top: 10px;">© Copyright MSewa Software Pvt. Ltd.</p>
			</div>
		</div>
	</div>

</body>
</html>