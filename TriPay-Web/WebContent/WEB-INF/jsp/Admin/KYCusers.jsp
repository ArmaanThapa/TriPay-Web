<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicontripays, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<sec:csrfMetaTags />
<title>TriPay | usersList</title>

<!-- Bootstrap core CSS -->
<link rel="icon" href='<c:url value="/resources/images/favicontripay.png"/>'
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

<!-- Custom styling plus plugins -->
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

	<link rel="stylesheet" href="<c:url value="/resources/css/datepicker.css"/>">
	<script src="<c:url value="/resources/js/datepicker.js"/>"></script>
	<script>
		$(function() {
			$( "#toDate" ).datepicker({
				format:"yyyy-mm-dd"
			});
			$( "#fromDate" ).datepicker({
				format:"yyyy-mm-dd"
			});
		});
	</script>

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
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>KYC users</h3>
						</div>

					</div>
					<div class="clearfix"></div>

					<div class="row">

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<!-- <h2>Button Example <small>Users</small></h2> -->
									
									</ul>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<p class="text-muted font-13 m-b-30">
									<form action="<c:url value="/Admin/filteredUserKYCResults"/>" method="post" class="form form-inline">
										From <input type="text" id="fromDate" name="startDate" class="form-control" readonly/>
										To <input type="text" id="toDate" name="endDate" class="form-control" readonly/>
										<sec:csrfInput/>
										<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-filter"></span></button>
									</form>
									<h4><c:out value="${msg}" escapeXml="true"/></h4>
									</p>
									<table id="datatable-buttons"
										class="table table-striped table-bordered date_sorted">
										<thead>
											<tr>
											<th>S.No</th>
												<th>User Account Details</th>
												<th>Personal Details</th>
												<th>Account</th>
												<th>Date</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${KYCuserlist}" var="slist" varStatus="loopCount">
											<tr>
											<td>${loopCount.count}</td>
											<td><b>UserName:</b><c:out value="${slist.username}"></c:out><br>
											<b>MobileNo:<a href="${pageContext.request.contextPath}/Admin/User/${slist.contactNo}"></b><c:out value="${slist.contactNo}"></c:out></a><br>
											<b>Email Id:</b><c:out value="${slist.email}"/></td>
											<td>
											<b>UserType:</b><c:out value="${slist.kycNonkyc}"/><br>
											<b>DOB:</b><c:out value="${slist.dob}"/><br>
											<b>Gender:</b><c:out value="${slist.gender}"/><br>
											<b>Authority:</b><c:out value="${slist.authority}"/>
											</td>
											<td>
											<b>Balance:</b><c:out value="${slist.balance}"/><br>
										<b>	Points: </b><c:out value="${slist.points}"/>
											</td>
											<td>
											<c:out value="${slist.dateOfAccountCreation}"/>
											</td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>

					</div>
				</div>


				<jsp:include page="/WEB-INF/jsp/Admin/Footer.jsp" />
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
    <script
            src="//cdn.datatables.net/plug-ins/1.10.11/sorting/date-dd-MMM-yyyy.js"></script>

    <!-- pace -->
    <script
            src="${pageContext.request.contextPath}/resources/admin/js/pace/pace.min.js"></script>
	<script type="text/javascript">
		var handleDataTableButtons = function() {
            console.log("inside datatable buttons");
			"use strict";
			0 !== $("#datatable-buttons").length
					&& $("#datatable-buttons").DataTable({
                columnDefs: [
                    {
                        type: 'date-dd-mmm-yyyy',
                        targets: 1
                    }
                ],
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
