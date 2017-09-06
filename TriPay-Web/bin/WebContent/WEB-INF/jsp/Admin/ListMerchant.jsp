<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<sec:csrfMetaTags/>
<title>VPayQwik | Merchant List</title>

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
<script
	src="${pageContext.request.contextPath}/resources/admin/js/jquery.min.js"></script>

</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
				<jsp:include page="/WEB-INF/jsp/Admin/LeftMenu.jsp"/>
				<jsp:include page="/WEB-INF/jsp/Admin/TopNavigation.jsp"/>


			<div class="right_col" role="main">

				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>Merchant List</h3>
						</div>

						<div class="title_right">
							<div
								class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
								<div class="input-group">
									<input type="text" class="form-control"
										placeholder="Search for..."> <span
										class="input-group-btn">
										<button class="btn btn-default" type="button">Go!</button>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix"></div>

					<div class="row">
						<div class="col-md-12">
							<div class="x_panel">
								<div class="x_title">
									<h2>Merchants</h2>
									<ul class="nav navbar-right panel_toolbox">
										<li><a class="collapse-link"><i
												class="fa fa-chevron-up"></i></a></li>
										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-expanded="false"><i
												class="fa fa-wrench"></i></a>
											<ul class="dropdown-menu" role="menu">
												<li><a href="#">Settings 1</a></li>
												<li><a href="#">Settings 2</a></li>
											</ul></li>
										<li><a class="close-link"><i class="fa fa-close"></i></a>
										</li>
									</ul>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">

									<!-- start project list -->
									<table class="table table-striped projects">
										<thead>
											<tr>
												<th style="width: 1%">#</th>
												<th style="width: 20%">Merchant Name</th>
												<th>Payment Integration Project Name</th>
												<th>Project Progress</th>
												<th>Status</th>
												<th style="width: 20%">#Edit</th>
											</tr>
										</thead>
										<tbody id=userList>
											<c:forEach items="${merchants}" var="merchants">
												<tr>
													<td>#</td>
													<td><a>${merchants.username}</a> <br /> <small>Created
															: ${merchants.created} </small></td>
													<td>
													</td>
													<td class="project_progress">
														<div class="progress progress_sm">
															<div class="progress-bar bg-green" role="progressbar"
																data-transitiongoal="57"></div>
														</div> <small>57% Complete</small>
													</td>
													<td>
														<button type="button" class="btn btn-success btn-xs">Success</button>
													</td>
													<td><a href="#" class="btn btn-primary btn-xs"><i
															class="fa fa-folder"></i> View </a> <a href="#"
														class="btn btn-info btn-xs"><i class="fa fa-pencil"></i>
															Edit </a> <a
														href='<c:url value="GenertateInvoice/${merchants.username}"/>'
														class="btn btn-danger btn-xs"><i class="fa fa-folder"></i>
															Invoice </a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<!-- end project list -->

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
