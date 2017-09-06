<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%-- <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<sec:csrfMetaTags/>
<title>VPayQwik | User</title>

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

			<jsp:include page="/WEB-INF/jsp/Admin/LeftMenu.jsp" />
			<jsp:include page="/WEB-INF/jsp/Admin/TopNavigation.jsp" />


			<!-- page content -->
			<div class="right_col" role="main">

				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>
								Profile Type : <i></i>
							</h3>
							<h6>
								Mobile OTP Code :
								<code><c:out value="${user.mobileToken}" escapeXml="true" default=""/></code>
							</h6>
						</div>

						<div class="title_right"></div>
					</div>
					<div class="clearfix"></div>

					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>
										Authority :
										<code><c:out value="${user.authority}" escapeXml="true" default=""/></code>
									</h2>
									<br /> <br />
									<h2>
										Mobile Status :
										<code><c:out value="${user.mobileStatus}" default="" escapeXml="true"/></code>
									</h2>

									<br /> <br />
									<h2>
										Email Status :
										<code><c:out value="${user.emailStatus}" default="" escapeXml="true"/></code>
									</h2>
									<ul class="nav navbar-right panel_toolbox">
										
									</ul>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">

									<div class="col-md-3 col-sm-3 col-xs-12 profile_left">

										<div class="profile_img">

											<!-- end of image cropping -->
											<div id="crop-avatar">
												<!-- Current avatar -->
												<div class="avatar-view" title="Change the avatar">
													 <img src='<c:url value="${user.image}"/>'
														alt="Avatar"> 
														
													<%-- <img alt=""
														src="https://www.vpayqwik.com/${user.image}" />"> --%>
												</div>

												<!-- Cropping modal -->
												<div class="modal fade" id="avatar-modal" aria-hidden="true"
													aria-labelledby="avatar-modal-label" role="dialog"
													tabindex="-1">
													<div class="modal-dialog modal-lg">
														<div class="modal-content">
															<form class="avatar-form" action="crop.php"
																enctype="multipart/form-data" method="post">
																<div class="modal-header">
																	<button class="close" data-dismiss="modal"
																		type="button">&times;</button>
																	<h4 class="modal-title" id="avatar-modal-label">Change
																		Avatar</h4>
																</div>
																<div class="modal-body">
																	<div class="avatar-body">

																		<!-- Upload image and data -->
																		<div class="avatar-upload">
																			<input class="avatar-src" name="avatar_src"
																				type="hidden"> <input class="avatar-data"
																				name="avatar_data" type="hidden"> <label
																				for="avatarInput">Local upload</label> <input
																				class="avatar-input" id="avatarInput"
																				name="avatar_file" type="file">
																		</div>

																		<!-- Crop and preview -->
																		<div class="row">
																			<div class="col-md-9">
																				<div class="avatar-wrapper"></div>
																			</div>
																			<div class="col-md-3">
																				<div class="avatar-preview preview-lg"></div>
																				<div class="avatar-preview preview-md"></div>
																				<div class="avatar-preview preview-sm"></div>
																			</div>
																		</div>

																		<div class="row avatar-btns">
																			<div class="col-md-9">
																				<div class="btn-group">
																					<button class="btn btn-primary"
																						data-method="rotate" data-option="-90"
																						type="button" title="Rotate -90 degrees">Rotate
																						Left</button>
																					<button class="btn btn-primary"
																						data-method="rotate" data-option="-15"
																						type="button">-15deg</button>
																					<button class="btn btn-primary"
																						data-method="rotate" data-option="-30"
																						type="button">-30deg</button>
																					<button class="btn btn-primary"
																						data-method="rotate" data-option="-45"
																						type="button">-45deg</button>
																				</div>
																				<div class="btn-group">
																					<button class="btn btn-primary"
																						data-method="rotate" data-option="90"
																						type="button" title="Rotate 90 degrees">Rotate
																						Right</button>
																					<button class="btn btn-primary"
																						data-method="rotate" data-option="15"
																						type="button">15deg</button>
																					<button class="btn btn-primary"
																						data-method="rotate" data-option="30"
																						type="button">30deg</button>
																					<button class="btn btn-primary"
																						data-method="rotate" data-option="45"
																						type="button">45deg</button>
																				</div>
																			</div>
																			<div class="col-md-3">
																				<button
																					class="btn btn-primary btn-block avatar-save"
																					type="submit">Done</button>
																			</div>
																		</div>
																	</div>
																</div>
																<!-- <div class="modal-footer">
                                                  <button class="btn btn-default" data-dismiss="modal" type="button">Close</button>
                                                </div> -->
															</form>
														</div>
													</div>
												</div>
												<!-- /.modal -->

												<!-- Loading state -->
												<div class="loading" aria-label="Loading" role="img"
													tabindex="-1"></div>
											</div>
											<!-- end of image cropping -->

										</div>
										<h3><c:out value="${user.firstName}" escapeXml="true" default=""/>
											<%-- ${user.lastName} --%>
										</h3>

										<ul class="list-unstyled user_data">
											<li><i class="fa fa-map-marker user-profile-icon"></i>
												<c:out value="${user.email}" escapeXml="true" default=""/></li>

											<li><i class="fa fa-briefcase user-profile-icon"></i>
												<c:out value="${user.contactNo}" escapeXml="true" default=""/></li>

											<li class="m-top-xs"><i
												class="fa fa-external-link user-profile-icon"></i> <a
												href="#"> <c:out value="${user.dateOfTransaction}" escapeXml="true" default=""/></a></li>
										</ul>

										<%-- <a class="btn btn-danger"><i class="fa fa-edit m-right-xs"></i>Block
											${user..firstName}</a> <br /> --%>

										<c:if test="${fn:containsIgnoreCase( user.authority, 'ROLE_AUTHENTICATED')}">
											<a class="btn btn-danger"
											   href='<spring:url value="/Admin/User/Block/${user.contactNo}" />'>Lock <i class="fa fa-lock" aria-hidden="true"></i></a>
										</c:if>
										<c:if test="${fn:containsIgnoreCase( user.authority, 'ROLE_LOCKED')}">
										<a class="btn btn-success"
											href='<spring:url value="/Admin/User/Unblock/${user.contactNo}" />'>Unlock<i class="fa fa-unlock" aria-hidden="true"></i></a>
										</c:if>
										<c:if test="${fn:containsIgnoreCase( user.authority, 'ROLE_BLOCKED')}">
											<a class="btn btn-info"
											   href='<spring:url value="/Admin/User/Unblock/${user.contactNo}" />'>Unblock <i class="fa fa-ban" aria-hidden="true"></i></a>
										</c:if>
									</div>
									<div class="col-md-9 col-sm-9 col-xs-12">

										<div class="profile_title">
											<div class="col-md-6">
												<h2>User Activity Report</h2>
											</div>
											<div class="col-md-6"></div>
										</div>
										<canvas id="lineChart"></canvas>
									</div>
								</div>
							</div>
							<%-- <div class="" role="tabpanel" data-example-id="togglable-tabs">
								<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
									<li role="presentation" class="active"><a
										href="#tab_content1" id="home-tab" role="tab"
										data-toggle="tab" aria-expanded="true"><c:out value="${user.firstName}" escapeXml="true" default=""/>
											Transactions</a></li>


											<li style="float: right;"><form method="post"
											action="${pageContext.request.contextPath}/Admin/Report" modelAttribute="transactionReport">
											<c:forEach items="${userList}" var="contact"
												varStatus="status">
												<input name="transactions[${status.index}].created" value="${contact.created}"/></td>
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].amount"
													value="<c:out value="${contact.amount}" escapeXml="true" default=""/>" hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].currentBalance"
													value="<c:out value="${contact.currentBalance}" escapeXml="true" default=""/>" hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].status"
													value="<c:out value="${contact.status}" escapeXml="true" default=""/>" hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].transactionRefNo"
													value="<c:out value="${contact.transactionRefNo}" escapeXml="true" default=""/>" hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].serviceType"
													value="<c:out value="${contact.serviceType}" escapeXml="true" default=""/>" hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].commision"
													value="<c:out value="${contact.commision}" default="" escapeXml="true"/>" hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].username"
													value="<c:out value="${user.firstName}" default="" escapeXml="true"/> <c:out value="${user.lastname}" escapeXml="true" default=""/> " hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].contactNo"
													value="<c:out value="${contact.contactNo}" escapeXml="true" default=""/>" hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].debit"
													value="<c:out value="${contact.debit}" escapeXml="true" default=""/>" hidden="hidden" />
												<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].credit"
													value="<c:out value="${contact.credit}" escapeXml="true" default=""/>" hidden="hidden" />
													<input name="transactions[<c:out value="${status.index}" escapeXml="true" default=""/>].dateOfTransaction"
													value="<c:out value="${contact.dateOfTransaction}" escapeXml="true" default=""/>" hidden="hidden" />
											</c:forEach>
											<input type="submit" value="Download" />
										</form></li>
											
								</ul>
								<div id="myTabContent" class="tab-content">
									<div role="tabpanel" class="tab-pane fade active in"
										id="tab_content1" aria-labelledby="home-tab">

										<!-- start recent activity -->
										<div class="x_content">
											<p class="text-muted font-13 m-b-30"></p>
											<table id="datatable"
												class="table table-striped table-bordered">
												<thead>
													<tr>
														
														<th>Transaction Date</th>
														<th>Amount</th>
														<th>Balance</th>
														<th>Transaction Type</th>
														 <th>Status</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${userList}" var="userList">
														<tr>
															<td><c:out value="${userList.dateOfTransaction}" escapeXml="true" default=""/></td>
															<td><c:out value="${userList.amount}" escapeXml="true" default=""/></td>
															<td><c:out value="${userList.currentBalance}" escapeXml="true" default=""/></td>
															<td><c:out value="${userList.serviceType}" escapeXml="true" default=""/></td>
															<td><c:out value="${userList.status}" escapeXml="true" default=""/></td>

														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<!-- end recent activity -->

									</div>

								</div>
							</div> --%>
							<div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                   <!--  <h2>Button Example <small>Users</small></h2> -->
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        <ul class="dropdown-menu" role="menu">
                          <!-- <li><a href="#">Settings 1</a>
                          </li>
                          <li><a href="#">Settings 2</a>
                          </li> -->
                        </ul>
                      </li>
                      <li><a class="close-link"><i class="fa fa-close"></i></a>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <p class="text-muted font-13 m-b-30">
<!--                       The Buttons extension for DataTables provides a common set of options, API methods and styling to display buttons on a page that will interact with a DataTable. The core library provides the based framework upon which plug-ins can built.
 -->                    </p>
                    <table id="datatable-buttons" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                        <th>S.No</th>
						<th>User Details</th>
						<th>Transaction ID</th>
						<th>Transaction Date</th>
						<th>Transaction Type</th>
						<th>Credit</th>
						<th>Debit</th>
						<th>Status</th>
						<!-- <th>Debit</th>
						<th>Credit</th> -->
                        </tr>
                      </thead>
					<tbody id="userList">
											<c:forEach items="${userList}" var="userList" varStatus="loopCounter">
												<tr>
												<td>${loopCounter.count}</td>
													<td><c:out value="${userList.username}" default="" escapeXml="true"/>  
													Bal. <c:out value="${userList.currentBalance}" default="" escapeXml="true"/>   |
														<c:out value="${userList.contactNo}" default="" escapeXml="true"/>
													</td>
													<td><c:out value="${userList.transactionRefNo}" default="" escapeXml="true"/></td>
													<td><c:out value="${userList.dateOfTransaction}" default="" escapeXml="true"/></td>
													<td><c:out value="${userList.serviceType}" escapeXml="true" default=""/></td>
													<td><c:out value="${userList.credit}" default="" escapeXml="true" /></td>
													<td><c:out value="${userList.debit}" default="" escapeXml="true" /></td>
													<td><c:out value="${userList.status}" default="" escapeXml="true" /></td>
												</tr>
											</c:forEach>
										</tbody>
                    </table>
                  </div>
                </div>
              </div>
						</div>
					</div>
				</div>
			</div>

			<!-- footer content -->
				<jsp:include page="/WEB-INF/jsp/Admin/Footer.jsp"/>
			<!-- /footer content -->

		</div>
		<!-- /page content -->
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
		src="${pageContext.request.contextPath}/resources/admin/js/cropping/cropper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/cropping/main.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/moment/moment.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/datepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/chartjs/chart.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.scroller.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/admin/js/moris/raphael-min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/moris/morris.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/admin/js/pace/pace.min.js"></script>

	<script>
		Chart.defaults.global.legend = {
			enabled : false
		};

		// Line chart
		
		console.log("${day}");
		console.log("${month}");
		console.log("${year}");
		
		var ctx = document.getElementById("lineChart");
		var lineChart = new Chart(
				ctx,
				{
					type : 'line',
					data : {
						labels : [
								"${day} - ${month} - ${year}",
								"${day}" + 1
										+ " - ${month} - ${year}",
								"${day}" + 2
										+ " - ${month} - ${year}",
								"${day}" + 3
										+ " - ${month} - ${year}",
								"${day}" + 4
										+ " - ${month} - ${year}",
								"${day}" + 5
										+ " - ${month} - ${year}", ],
						datasets : [
								{
									label : "",
									backgroundColor : "",
									borderColor : "",
									pointBorderColor : "",
									pointBackgroundColor : "",
									pointHoverBackgroundColor : "",
									pointHoverBorderColor : "",
									pointBorderWidth : 1,
									data : [ 0, 0, 0, 0, 0, 0, 0 ]
								},
								{
									label : "Transaction",
									backgroundColor : "#0099ff",
									borderColor : "rgba(3, 88, 106, 0.70)",
									pointBorderColor : "rgba(3, 88, 106, 0.70)",
									pointBackgroundColor : "rgba(3, 88, 106, 0.70)",
									pointHoverBackgroundColor : "#fff",
									pointHoverBorderColor : "rgba(151,187,205,1)",
									pointBorderWidth : 1,
									data : [ "${aa}", "${bb}",
											"${cc}", "${dd}",
											"${ee}", "${ff}",
											"${gg}" ]
								} ]
					},
				});

		// Bar chart

		// Doughnut chart

		// Radar chart

		// Pie chart

		// PolarArea chart
	</script>

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
	<script>
		$(function() {
			var day_data = [ {
				"period" : "Jan",
				"Hours worked" : 80
			}, {
				"period" : "Feb",
				"Hours worked" : 125
			}, {
				"period" : "Mar",
				"Hours worked" : 176
			}, {
				"period" : "Apr",
				"Hours worked" : 224
			}, {
				"period" : "May",
				"Hours worked" : 265
			}, {
				"period" : "Jun",
				"Hours worked" : 314
			}, {
				"period" : "Jul",
				"Hours worked" : 347
			}, {
				"period" : "Aug",
				"Hours worked" : 287
			}, {
				"period" : "Sep",
				"Hours worked" : 240
			}, {
				"period" : "Oct",
				"Hours worked" : 211
			} ];
			Morris.Bar({
				element : 'graph_bar',
				data : day_data,
				xkey : 'period',
				hideHover : 'auto',
				barColors : [ '#26B99A', '#34495E', '#ACADAC', '#3498DB' ],
				ykeys : [ 'Hours worked', 'sorned' ],
				labels : [ 'Hours worked', 'SORN' ],
				xLabelAngle : 60
			});
		});
	</script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {

							var cb = function(start, end, label) {
								console.log(start.toISOString(), end
										.toISOString(), label);
								$('#reportrange span').html(
										start.format('MMMM D, YYYY') + ' - '
												+ end.format('MMMM D, YYYY'));
								//alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
							}

							var optionSet1 = {
								startDate : moment().subtract(29, 'days'),
								endDate : moment(),
								minDate : '01/01/2012',
								maxDate : '12/31/2015',
								dateLimit : {
									days : 60
								},
								showDropdowns : true,
								showWeekNumbers : true,
								timePicker : false,
								timePickerIncrement : 1,
								timePicker12Hour : true,
								ranges : {
									'Today' : [ moment(), moment() ],
									'Yesterday' : [
											moment().subtract(1, 'days'),
											moment().subtract(1, 'days') ],
									'Last 7 Days' : [
											moment().subtract(6, 'days'),
											moment() ],
									'Last 30 Days' : [
											moment().subtract(29, 'days'),
											moment() ],
									'This Month' : [ moment().startOf('month'),
											moment().endOf('month') ],
									'Last Month' : [
											moment().subtract(1, 'month')
													.startOf('month'),
											moment().subtract(1, 'month')
													.endOf('month') ]
								},
								opens : 'left',
								buttonClasses : [ 'btn btn-default' ],
								applyClass : 'btn-small btn-primary',
								cancelClass : 'btn-small',
								format : 'MM/DD/YYYY',
								separator : ' to ',
								locale : {
									applyLabel : 'Submit',
									cancelLabel : 'Clear',
									fromLabel : 'From',
									toLabel : 'To',
									customRangeLabel : 'Custom',
									daysOfWeek : [ 'Su', 'Mo', 'Tu', 'We',
											'Th', 'Fr', 'Sa' ],
									monthNames : [ 'January', 'February',
											'March', 'April', 'May', 'June',
											'July', 'August', 'September',
											'October', 'November', 'December' ],
									firstDay : 1
								}
							};
							$('#reportrange span').html(
									moment().subtract(29, 'days').format(
											'MMMM D, YYYY')
											+ ' - '
											+ moment().format('MMMM D, YYYY'));
							$('#reportrange').daterangepicker(optionSet1, cb);
							$('#reportrange').on('show.daterangepicker',
									function() {
										console.log("show event fired");
									});
							$('#reportrange').on('hide.daterangepicker',
									function() {
										console.log("hide event fired");
									});
							$('#reportrange')
									.on(
											'apply.daterangepicker',
											function(ev, picker) {
												console
														.log("apply event fired, start/end dates are "
																+ picker.startDate
																		.format('MMMM D, YYYY')
																+ " to "
																+ picker.endDate
																		.format('MMMM D, YYYY'));
											});
							$('#reportrange').on('cancel.daterangepicker',
									function(ev, picker) {
										console.log("cancel event fired");
									});
							$('#options1').click(
									function() {
										$('#reportrange').data(
												'daterangepicker').setOptions(
												optionSet1, cb);
									});
							$('#options2').click(
									function() {
										$('#reportrange').data(
												'daterangepicker').setOptions(
												optionSet2, cb);
									});
							$('#destroy').click(
									function() {
										$('#reportrange').data(
												'daterangepicker').remove();
									});
						});
	</script>

</body>

</html>
