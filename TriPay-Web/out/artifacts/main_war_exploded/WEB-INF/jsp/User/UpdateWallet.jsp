<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage="" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<sec:csrfMetaTags/>
<title>VPayQwik | Update Wallet</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">

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
	src="<c:url value="/resources/js/header.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/userdetails.js"/>"></script>
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>'
	type="image/png" />
<!-- Latest compiled and minified CSS -->

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
	<%--<div class="se-pre-con"></div>--%>
	<jsp:include page="/WEB-INF/jsp/User/Header.jsp" />
	<jsp:include page="/WEB-INF/jsp/User/Menu.jsp" />

	<!-----------------end navbar---------------------->

	<!------------- end main-------------------->

	<div class="background"></div>
	<!---blue box---->

	<div class="container" id="UpdateWallet">
		<div class="row" style="background: #f0f0f0;">
			<div class="col-md-12">
				<center><h3><b>Upgrade Your Wallet</b></h3></center>
				<h4>Say goodbye to the Rs.10,000 monthly wallet limit.</h4>
				<br>
				<ul>
					<li>Your new wallet balance can be extended to Rs. 1,00,000/- at any point in time (Available in Select Cities Only)</li>
					<li>Want to make a single transaction of more than Rs. 50,000? Upload your Pan Card details too!</li>
					<li>We have exclusive offers in store for users who upgrade their wallets.</li>
				</ul>
				<br>
				<center><h4><b>Upload Documents</b></h4><br></center>
			</div><!---col-md-12-->
		</div><!--row-->
		<div class="row"><br>
			<h4 style="margin-left: 20px; color: #8c8c8c;"><b>How It Works</b></h4>
			<br>
			<div class="col-sm-4">
				<ul>
					<li style="color: #797979;">Email your ID proof / Address proof <br/> at <a href="mailto:care@vpayqwik.com">care@vpayqwik.com</a></li>
				</ul><br>
				<img src="<c:url value='/resources/images/email.svg'/>" width="120px" style="margin-left:22%;">
			</div>

			<div class="col-sm-3">
				<ul>
					<li style="color: #797979;">We’ll verify these documents.</li>
				</ul>
				<img src="<c:url value='/resources/images/pencil.jpg'/>" width="140px" style="margin-left:20%;">
			</div>
			<div class="col-sm-5">
				<ul>
					<li style="color: #797979;">Your wallet will be upgraded after successful verification.</li>
				</ul>
				<img src="<c:url value='/resources/images/wallet.svg'/>" width="200px" style="margin-left:20%; margin-bottom: 30px;">
			</div>
		</div>
	</div><!-------END BOX----------->


	<jsp:include page="/WEB-INF/jsp/User/Footer.jsp" />

</body>
</html>
<!-- ========================================================================================================================== -->

