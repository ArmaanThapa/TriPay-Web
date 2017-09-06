<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<sec:csrfMetaTags/>
<title>VPayQwik | Add Merchant</title>

<!-- Bootstrap core CSS -->
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>' type="image/png" />

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
			

			<!-- page content -->
			<div class="right_col" role="main">

				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>Add Merchant</h3>
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

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
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

									<!-- Smart Wizard -->
                                    ${msg}

                                    <sf:form modelAttribute="addMerchant" action="/Admin/Merchant" method="post" enctype="multipart/form-data">
									<table class="table table-bordered">
                                        <tr>
											<td><h2>Basic Info</h2></td>
											<td></td>
										</tr>
										<tr>
										<td><sf:input path="firstName" placeholder="Merchant Name" class="form-ds"/></td>
                                        <td>${error.firstName}</td>
										</tr>
										<tr>
										<td><sf:input path="email" placeholder="Email ID" class="form-ds" /></td>

										</tr>
										<tr>
										<td><sf:input path="contactNo" placeholder="Mobile No" class="form-ds" /></td>

										</tr>
										<tr>
											<td><input type="file" name = "mlogo" placeholder="Choose Logo" accept="image/png" /></td>
											<td>${error.middleName}</td>
										</tr>
										<tr>
											<td><h2>PG Info</h2></td>
											<td></td>
										</tr>
										<tr>
										<td><sf:input path="ipAddress" placeholder="IP Address" class="form-ds" /></td>

										</tr>
										<tr>
										<td><sf:input path="successURL" placeholder="Success URL" /></td>

										</tr>
										<tr>
                                        <sec:csrfInput/>
										</tr>
										<tr>
										<td><sf:input path="failureURL" placeholder="Failure URL" /></td>

										</tr>
										<tr>
											<td><h2>Commission Info</h2></td>
											<td></td>
										</tr>
										<tr>
											<td>Fixed <sf:checkbox path="fixed" /></td>
											<td></td>
										</tr>
										<tr>
											<td>Min Amount<sf:input path="minAmount" placeholder="Minimum Amount" /></td>
											<td>${error.minAmount}</td>
										</tr>
										<tr>
											<td>Maximum Amount<sf:input path="maxAmount" placeholder="Maximum Amount" /></td>
											<td>${error.maxAmount}</td>
										</tr>
										<tr>
											<td>Amount to Charge<sf:input path="value" placeholder="Amount to charge" /></td>
											<td>${error.value}</td>
										</tr>
										<tr>
											<td colspan="2">
											<input type="submit" value="Add Merchant" class="btn-ds"/>
											</td>
										</tr>
									</table>
									</sf:form>

								</div>
							</div>


						</div>
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
