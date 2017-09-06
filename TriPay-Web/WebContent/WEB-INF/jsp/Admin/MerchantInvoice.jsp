<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicontripays, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<sec:csrfMetaTags/>

<title>TriPay | Merchant Invoice</title>
<!-- Bootstrap core CSS -->
<link rel="icon" href='<c:url value="/resources/images/favicontripay.png"/>' type="image/png" />

<link
	href="${pageContext.request.contextPath}/resources/admin/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/admin/fonts/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/admin/css/animate.min.css"
	rel="stylesheet">

<!-- Custom styling plus plugins -->
<link
	href="${pageContext.request.contextPath}/resources/admin/css/custom.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/admin/css/icheck/flat/green.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resources/admin/js/jquery.min.js"></script>
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
<body class="nav-md">
	<div class="se-pre-con"></div>
	<div class="container body">
		<div class="main_container">

				<jsp:include page="/WEB-INF/jsp/Admin/LeftMenu.jsp"/>
				<jsp:include page="/WEB-INF/jsp/Admin/TopNavigation.jsp"/>

			<div class="right_col" role="main">

				<div class="">
					<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12">
							<div class="page-title">
								<div class="x_title">
									<h2>
										Merchant Invoice Design <small></small>
									</h2>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">

									<section class="content invoice">
										<!-- title row -->
										<div class="row">
											<div class="col-xs-12 invoice-header">
												<h1>
													<i class="fa fa-globe"></i> Invoice. <small
														class="pull-right">Date: <%
														Date date = new Date();
														out.print("<h6 align=\"center\">" + date.toString() + "</h6>");
													%></small>
												</h1>
											</div>
											<!-- /.col -->
										</div>
										<!-- info row -->
										<div class="row invoice-info">
											<div class="col-sm-4 invoice-col">
												From
												<address>
													<strong>TriPay SOLUTION</strong> <br>106, 4th Cross <br>Koramangala,
													Bengaluru <br>Phone: 080 2553 5857 <br>Email:
													care@TriPay.in
												</address>
											</div>
											<!-- /.col -->
											<div class="col-sm-4 invoice-col">
												To
												<address>
													<strong>Asiadedu</strong> <br>Noida Sector-5 <br>U.P
													<br>Phone: 1234567890 <br>Email: asiad@gmail.com
												</address>
											</div>
											<!-- /.col -->
											<div class="col-sm-4 invoice-col">
												<b>Invoice #007612</b> <br> <br> <b>Order ID:</b>
												4F3S8J <br> <b>Payment Due:</b> 2/22/2014 <br> <b>Account:</b>
												968-34567
											</div>
											<!-- /.col -->
										</div>
										<!-- /.row -->

										<!-- Table row -->
										<div class="row">
											<div class="col-xs-12 table">
												<table class="table table-striped">
													<thead>
														<tr>
															<th>Qty</th>
															<th>Product</th>
															<th>Serial #</th>
															<th style="width: 59%">Description</th>
															<th>Subtotal</th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td>1</td>
															<td>Call of Duty</td>
															<td>455-981-221</td>
															<td>El snort testosterone trophy driving gloves
																handsome gerry Richardson helvetica tousled street art
																master testosterone trophy driving gloves handsome gerry
																Richardson</td>
															<td>64.50 /- Rs.</td>
														</tr>
														<tr>
															<td>1</td>
															<td>Need for Speed IV</td>
															<td>247-925-726</td>
															<td>Wes Anderson umami biodiesel</td>
															<td>50.00 /- Rs.</td>
														</tr>
														<tr>
															<td>1</td>
															<td>Monsters DVD</td>
															<td>735-845-642</td>
															<td>Terry Richardson helvetica tousled street art
																master, El snort testosterone trophy driving gloves
																handsome letterpress erry Richardson helvetica tousled</td>
															<td>10.70 /- Rs.</td>
														</tr>
														<tr>
															<td>1</td>
															<td>Grown Ups Blue Ray</td>
															<td>422-568-642</td>
															<td>Tousled lomo letterpress erry Richardson
																helvetica tousled street art master helvetica tousled
																street art master, El snort testosterone</td>
															<td>25.99 /- Rs.</td>
														</tr>
													</tbody>
												</table>
											</div>
											<!-- /.col -->
										</div>
										<!-- /.row -->

										<div class="row">
											<!-- accepted payments column -->
											<div class="col-xs-6">
												<p class="lead">
													Payment Methods: <i>TriPay Payment Gateway</i>
												</p>
												<img
													src="${pageContext.request.contextPath}/resources/admin/images/logo_ios.png"
													alt="Visa">
												<p class="text-muted well well-sm no-shadow"
													style="margin-top: 10px;">TriPay is a Mobile wallet
													which allows you to make online payments to various
													merchants, transfer funds to various banks and many more.</p>
											</div>
											<!-- /.col -->
											<div class="col-xs-6">
												<p class="lead">Amount Due 2/22/2014</p>
												<div class="table-responsive">
													<table class="table">
														<tbody>
															<tr>
																<th style="width: 50%">Subtotal:</th>
																<td>250.30 /- Rs.</td>
															</tr>
															<tr>
																<th>Tax (9.3%)</th>
																<td>10.34 /- Rs.</td>
															</tr>
															<tr>
																<th>Shipping:</th>
																<td>5.80 /- Rs.</td>
															</tr>
															<tr>
																<th>Total:</th>
																<td>265.24 /- Rs.</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
											<!-- /.col -->
										</div>
										
										<!-- /.row -->

										<!-- this row will not appear when printing -->
										<div class="row no-print">
											<div class="col-xs-12">
												<button class="btn btn-default" onclick="window.print();">
													<i class="fa fa-print"></i> Print
												</button>
												<!-- <button class="btn btn-success pull-right">
													<i class="fa fa-credit-card"></i> Submit Payment
												</button>
												<button class="btn btn-primary pull-right"
													style="margin-right: 5px;">
													<i class="fa fa-download"></i> Generate PDF
												</button> -->
											</div>
										</div>
									</section>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- footer content -->
				<jsp:include page="/WEB-INF/jsp/Admin/Footer.jsp"/>
				<!-- /footer content -->

			</div>
		</div>
	</div>

	<!-- footer content -->
	<footer>
		<div class="copyright-info"></div>
		<div class="clearfix"></div>
	</footer>
	<!-- /footer content -->
	<!-- /page content -->

	<div id="custom_notifications" class="custom-notifications dsp_none">
		<ul class="list-unstyled notifications clearfix"
			data-tabbed_notifications="notif-group">
		</ul>
		<div class="clearfix"></div>
		<div id="notif-group" class="tabbed_notifications"></div>
	</div>

	<script
		src="${pageContext.request.contextPath}/resources/admin/js/bootstrap.min.js"></script>

	<!-- bootstrap progress js -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/progressbar/bootstrap-progressbar.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/nicescroll/jquery.nicescroll.min.js"></script>
	<!-- icheck -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/icheck/icheck.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/admin/js/custom.js"></script>
	<!-- form wizard -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/wizard/jquery.smartWizard.js"></script>
	<!-- pace -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/pace/pace.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			// Smart Wizard
			$('#wizard').smartWizard();

			function onFinishCallback() {
				$('#wizard').smartWizard('showMessage', 'Finish Clicked');
				//alert('Finish Clicked');
			}
		});

		$(document).ready(function() {
			// Smart Wizard
			$('#wizard_verticle').smartWizard({
				transitionEffect : 'slide'
			});

		});
	</script>
</body>
</html>
