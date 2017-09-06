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
<title>VPayQwik | Promo Code List</title>

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

<!--[if lt IE 9]>
        <script src="../assets/js/ie8-responsive-file-warning.js"></script>
        <![endif]-->

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
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
					<div class="page-title">
						<div class="title_left">
							<h3>Promo Code List</h3>
						</div>


					</div>
					<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12">
							<div class="x_panel">
								<div class="x_title">

									<div class="clearfix"></div>
								</div>
								<div class="x_content">

									<!-- start project list -->
									<table class="table table-striped projects">
										<thead>
											<tr>
												<th>#</th>
												<th >Promo Code</th>
												<th>Min Amount</th>
												<th>Start Date</th>
												<th>End Date</th>
												<th>Description</th>
												<th>Fixed</th>
												<th>Value</th>
											</tr>
										</thead>
										<tbody id=userList>
											<c:forEach items="${list}" var="list">
												<tr>
													<td><i class="fa fa-chevron-circle-right" aria-hidden="true"></i></td>
													<td><c:out value="${list.promoCode}" default="" escapeXml="true"/></td>
													<td><c:out value="${list.terms}" default="" escapeXml="true"/> <br /> </td>
													<td><c:out value="${list.startDate}" default="" escapeXml="true"/> <br /> </td>
													<td><c:out value="${list.endDate}" default="" escapeXml="true"/><br /> </td>
													<td><c:out value="${list.description}" default="" escapeXml="true"/><br /> </td>
													<td><c:out value="${list.fixed}" default="" escapeXml="true"/> <br /> </td>
													<td><c:out value="${list.value}" default="" escapeXml="true"/> <br/> </td>

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
