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
<title>Bill Payment</title>
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>' type="image/png" />
<link href='<c:url value='/resources/css/font-family.css'/>'
	rel='stylesheet' type='text/css'>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/billpay.js"></script>
		
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

</head>
<body
	onLoad="ActiveSubmenu('${menu}');ActiveFadeIn('${menu}');">
	<jsp:include page="/WEB-INF/jsp/User/Header.jsp" />
	<jsp:include page="/WEB-INF/jsp/User/Menu.jsp" />
	<div class="background"></div>
	<!---blue box---->

	<div class="container" id="bill_box">
		<div class="row">

			<div class="col-md-6" id="Prepaid">
				<!---form---->
				<div class="box hidden-xs"></div>
				<ul class="nav nav-pills">
					<li id="DTHSubMenu"><a data-toggle="pill" href="#DTHFadeIn">DTH</a></li>
					<li id="LandlineSubMenu"><a data-toggle="pill"
						href="#LandlineFadeIn">Landline</a></li>
					<li id="ElectricitySubMenu"><a data-toggle="pill"
						href="#ElectricityFadeIn">Electricity</a></li>
					<li id="GasSubMenu"><a data-toggle="pill" href="#GasFadeIn">Gas</a></li>
					<li id="InsuranceSubMenu"><a data-toggle="pill"
						href="#InsuranceFadeIn">Insurance</a></li>
				</ul>

				<div class="tab-content" id="bill_payment">
					<div id="DTHFadeIn" class="tab-pane fade">
						<p  id="error_dth_bill" class="title_error"></p>
						<p  id="sucess_dth_bill" class="title_error"></p><br><br>
						<form method="post"
							action="#">
							<div class="group_1">
							<select name="serviceProvider" id="dth_provider"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
									<option value="#">Select DTH Provider</option>
									<option value="ATV">Airtel Digital TV</option>
									<option value="DTV">Dish TV</option>
									<option value="RTV">Reliance Digital TV</option>
									<option value="STV">Sun Direct</option>
									<option value="TTV">Tata Sky</option>
									<option value="VTV">Videocon d2h</option>
								</select>
								<p class="error" id="error_dth_provider"></p>
							</div>

							<div class="group_1">
								<input type="text" name="dthNo" id="dth_number" > <span class="highlight"></span><span class="bar"></span>
								<label>DTH No</label>
								
								<p class="error" id="error_dth_number"></p>
							</div>

							<div class="group_1">
								<input type="number" name="amount" min="10" id="dth_amount"> <span class="highlight"></span> <span
									class="bar"></span>
									<p id="error_dth_amount" class="error"></p>
								<label>Amount</label>
							</div>
							<button type="button" class="btn" id="dth_submit"
								style="width: 80%; background: #ff0000; margin-top: 10px; color: #FFFFFF;">Pay</button>
						</form>
					</div>

					<div id="LandlineFadeIn" class="tab-pane fade">
						<p class="title_error" id="error_landline_bill"></p>
						<p class="title_error" id="success_landline_bill"></p><br>
						<form method="post"
							action="#">
							<div class="group_1">
								<select name="serviceProvider" id="landline_provider"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
									<option value="#">Select Landline Provider</option>
									<option value="ATL">Airtel</option>
									<option value="BGL">BSNL</option>
									<option value="MDL">MTNL - Delhi</option>
									<option value="RGL">Reliance</option>
									<option value="TCL">Tata Docomo</option>
						</select>
								<p class="error" id="error_landline_provider"></p>
							</div>

							<div class="group_1">
								<input type="text" name="stdCode" id="landline_std"
									maxlength="4" > <span class="highlight"></span>
								<span class="bar"></span>
								<p class="error" id="error_landline_std"></p>
								<label>STD Code</label>

							</div>
							<div class="group_1">
								<input type="text" name="landlineNumber" id="landline_number"> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_landline_number"></p>
								<label>Landline No</label>

							</div>
							<div class="group_1">
								<input type="text" name="accountNumber" id="landline_account_number"> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_landline_account_number"></p>
								<label>Account No</label>
							</div>

							<div class="group_1">
								<input type="number" name="amount" min="10" id="landline_amount"> <span
									class="highlight"></span> <span class="bar"></span> <label>Enter
									Amount</label>
									<p class="error" id="error_landline_amount"></p>
							</div>
							<button type="button" class="btn" id="landline_submit"
								style="width: 80%; background: #ff0000; margin-top: 0px; color: #FFFFFF;">Pay</button>
						</form>
					</div>
					
					<div id="ElectricityFadeIn" class="tab-pane fade">
						<p class="error" id="success_ecity_bill"></p>
						<p class="error" id="error_ecity_bill"></p><br><br>
						<form method="post" action="#">
							<div class="group_1">
								<select name="serviceProvider" id="ecity_provider"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
									<option value="#">Select Electricity Provider</option>
									<option value="TTE">TSECL - TRIPURA</option>
									<option value="TPE">Torrent Power</option>
									<option value="NDE">Tata Power - DELHI</option>
									<option value="STE">Southern Power - TELANGANA</option>
									<option value="SAE">Southern Power - ANDHRA PRADESH </option>
									<option value="REE">Reliance Energy - MUMBAI</option>
									<option value="MPE">Paschim Kshetra Vitaran - MADHYA PRADESH</option>
									<option value="DOE">Odisha Discoms - ODISHA</option>
									<option value="NUE">Noida Power - NOIDA</option>
									<option value="MDE">MSEDC - MAHARASHTRA</option>
									<option value="MME">Madhya Kshetra Vitaran - MADHYA PRADESH</option>
									<option value="DRE">Jodhpur Vidyut Vitran Nigam - RAJASTHAN</option>
									<option value="JUE">Jamshedpur Utilities & Services (JUSCO)</option>
									<option value="JRE">Jaipur Vidyut Vitran Nigam - RAJASTHAN</option>
									<option value="IPE">India Power</option>
									<option value="DNE">DNHPDCL - DADRA & NAGAR HAVELI</option>
									<option value="DHE">DHBVN - HARYANA</option>
									<option value="CCE">CSEB - CHHATTISGARH</option>
									<option value="CWE">CESC - WEST BENGAL</option>
									<option value="BYE">BSES Yamuna - DELHI</option>
									<option value="BRE">BSES Rajdhani - DELHI</option>
									<option value="BME">BEST Undertaking - MUMBAI</option>
									<option value="BBE">BESCOM - BENGALURU</option>
									<option value="AAE">APDCL - ASSAM</option>
									<option value="ARE">Ajmer Vidyut Vitran Nigam - RAJASTHAN</option>
								</select>
								<p class="error" id="error_ecity_provider"></p>
							</div>


							<div class="group_1">
								<input type="text" name="accountNumber" id="ecity_account_number"> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_ecity_account_number"></p>
								<label>Account No</label>
							</div>

							<div class="group_1" id="cycle_number">
								<input type="text" name="cycleNumber" id="ecity_cycle_number"> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_ecity_cycle_number"></p>
								<label>Cycle No</label>
							</div>

							<div class="group_1">
								<input type="number" name="amount" min="10" id="ecity_amount"> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_ecity_amount"></p>
								<label>Enter Amount</label>
							</div>
							<button type="button" class="btn" id="ecity_submit"
								style="width: 80%; background: #ff0000; margin-top: 10px; color: #FFFFFF;">Pay</button>
						</form>
					</div>

					<div id="GasFadeIn" class="tab-pane fade">
						<p class="title_error" id="success_gas_bill"></p>
						<p class="title_error" id="error_gas_bill"></p><br><br>
						<form method="post"
							action="#">
							<div class="group_1">
								<select name="serviceProvider" id="gas_provider"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
									<option value="#">Select Gas Provider</option>
									<option value="IPG">Indraprastha Gas</option>
									<option value="MMG">Mahanagar Gas</option>
									<option value=“GJG”>Gujarat Gas </option>
								  	<option value=“GSG”>GSPC Gas </option>
								 	<option value=“ADG”>Adani Gas </option>
								</select>
								<p class="error" id= "error_gas_provider"></p>
							</div>

							<div class="group_1">
								<input type="text" name="accountNumber" id="gas_account_number"> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_gas_account_number"></p>
								<label>Account No</label>
							</div>

							<div class="group_1">
								<input type="number" name="amount" min="10" id="gas_amount"> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_gas_amount"></p>
								<label>Enter Amount</label>
							</div>
							
							<button type="button" class="btn" id="gas_submit"
								style="width: 80%; background: #ff0000; margin-top: 10px; color: #FFFFFF;">Pay</button>
						</form>
					</div>

					<div id="InsuranceFadeIn" class="tab-pane fade">
						<p class="title_error" id="success_ins_bill"></p>
						<p class="title_error" id="error_ins_bill"></p><br><br>
						<form method="post"
							action="#">
							<div class="group_1">
								<select name="serviceProvider" id="ins_provider"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
									<option value="#">Select Insurance Provider</option>
									<option value="IPI">ICICI Prudential Life Insurance</option>
									<option value="TAI">Tata AIA Life Insurance</option>
									<option value=“ILI”>IndiaFirst Life Insurance</option>
  									<option value=“BAI”>Bharti AXA Life Insurance</option>
								</select>
								<p class="error" id="error_ins_provider"></p>
							</div>

							<div class="group_1">
								<input type="text" name="policyNumber" id="ins_policy_number"> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_ins_policy_number"></p>
								<label>Policy No</label>
							</div>

							<div class="group_1">
								<input type="date" name="policyDate" id="ins_policy_date">
								<p class="error" id="error_ins_policy_date"></p>
							</div>

							<div class="group_1">
								<input type="number" name="amount" min="10" id="ins_amount"> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_ins_amount"></p>
								<label>Enter Amount</label>
							</div>
							<button type="button" class="btn" id="ins_submit"
								style="width: 80%; background: #ff0000; margin-top: 10px; color: #FFFFFF;">Pay</button>
						</form>
					</div>
				</div>
			</div>
						<div class="col-md-6 hidden-xs">
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

								<img src='<c:url value="/resources/images/slider_7.jpg"/>' />
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
				<table class="table table-fixed" id="plan_table">

				</table>
			</div>
			<div class="item"></div>
			<!---end item---->
		</div>
		<!--end carousel inner------>
	</div>

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