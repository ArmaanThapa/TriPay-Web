<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<sec:csrfMetaTags/>
<title>VPayQwik | Daily Report</title>

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
				

	
			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>VPayQwik Users</h3>
						</div>

					</div>
					<div class="clearfix"></div>

					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<div class="group_1"></div>
									<form method="post"
										action="${pageContext.request.contextPath}/Admin/Dailytransaction">
										<input type="date" name="startDate" value="startDate" /> <input
											type="date" name="endDate" value="endDate" />
											<sec:csrfInput/>
											 <input
											type="submit" value="Submit" />
									</form>

									<ul class="nav navbar-right panel_toolbox">
										<%-- <li><form method="post"	action="${pageContext.request.contextPath}/Admin/downloadExcel"><input type="text" name="data" value="${pQTransaction}" hidden="hidden"/><input type="submit" value="Download"></form></li> --%>
										<form method="post"
											action="${pageContext.request.contextPath}/Admin/Report"
											modelAttribute="transactionReport">

											<c:forEach items="${userList}" var="contact"
												varStatus="status">
												
												 <input name="transactions[${status.index}].amount"
													value="${contact.amount}" hidden="hidden" />
												<%-- <input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].amount"
													value="<c:out value="${contact.amount}"/>" hidden="hidden" /> --%>
													
													
												<%-- <input name="list[${status.index}].amount"	value="${contact.amount}" hidden="hidden" /> --%>
													
												<%-- <input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].balance"
													value="<c:out value="${contact.currentBalance}" default="" escapeXml="true"/>" hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].status"
													value="<c:out value="${contact.status}" default="" escapeXml="true"/>" hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].transactionRefNo"
													value="<c:out value="${contact.transactionRefNo}" escapeXml="true" default=""/>" hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].serviceType"
													value="${contact.serviceType}" hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].commision"
													value="<c:out value="${contact.commision}" default="" escapeXml="true"/>" hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].username"
													value="<c:out value="${contact.username}" escapeXml="true" default=""/>" hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].contactNo"
													value="<c:out value="${contact.contactNo}" escapeXml="true" default=""/>" hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].debit"
													value="<c:out value="${contact.debit}" escapeXml="true" default=""/>" hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].credit"
													value="<c:out value="${contact.credit}" escapeXml="true" default=""/>" hidden="hidden" />
													<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].dateOfTransaction"
													value="<c:out value="${contact.dateOfTransaction}" default="" escapeXml="true"/>" hidden="hidden" /> --%>
											</c:forEach>
											<sec:csrfInput/>
											<input type="submit" value="Download" />
										</form>
											<li><a href="#"><i class="fa fa-chevron-up"></i></a></li>
											<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-expanded="false"><i
												class="fa fa-wrench"></i></a> 
										</ul>
										<!-- <ul class="dropdown-menu" role="menu">
             							
             							   </li>				
										<li><a href="#"><i class="fa fa-close"></i></a></li>
									</ul>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<p class="text-muted font-13 m-b-30">
										<!--  DataTables has most features enabled by default, so all you need to do to use it with your own tables is to call the construction function: <code>$().DataTable();</code> -->
									</p>

									<table id="datatable"
										class="table table-striped table-bordered">
										<thead>
											<tr>
											 	<th>S.No</th>
												<th>User Details</th>
												<th>Transaction ID</th>
												<th>Transaction Date</th>
												<th>Transaction Type</th>
												<!-- <th>Debit</th>
												<th>Credit</th> -->
												<th>Status</th>
											</tr>
										</thead>
										<tbody id="userList">
											<c:forEach items="${userList}" var="userList" varStatus="loopCounter">
												<tr>
												<td>${loopCounter.count}</td>
													<td>User Name : <c:out value="${userList.username}" default="" escapeXml="true"/><br> Balance
														: <c:out value="${userList.balance}" default="" escapeXml="true"/><br>Number
														:<c:out value="${userList.contactNo}" default="" escapeXml="true"/>
													</td>
													<td><c:out value="${userList.transactionRefNo}" default="" escapeXml="true"/></td>
													<td><c:out value="${userList.dateOfTransaction}" default="" escapeXml="true"/></td>
													<td><c:out value="${userList.serviceType}" escapeXml="true" default=""/></td>

													<td><c:out value="${userList.status}" default="" escapeXml="true"/></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>

										</div>
				</div>


          <jsp:include page="/WEB-INF/jsp/Admin/Footer.jsp"/>
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
