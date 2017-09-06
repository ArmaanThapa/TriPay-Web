<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
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
<title>Bill Payment</title>
<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">

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
<script type="text/javascript"
	src="<c:url value="/resources/js/userdetails.js"/>"></script>
<script
	src="<c:url value='/resources/js/bootstrap.js' />"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/header.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/billpay.js"/>"></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/datepicker.css"/>">
	<script src="<c:url value="/resources/js/datepicker.js"/>"></script>
	<script>
		$(function() {
			$( "#ins_policy_date" ).datepicker({
				format:"yyyy-mm-dd"
			});
		});
	</script>

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
	<div class="background"></div>
	<!---blue box---->

	<div class="container" id="bill_box">
		<div class="row">

			<div class="col-md-6 billprepaid" id="Prepaid">
				<!---form---->
				<div class="box hidden-xs"></div>
				<ul class="nav nav-pills">
					<li id="DTHSubMenu" class="active"><a data-toggle="pill" href="#DTHFadeIn"><i class="fa fa-television" aria-hidden="true"></i> <span>DTH</span></a></li>
					<li id="LandlineSubMenu"><a data-toggle="pill"
						href="#LandlineFadeIn"> <i class="fa fa-phone" aria-hidden="true"></i><span>Landline</span></a></li>
					<li id="ElectricitySubMenu"><a data-toggle="pill"
						href="#ElectricityFadeIn"> <i class="fa fa-lightbulb-o" aria-hidden="true"></i><span>Electricity</span></a></li>
					<li id="GasSubMenu"><a data-toggle="pill" href="#GasFadeIn"><i class="fa fa-fire" aria-hidden="true"></i><span>Gas</span></a></li>
					<li id="InsuranceSubMenu"><a data-toggle="pill"
						href="#InsuranceFadeIn"><i class="fa fa-medkit" aria-hidden="true"></i><span>Insurance</span></a></li>
				</ul>

				<div class="tab-content" id="bill_payment">
					<div id="DTHFadeIn" class="tab-pane fade in active">
						<p  id="error_dth_bill" class="title_error"></p>
						<p  id="sucess_dth_bill" class="title_error"></p><br><br>
						<form method="post"
							action="#">
							<div class="group_1">
							<select name="serviceProvider" id="dth_provider"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
									<option value="#">Select DTH Provider</option>
									<option value="VATV">Airtel Digital TV</option>
									<option value="VDTV">Dish TV</option>
									<option value="VRTV">Reliance Digital TV</option>
									<option value="VSTV">Sun Direct</option>
									<option value="VTTV">Tata Sky</option>
									<option value="VVTV">Videocon d2h</option>
								</select>
								<p class="error" id="error_dth_provider"></p>
							</div>

							<div class="group_1">
								<input type="text" name="dthNo" id="dth_number" required> <span class="highlight"></span><span class="bar"></span>
								<label id="dth_number_label"></label>
								
								<p class="error" id="error_dth_number"></p>
							</div>

							<div class="group_1">
								<input type="number" class="numeric" name="amount" min="10" id="dth_amount" required> <span class="highlight"></span> <span
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
									<option value="VATL">Airtel</option>
									<option value="VBGL">BSNL</option>
									<option value="VMDL">MTNL - Delhi</option>
									<option value="VRGL">Reliance</option>
									<option value="VTCL">Tata Docomo</option>
						        </select>
								<p class="error" id="error_landline_provider"></p>
							</div>

							<div class="group_1">
								<input type="text" class="numeric" name="stdCode" id="landline_std"
									maxlength="6" required> <span class="highlight"></span>
								<span class="bar"></span>
								<p class="error" id="error_landline_std"></p>
								<label>STD Code</label>
							</div>
							<div class="group_1">
								<input type="text" name="caCode" id="landline_ca"
									   maxlength="4" required> <span class="highlight"></span>
								<span class="bar"></span>
								<p class="error" id="error_landline_ca"></p>
								<label>CA Number</label>

							</div>
							<div class="group_1">
								<input type="text" class="numeric" name="landlineNumber" id="landline_number" required> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_landline_number"></p>
								<label>Landline No</label>
							</div>
							<div class="group_1">
								<input type="text" name="accountNumber" class="numeric" id="landline_account_number" required> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_landline_account_number"></p>
								<label>Account No</label>
							</div>

							<div class="group_1">
								<input type="number" name="amount" min="10" class="numeric" id="landline_amount" required> <span
									class="highlight"></span> <span class="bar"></span> <label>Enter
									Exact Amount</label>
                                <a href="#" id="landline_get_amount" class="links">Get Due Amount</a>
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
									<option value="VTTE">TSECL - TRIPURA</option>
									<option value="VTPE">Torrent Power</option>
									<option value="VNDE">Tata Power - DELHI</option>
									<option value="VSTE">Southern Power - TELANGANA</option>
									<option value="VSAE">Southern Power - ANDHRA PRADESH </option>
									<option value="VREE">Reliance Energy - MUMBAI</option>
									<option value="VMPE">Paschim Kshetra Vitaran - MADHYA PRADESH</option>
									<option value="VDOE">Odisha Discoms - ODISHA</option>
									<option value="VNUE">Noida Power - NOIDA</option>
									<option value="VMDE">MSEDC - MAHARASHTRA</option>
									<option value="VMME">Madhya Kshetra Vitaran - MADHYA PRADESH</option>
									<option value="VDRE">Jodhpur Vidyut Vitran Nigam - RAJASTHAN</option>
									<option value="VJUE">Jamshedpur Utilities & Services (JUSCO)</option>
									<option value="VJRE">Jaipur Vidyut Vitran Nigam - RAJASTHAN</option>
									<option value="VIPE">India Power</option>
									<option value="VDNE">DNHPDCL - DADRA & NAGAR HAVELI</option>
									<option value="VDHE">DHBVN - HARYANA</option>
									<option value="VCCE">CSEB - CHHATTISGARH</option>
									<option value="VCWE">CESC - WEST BENGAL</option>
									<option value="VBYE">BSES Yamuna - DELHI</option>
									<option value="VBRE">BSES Rajdhani - DELHI</option>
									<option value="VBME">BEST Undertaking - MUMBAI</option>
									<option value="VBBE">BESCOM - BENGALURU</option>
									<option value="VAAE">APDCL - ASSAM</option>
									<option value="VARE">Ajmer Vidyut Vitran Nigam - RAJASTHAN</option>
								</select>
								<p class="error" id="error_ecity_provider"></p>
							</div>


							<div class="group_1">
								<input type="text" name="accountNumber" class="numeric" id="ecity_account_number" required> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_ecity_account_number"></p>
								<label id="ecity_account_label"></label>
							</div>

							<div class="group_1" id="cycle_number">
								<input type="text" name="cycleNumber" class="numeric" id="ecity_cycle_number" required> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_ecity_cycle_number"></p>
								<label>Cycle No</label>
							</div>
							<div class="group_1" id="billing_unit">
								<input type="text" name="cycleNumber" class="numeric" id="ecity_billing_unit" required> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_ecity_billing_unit"></p>
								<label>Billing Unit</label>
							</div>
							<div class="group_1" id="processing_cycle">
								<input type="text" name="cycleNumber" id="ecity_processing_cycle" required> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_ecity_processing_cycle"></p>
								<label>Processing Cycle</label>
							</div>
							<div class="group_1" id="city_name">
								<input type="text" name="cycleNumber" id="ecity_city_name" required> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_ecity_city_name"></p>
								<label>City Name</label>
							</div>

							<div class="group_1">
								<input type="number" name="amount" min="10" class="numeric" id="ecity_amount" required> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_ecity_amount"></p>
                                <a href="#" id="ecity_get_amount" class="links">Get Due Amount</a>
								<label>Enter Exact Amount</label>
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
									<option value="VIPG">Indraprastha Gas</option>
									<option value="VMMG">Mahanagar Gas</option>
									<option value="VGJG">Gujarat Gas </option>
								 	<option value="VADG">Adani Gas</option>
								</select>
								<p class="error" id= "error_gas_provider"></p>
							</div>

							<div class="group_1">
								<input type="text" name="accountNumber" class="numeric" id="gas_account_number" required> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_gas_account_number"></p>
								<label id="gas_account_label">Account No</label>
							</div>

							<div class="group_1" id="bill_group">
								<input type="text" name="billGroupNumber" id="gas_bill_group" required> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_gas_bill_group"></p>
								<label>Bill Group No</label>
							</div>

							<div class="group_1">
								<input type="number" name="amount" min="10" class="numeric" id="gas_amount" required> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_gas_amount"></p>
                                <a href="#" id="gas_get_amount" class="links">Get Due Amount</a>
								<label>Enter Exact Amount</label>
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
									<option value="VIPI">ICICI Prudential Life Insurance</option>
									<option value="VTAI">Tata AIA Life Insurance</option>
									<option value=“VILI”>IndiaFirst Life Insurance</option>
  									<option value=“VBAI”>Bharti AXA Life Insurance</option>
								</select>
								<p class="error" id="error_ins_provider"></p>
							</div>

							<div class="group_1">
								<input type="text" name="policyNumber" class="numeric" id="ins_policy_number" required> <span
									class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_ins_policy_number"></p>
								<label id="i_account_name">Policy No</label>
							</div>

							<div class="group_1">
								<input type="text" name="policyDate" id="ins_policy_date" readonly="readonly" required>
								<p class="error" id="error_ins_policy_date" ></p>
								<label>Date Of Birth</label>
							</div>

							<div class="group_1">
								<input type="number" name="amount" min="10" id="ins_amount" class="numeric" required> <span
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

								<img src='<c:url value="/resources/images/slider_8.jpg"/>'>
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
	<div id="order_confirmation" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h5 class="modal-title">Order Confirmation</h5>
				</div>
				<div class="modal-body">
					<table class="table table-condensed">
						<tr>
							<td><b>Service </b> </td> <td id="o_service"> </td>
						</tr>
						<tr>
							<td><b id="o_account_name">Account Number </b> </td> <td id="o_account_number"> </td>
						</tr>
						<tr>
							<td><b>Bill Amount</b> </td><td id="o_bill_amount"></td>
						</tr>
						<tr>
							<td><b>Service Charge</b> </td><td id="o_service_amount"></td>
						</tr>
						<tr>
							<td><b>Net Amount</b></td> <td id="o_net_amount"></td>
						</tr>
					</table>
					<button type="button" class="btn btn-block btn-info" id="confirm_order">Confirm</button>
				</div>
			</div>
		</div>
	</div>

	<%--<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>--%>
	<script type="text/javascript"
		src='<c:url value="/resources/js/wow.js"/>' ></script>
	<script>
		new WOW().init();
	</script>
<script
	src="<c:url value='/resources/js/bootstrap.js'/>"></script>

</body>
</html>