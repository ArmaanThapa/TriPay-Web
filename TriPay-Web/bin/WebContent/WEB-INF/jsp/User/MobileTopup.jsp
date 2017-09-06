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
<title>VPayQwik | Mobile Topup</title>
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
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>'
	type="image/png" />
<link href='<c:url value="/resources/css/css_style.css"/>'
	rel='stylesheet' type='text/css'>
	<script type="text/javascript" src="<c:url value="/resources/js/topup.js"/>" ></script>

</head>

<body onLoad="ActiveMenu('MobileTopup');ActiveSubmenu('${menu}');ActiveFadeIn('${menu}');"> 
 
	<jsp:include page="/WEB-INF/jsp/User/Header.jsp" />
	<jsp:include page="/WEB-INF/jsp/User/Menu.jsp" />

	<!-----------------end navbar---------------------->

	<!------------- end main-------------------->


	<div class="background"></div>
	<!---blue box---->

	<div class="container" id="box">
		<div class="row">

			<div class="col-md-4" id="Prepaid">
				<!---form---->
				<div class="box hidden-xs"></div>

				<ul class="nav nav-pills">
					<li id="PrepaidSubMenu"><a data-toggle="pill"
						href="#PrepaidFadeIn">Prepaid</a></li>
					<li id="PostpaidSubMenu"><a data-toggle="pill"
						href="#PostpaidFadeIn">Postpaid</a></li>
					<li id="DataCardSubMenu"><a data-toggle="pill"
						href="#DataCardFadeIn">DataCard</a></li>
				</ul>

				<div class="tab-content">
				
						<p id="error_pre_topup" class="title_error"></p>
						<p id="success_pre_topup" class="title_error"></p>
					<br><br><div id="PrepaidFadeIn" class="tab-pane fade">
						
						<form action="#" method="post">

							<div class="group_1">
								<input type="text" name="mobileNo" id="pre_mobile"
									 required> <span
									class="highlight"></span><span class="bar"></span>
								<p id="error_pre_mobile" class="error"></p>
								<label>Prepaid Mobile No</label>
							</div>

							<div class="group_1">
							<select name="serviceProvider" id="pre_operator"
									class="form-control" style="width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none; border-radius: 0;">
									<option value="#">Select Operator</option>
									<option value="ACP">Aircel</option>
									<option value="ATP">Airtel</option>
									<option value="BVP">BSNL - Special Tariff</option>
									<option value="BGP">BSNL</option>
									<option value="IDP">Idea</option>
									<option value="MSP">MTNL - Special Tariff</option>
									<option value="MMP">MTNL</option>
									<option value="MTP">MTS</option>
									<option value="RGP">Reliance</option>
									<option value="TVP">T24 Mobile - Special Tariff</option>
									<option value="TMP">T24 Mobile</option>
									<option value="TCP">Tata Docomo CDMA</option>
									<option value="TSP">Tata Docomo GSM - Special Tariff</option>
									<option value="TGP">Tata Docomo GSM</option>
									<option value="USP">Telenor - Special Tariff</option>
									<option value="UGP">Telenor</option>
									<option value="VSP">Videocon - Special Tariff</option>
									<option value="VGP">Videocon</option>
									<option value="VFP">Vodafone</option>
								</select>
								<p id="error_pre_operator" class="error"></p>
							</div>

							<div class="group_1">
								<select name="serviceProvider" id="pre_circle"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
									<option value="#">Select Area</option>
									<option value="AP">Andhra Pradesh</option>
									<option value="AS">Assam</option>
									<option value="BR">Bihar and Jharkhand</option>
									<option value="CH">Chennai</option>
									<option value="DL">Delhi</option>
									<option value="GJ">Gujarat</option>
									<option value="HR">Haryana</option>
									<option value="HP">Himachal Pradesh</option>
									<option value="JK">Jammu and Kashmir</option>
									<option value="KN">Karnataka</option>
									<option value="KL">Kerala</option>
									<option value="KO">Kolkata</option>
									<option value="MP">Madhya Pradesh/Chattisgarh</option>
									<option value="MH">Maharashtra</option>
									<option value="MU">Mumbai</option>
									<option value="NE">North East</option>
									<option value="OR">Orissa</option>
									<option value="PB">Punjab</option>
									<option value="RJ">Rajasthan</option>
									<option value="TN">Tamil Nadu</option>
									<option value="UW">Uttar Pradesh(W)/Uttranchal</option>
									<option value="UE">Uttar Pradesh(E)</option>
									<option value="WB">West Bengal</option>
								</select>
								<div id="plan_link"></div>
								<p id="error_pre_circle" class="error"></p>
							</div>
							
							<div class="group_1">
								<input type="number" id="pre_amount" name="amount" min="10"
									 required> <span
									class="highlight"></span> <span class="bar"></span> <label>Enter
									Amount</label>
									<p id="error_pre_amount" class="error"></p>
							</div>

							<button type="button" class="btn" id="pre_submit" 
								style="width: 80%; background: #ff0000; margin-top: 10px; color: #FFFFFF;">Pay</button>
						</form>
					</div><!-- end prepaid -->
					
					<p id="error_post_topup"  class="title_error"></p>
						<p id="success_post_topup"  class="title_error"></p>
					<div id="PostpaidFadeIn" class="tab-pane fade">
					
						<form method="post"
							action="#">

							<div class="group_1">
								<input type="text" name="mobileNo" 	id="post_mobile" required> <span class="highlight"></span><span class="bar"></span>
								<p id="error_post_mobile" class="error"></p>
								<label>Postpaid Mobile No</label>
							</div>

							<div class="group_1">
								<select name="serviceProvider" id="post_operator"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
									<option value="#">Select Operator</option>
									<option value="ACC">Aircel</option>
									<option value="ATC">Airtel</option>
									<option value="BGC">BSNL</option>
									<option value="IDC">Idea</option>
									<option value="MTC">MTS</option>
									<option value="RGC">Reliance</option>
									<option value="TDC">Tata Docomo</option>
									<option value="VFC">Vodafone</option>
								</select>
								<p id="error_post_operator" class="error"></p>
							</div>
							<div class="group_1">
								<select name="serviceProvider" id="post_circle"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
									<option value="">Select Area</option>
									<option value="AP">Andhra Pradesh</option>
									<option value="AS">Assam</option>
									<option value="BR">Bihar and Jharkhand</option>
									<option value="CH">Chennai</option>
									<option value="DL">Delhi</option>
									<option value="GJ">Gujarat</option>
									<option value="HR">Haryana</option>
									<option value="HP">Himachal Pradesh</option>
									<option value="JK">Jammu and Kashmir</option>
									<option value="KN">Karnataka</option>
									<option value="KL">Kerala</option>
									<option value="KO">Kolkata</option>
									<option value="MP">Madhya Pradesh/Chattisgarh</option>
									<option value="MH">Maharashtra</option>
									<option value="MU">Mumbai</option>
									<option value="NE">North East</option>
									<option value="OR">Orissa</option>
									<option value="PB">Punjab</option>
									<option value="RJ">Rajasthan</option>
									<option value="TN">Tamil Nadu</option>
									<option value="UW">Uttar Pradesh(W)/Uttranchal</option>
									<option value="UE">Uttar Pradesh(E)</option>
									<option value="WB">West Bengal</option>
								</select>
								<p id="error_post_circle" class="error"></p>
							</div>

							<div class="group_1">
								<input type="number" id="post_amount" name="amount" min="10"
									 required> <span
									class="highlight"></span> <label>Enter Amount</label>
								<p id="error_post_amount" class="error"></p>
							</div>
							<button type="button" id="post_submit" class="btn"
								style="width: 80%; background: #ff0000; margin-top: 10px; color: #FFFFFF;">Pay</button>
						</form>
					</div>
					
					
					
					<p id="error_dc_topup" class="title_error"></p>
							<p class="success_dc_topup" class="title_error"></p>
							
					<div id="DataCardFadeIn" class="tab-pane fade">

						<form action="#" method="post">
							
							<div class="group_1">
								<input type="text" name="mobileNo" id="dc_mobile">
								 <span class="highlight"></span> <span class="bar"></span> 
									<p id="error_dc_mobile" class="error"></p>
									<label>Mobile No</label>
							</div>
							
							<div class="group_1">
								<select name="serviceProvider" id="dc_operator"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
									<option value="#">Select Operator</option>
									<option value="ACP">Aircel</option>
									<option value="ATP">Airtel</option>
									<option value="BVP">BSNL - Special Tariff</option>
									<option value="BGP">BSNL</option>
									<option value="IDP">Idea</option>
									<option value="MSP">MTNL - Special Tariff</option>
									<option value="MMP">MTNL</option>
									<option value="MTP">MTS</option>
									<option value="RGP">Reliance</option>
									<option value="TVP">T24 Mobile - Special Tariff</option>
									<option value="TMP">T24 Mobile</option>
									<option value="TCP">Tata Docomo CDMA</option>
									<option value="TSP">Tata Docomo GSM - Special Tariff</option>
									<option value="TGP">Tata Docomo GSM</option>
									<option value="USP">Telenor - Special Tariff</option>
									<option value="UGP">Telenor</option>
									<option value="VSP">Videocon - Special Tariff</option>
									<option value="VGP">Videocon</option>
									<option value="VFP">Vodafone</option>
								</select>
								<p id="error_dc_operator" class="error"></p>
							</div>

							<div class="group_1">
								<select name="serviceProvider" id="dc_circle"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
									<option value="#">Select Area</option>
									<option value="AP">Andhra Pradesh</option>
									<option value="AS">Assam</option>
									<option value="BR">Bihar and Jharkhand</option>
									<option value="CH">Chennai</option>
									<option value="DL">Delhi</option>
									<option value="GJ">Gujarat</option>
									<option value="HR">Haryana</option>
									<option value="HP">Himachal Pradesh</option>
									<option value="JK">Jammu and Kashmir</option>
									<option value="KN">Karnataka</option>
									<option value="KL">Kerala</option>
									<option value="KO">Kolkata</option>
									<option value="MP">Madhya Pradesh/Chattisgarh</option>
									<option value="MH">Maharashtra</option>
									<option value="MU">Mumbai</option>
									<option value="NE">North East</option>
									<option value="OR">Orissa</option>
									<option value="PB">Punjab</option>
									<option value="RJ">Rajasthan</option>
									<option value="TN">Tamil Nadu</option>
									<option value="UW">Uttar Pradesh(W)/Uttranchal</option>
									<option value="UE">Uttar Pradesh(E)</option>
									<option value="WB">West Bengal</option>
				
								</select>
								<div id="plan_link_dc"></div>
								<p id="error_dc_circle" class="error"></p>
							</div>

							<div class="group_1">
								<input type="number" name="amount"  id="dc_amount" min="10" > <span
									class="highlight"></span> <span class="bar"></span> <label>Enter
									Amount</label>
								<p id="error_dc_amount" class="error"></p>
							</div>

							<button type="button" class="btn" id="dc_submit"
								style="width: 80%; background: #ff0000; margin-top: 10px; color: #FFFFFF;">Pay</button>
						</form>
					</div>
				</div>

			</div>
			<!----end col-md-4-->


			<div class="col-md-8 hidden-xs">
				<div class="slider" id="slider"
					style="margin-right: -15px; margin-left: -15px;">
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
	<jsp:include page="/WEB-INF/jsp/User/Footer.jsp" />
	<div id="browse_plans_modal" role="dialog" class="modal fade">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h2><b>Choose Plan</b></h2>
				</div>
				<div class="modal-body" id="browse_plans_body">
 			<div class="row">
 			<div class="col-md-12">
 			<ul class="nav nav-tabs">
    			<li class="active"><a data-toggle="tab" href="#3g" id="size">3G</a></li>
    			<li><a data-toggle="tab" href="#2g" id="size">2G</a></li>
 	  			<li><a data-toggle="tab" href="#special" id="size">Special</a></li>
 	  			<li><a data-toggle="tab" href="#regular" id="size">Regular</a></li>
  			</ul>
  			</div>
			</div>
  <div class="tab-content">
    			<div id="3g" class="tab-pane fade in active">
    				<table id="3g_plans" class="table table-hover">
    				</table>
    			</div>
    			<div id="2g" class="tab-pane fade">
    				<table id="2g_plans" class="table table-hover">
    				</table>
    			</div>
    			<div id="special" class="tab-pane fade">
    				<table id="special_plans" class="table table-hover">
    				</table>
    			</div>
    			<div id="regular" class="tab-pane fade">
    			    <table id="regular_plans" class="table table-hover">
    				</table>
    			</div>
    			

  </div>							
							
											
				</div>
			</div>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>
	<script type="text/javascript"
		src='<c:url value="/resources/js/wow.js"/>' /></script>
	<script>
		new WOW().init();
	</script>
	<script
	src="<c:url value='/resources/js/bootstrap.js'/>"></script>
	
	<!-- Latest compiled and minified JavaScript -->
</body>
</html>



