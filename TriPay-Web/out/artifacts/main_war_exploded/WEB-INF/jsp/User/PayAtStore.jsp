
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
<meta name="viewport" content="width=device-width, initial-scale=1">
<sec:csrfMetaTags/>
<title>VPayQwik | Pay @ Store</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">

	<link href='<c:url value='/resources/css/font-family.css'/>'
		  rel='stylesheet' type='text/css'>


	<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>'
	type="image/png" />

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
	src="<c:url value="/resources/js/userdetails.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/header.js"/>"></script>

<link href='<c:url value="/resources/css/css_style.css"/>'
	rel='stylesheet' type='text/css'/>
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
<body style="overflow-x: hidden;">
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
				<div class="box hidden-xs"></div>

				<ul class="nav nav-pills">
					<li class="active"><a data-toggle="pill" href="#home"> <i class="fa fa-university"></i> Pay
							@ Store</a></li>
				</ul>

				<div class="tab-content" id="sendmoney">

					<code style="color: red"><c:out value="${messagePayAtStore}" default="" escapeXml="true"/></code>
					<div id="home" class="tab-pane fade in active">
	
	<form method="post"	action="#">

			<div class="group_1">
				<select name="serviceProvider" id="operator"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
					<option value="">--SELECT MERCHANT--</option>
				</select>
				<p></p>
			</div>
			<div class="group_1">
				<input type="text" name="orderID" required="required">
				<span class="highlight"></span>
								<span class="bar"></span> <label>Order ID</label>
				<p class="error"></p>
			</div>

							<div class="group_1">
								<input type="number" name="amount" min="10"
									required /> <span class="highlight"></span>
								<span class="bar"></span> <label>Amount</label>
				<p></p>
			</div>
			<div class="group_1">
				<input type="text"  name="message" required />
						<span class="highlight"></span>
						<span class="bar"></span> <label>Message</label>
				<p class="error"></p>
			</div>
				<button type="button" class="btn" disabled="disabled"
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
									>
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
	
	<!-- Latest compiled and minified JavaScript -->
</body>
</html>





