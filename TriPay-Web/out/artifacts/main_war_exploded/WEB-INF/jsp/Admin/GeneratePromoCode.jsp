<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
 <%@ taglib prefix="taq" uri="http://www.springframework.org/tags"%> 
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>


<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<sec:csrfMetaTags />
<title>VPayQwik | Generate Promo Code</title>

<!-- Bootstrap core CSS -->
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>'
	type="image/png" />
<link
	href="${pageContext.request.contextPath}/resources/admin/css/bootstrap.min.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath}/resources/admin/fonts/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/admin/css/animate.min.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath}/resources/admin/css/custom.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/admin/css/icheck/flat/green.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath}/resources/admin/js/datatables/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/admin/js/datatables/buttons.bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/admin/js/datatables/fixedHeader.bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/admin/js/datatables/responsive.bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/admin/js/datatables/scroller.bootstrap.min.css"
	rel="stylesheet" type="text/css" />

<script
	src="${pageContext.request.contextPath}/resources/admin/js/jquery.min.js"></script>

<link
	href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/datepicker.css"/>">
	<script src="<c:url value="/resources/js/datepicker.js"/>"></script>
	<script>
		$(function() {
			$( "#startDate" ).datepicker({
				format:"yyyy-mm-dd"
			});
			$( "#endDate" ).datepicker({
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
<body class="nav-md">
<div class="se-pre-con"></div>
	<div class="container body">
		<div class="main_container">


			<jsp:include page="/WEB-INF/jsp/Admin/LeftMenu.jsp" />
			<jsp:include page="/WEB-INF/jsp/Admin/TopNavigation.jsp" />

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="col-md-6 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>Add Promo Code</h2>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<br />
							${resp.message}
							<spring:form method="post" action="/Admin/GeneratePromoCode"
								modelAttribute="promoCodeRequest"
								class="form-horizontal form-label-left">
								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12">
										Promo Code </label>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<spring:input path="promoCode" class="form-control form-ds1" name="number" id="number"
										placeholder="Promo Code"  />
										<p class="error">${error.promoCode}</p>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12">Terms
										& Conditions <span class="required">*</span>
									</label>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<spring:input path="terms" class="form-control form-ds1" placeholder='Min Amount for Transaction'/>
										<p class="error">${error.terms}</p>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12">Start
										Date <span class="required">*</span>
									</label>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<spring:input path="startDate" readonly="true" class="form-control form-ds1"/>
									<p class="error">${error.startDate}</p>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12">End
										Date <span class="required">*</span>
									</label>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<spring:input path="endDate" readonly="true" class="form-control form-ds1"/>
										<p class="error">${error.endDate}</p>
									</div>
									<sec:csrfInput/>
								</div>


								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12">Description <span class="required">*</span>
									</label>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<spring:input path="description" class="form-control form-ds1"/>
										<p class="error">${error.description}</p>
									</div>
									<sec:csrfInput/>
								</div>


								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12">Fixed
										<span class="required">*</span>
									</label>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<spring:checkbox path="fixed" class="form-control form-ds1"
													  />
										<p class="error">${error.value}</p>
									</div>
								</div>


								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12">Amount
										 <span class="required">*</span>
									</label>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<spring:input path="value" class="form-control form-ds1" rows="3"
											 placeholder='Amount'/>
										<p class="error">${error.value}</p>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12">
										Services <span class="required">*</span>
									</label>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<table class="table">
									<c:forEach items="${services}" var="service">
										<tr><td style="width: 35px;"><spring:checkbox path="services" value="${service.code}" class="form-control form-ds1"/></td><td style="padding-top: 18px;">${service.description}</td></tr>
									</c:forEach>
										</table>
									</div>
								</div>


								<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
									<button type="submit" class="btn btn-success btn-ds1">Submit</button>
								</div>
							</spring:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer content -->
	<jsp:include page="/WEB-INF/jsp/Admin/Footer.jsp" />
	<!-- /footer content -->

	</div>
	<!-- /page content -->
	</div>

	</div>

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


	<!-- Datatables -->
	<!-- <script src="js/datatables/js/jquery.dataTables.js"></script>
  <script src="js/datatables/tools/js/dataTables.tableTools.js"></script> -->

	<!-- Datatables-->
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/datatables/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.bootstrap.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.buttons.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/datatables/buttons.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/datatables/jszip.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/datatables/pdfmake.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/datatables/vfs_fonts.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/datatables/buttons.html5.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/datatables/buttons.print.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.fixedHeader.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.keyTable.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.responsive.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/datatables/responsive.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.scroller.min.js"></script>


	<!-- pace -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/pace/pace.min.js"></script>
	<script>
		var handleDataTableButtons = function() {
			"use strict";
			0 !== $("#datatable-buttons").length
					&& $("#datatable-buttons").DataTable({
						dom : "Bfrtip",
						buttons : [ {
							extend : "copy",
							className : "btn-sm"
						}, {
							extend : "csv",
							className : "btn-sm"
						}, {
							extend : "excel",
							className : "btn-sm"
						}, {
							extend : "pdf",
							className : "btn-sm"
						}, {
							extend : "print",
							className : "btn-sm"
						} ],
						responsive : !0
					})
		}, TableManageButtons = function() {
			"use strict";
			return {
				init : function() {
					handleDataTableButtons()
				}
			}
		}();
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#datatable').dataTable();
			$('#datatable-keytable').DataTable({
				keys : true
			});
			$('#datatable-responsive').DataTable();
			$('#datatable-scroller').DataTable({
				ajax : "js/datatables/json/scroller-demo.json",
				deferRender : true,
				scrollY : 380,
				scrollCollapse : true,
				scroller : true
			});
			var table = $('#datatable-fixed-header').DataTable({
				fixedHeader : true
			});
		});
		TableManageButtons.init();
	</script>
</body>

</html>
