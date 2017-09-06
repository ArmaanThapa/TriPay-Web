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

</head>
<body class="nav-md">
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
											type="date" name="endDate" value="endDate" /> <input
											type="submit" value="Submit" />
									</form>

									<ul class="nav navbar-right panel_toolbox">
										<%-- <li><form method="post"	action="${pageContext.request.contextPath}/Admin/downloadExcel"><input type="text" name="data" value="${pQTransaction}" hidden="hidden"/><input type="submit" value="Download"></form></li> --%>
										<form method="post"
											action="${pageContext.request.contextPath}/Admin/Report"
											modelAttribute="transactionReport">

											<c:forEach items="${userList}" var="contact"
												varStatus="status">
												<%-- <input name="transactions[${status.index}].created" value="${contact.created}"/></td> --%>
												<input name="transactions[${status.index}].amount"
													value="${contact.amount}" hidden="hidden" />
												<input name="transactions[${status.index}].currentBalance"
													value="${contact.currentBalance}" hidden="hidden" />
												<input name="transactions[${status.index}].status"
													value="${contact.status}" hidden="hidden" />
												<input name="transactions[${status.index}].transactionRefNo"
													value="${contact.transactionRefNo}" hidden="hidden" />
												<input name="transactions[${status.index}].serviceType"
													value="${contact.serviceType}" hidden="hidden" />
												<input name="transactions[${status.index}].commision"
													value="${contact.commision}" hidden="hidden" />
												<input name="transactions[${status.index}].username"
													value="${contact.username}" hidden="hidden" />
												<input name="transactions[${status.index}].contactNo"
													value="${contact.contactNo}" hidden="hidden" />
												<input name="transactions[${status.index}].debit"
													value="${contact.debit}" hidden="hidden" />
												<input name="transactions[${status.index}].credit"
													value="${contact.credit}" hidden="hidden" />
													<input name="transactions[${status.index}].dateOfTransaction"
													value="${contact.dateOfTransaction}" hidden="hidden" />
											</c:forEach>
											<input type="submit" value="Download" />
										</form>
										<li><a href="#"><i class="fa fa-chevron-up"></i></a></li>
										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-expanded="false"><i
												class="fa fa-wrench"></i></a> <!-- <ul class="dropdown-menu" role="menu">
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
											<c:forEach items="${userList}" var="userList">
												<tr>
													<td>User Name : ${userList.username}<br> Balance
														: ${userList.balance}<br>Number
														:${userList.contactNo}
													</td>
													<td>${userList.transactionRefNo}</td>
													<td>${userList.dateOfTransaction}</td>
													<td>${userList.serviceType}</td>
													<%-- <td>${userList.amount}</td>
													<td>${userList.amount}</td> --%>
													<td>${userList.status}</td>
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
