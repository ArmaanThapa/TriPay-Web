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
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">

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
	src="<c:url value="/resources/js/userdetails.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/header.js"/>"></script>
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>' type="image/png" />


<!-- Latest compiled and minified CSS -->

<link href='<c:url value="/resources/css/css_style.css"/>'
	rel='stylesheet' type='text/css'>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/sendmoney.js"></script>

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
					<li class="active"><a data-toggle="pill" href="#home"><i class="fa fa-mobile"></i><span>Mobile</span></a></li>
					<li><a data-toggle="pill" href="#menu1"> <i class="fa fa-credit-card"></i><span>Bank Account</span></a></li>
				</ul>
				
				<div class="tab-content" id="sendmoney">
				<span id="error_mobile_sm" class="label label-danger"></span>
				<span id="success_mobile_sm" class="label label-success"></span>
					<div id="home" class="tab-pane fade in active" style="margin-top: 60px;">

						<form method="post" action="#">
							
							<div class="group_1">
								<input type="text" name="mobileNumber" maxlength="10" id="smm_mobile" required>
								<span class="highlight"></span> <span class="bar"></span>
								<p class="error" id="error_smm_mobile"></p>
								<label>Mobile No</label>
							</div>

							<div class="group_1">
								<input type="number" name="amount" min="10" id="smm_amount" required> <span
									class="highlight"></span> <span class="bar"></span>
								<p id="error_smm_amount" class="error"></p>
								<label>Amount</label>
							</div>

							<div class="group_1">
								<input type="text" name="amount" min="10" id="smm_message" required> <span
									class="highlight"></span> <span class="bar"></span>
								<p id="error_smm_message" class="error"></p>
								<label>Message</label>
							</div>

							<button type="button" class="btn" id="smm_submit"
								style="width: 80%; background: #ff0000; margin-top: 10px; color: #FFFFFF;">Send</button>
						</form>
					</div>

					<div id="menu1" class="tab-pane fade" style="margin-top: 20px;">
						<p id="bt_error"></p>
                        <p id="bt_success"></p>
                        <form method="post"
							action="<c:url value="User/SendMoney/Bank"/>">

							<div class="group_1">
								<input type="text" id="bank_account_no" required> <span class="highlight" required></span>
								<span class="bar"></span> <label>Account No</label>
                                <p class="error" id="error_bank_account_no"></p>
							</div>

							<div class="group_1">
								<input type="text" id="bank_account_holder" required> <span class="highlight" required></span>
								<span class="bar"></span> <label>Account Holder Name</label>
                                <p class="error" id="error_bank_account_holder"></p>
							</div>

							<div class="group_1">
								<input type="text" id="bank_ifsc_code" required> <span class="highlight" required></span>
								<span class="bar"></span> <label>IFSC Code</label>
                                <p class="error" id="error_bank_ifsc_code"></p>
							</div>

							<div class="group_1">
								<input type="text" id="bank_amount" required> <span class="highlight" required></span>
								<span class="bar"></span> <label>Amount</label>
                                <p class="error" id="error_bank_amount"></p>
							</div>
							<input type="hidden" id="bank_address" name="address" value=""/>
							<input type="hidden" id="bt_account_number"/>
                            <input type="hidden" id="bt_mobile_number"/>
                            <input type="hidden" id="bt_email"/>
                            <button type="button" id="bt_submit" class="btn" style="width: 80%; background: #ff0000; color: #FFFFFF;">Coming Soon</button>
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
						<td><b>Mobile No. </b></td> <td id="o_mobile_number"> </td>
					</tr>
					<tr>
						<td><b>Amount</b> </td><td id="o_amount"></td>
					</tr>
					<tr>
						<td><b>Commission</b> </td><td id="o_commission"></td>
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

		<script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>
		<script type="text/javascript"
			src='<c:url value="/resources/js/wow.js"/>' />
		<script>
			new WOW().init();
		</script>
		<!-- Latest compiled and minified JavaScript -->
	<script
	src="<c:url value='/resources/js/bootstrap.js'/>"></script>
</body>
</html>
