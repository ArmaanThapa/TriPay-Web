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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<sec:csrfMetaTags/>
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>VPayQwik | Fund Transfer</title>
<link href='<c:url value='/resources/css/font-family.css'/>'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="<c:url value='/resources/css/font-awesome.min.css'/>" type='text/css'>

<!-- Optional theme -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap-theme.min.css'/>" type='text/css'>

<link href="<c:url value="/resources/css/css_style.css"/>"
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.min.css'/>" type='text/css'>
<script
	src="<c:url value='/resources/js/jquery.js'/>"></script>
<script
	src="<c:url value='/resources/js/bootstrap.js'/>"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/userdetails.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/header.js"></script>
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>' type="image/png" />


<!-- Latest compiled and minified CSS -->

<link href='<c:url value="/resources/css/css_style.css"/>'
	rel='stylesheet' type='text/css'>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/sendmoney.js"></script>
 

</head>

<body onLoad="ActiveMenu('MobileTopup');ActiveSubmenu('${menu}');ActiveFadeIn('${menu}');">
<!-- <div class="se-pre-con"></div> -->
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
					<li class="active"><a data-toggle="pill" href="#home">Mobile</a></li>
					<li><a data-toggle="pill" href="#menu1">Bank Account</a></li>
				</ul>
				
				<div class="tab-content" id="sendmoney">
				<p id="error_mobile_sm"></p>
				<p id="success_mobile_sm"></p>
					<div id="home" class="tab-pane fade in active">
						<form method="post" action="#">
							
							<div class="group_1">
								<input type="text" name="mobileNumber" maxlength="10" id="smm_mobile"/>
								<span class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_smm_mobile"></p>
								<label>Mobile No</label>
							</div>

							<div class="group_1">
								<input type="text" name="amount" min="10" id="smm_amount"/> <span
									class="highlight"></span> <span class="bar"></span>
								<p id="error_smm_amount" class="error"></p>
								<label>Amount</label>
							</div>

							<button type="button" class="btn" id="smm_submit"
								style="width: 80%; background: #ff0000; margin-top: 10px; color: #FFFFFF;">Send</button>
						</form>
					</div>

					<div id="menu1" class="tab-pane fade">
						<form method="post"
							action="#">
							<div class="group_1">
								<input type="text" required> <span class="highlight"></span>
								<span class="bar"></span> <label>Select Bank</label>
							</div>

							<div class="group_1">
								<input type="text" required> <span class="highlight"></span>
								<span class="bar"></span> <label>Account No</label>
							</div>

							<div class="group_1">
								<input type="text" required> <span class="highlight"></span>
								<span class="bar"></span> <label>Account Holder Name</label>
							</div>

							<div class="group_1">
								<input type="text" required> <span class="highlight"></span>
								<span class="bar"></span> <label>IFSC Code</label>
							</div>

							<div class="group_1">
								<input type="text" required> <span class="highlight"></span>
								<span class="bar"></span> <label>Amount</label>
							</div>
							<button type="button" class="btn" disabled ="disabled"
								style="width: 80%; background: #ff0000; color: #FFFFFF;">Coming Soon</button>
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
		<!-- Latest compiled and minified JavaScript -->
	<script
	src="<c:url value='/resources/js/bootstrap.js'/>"></script>
</body>
</html>



