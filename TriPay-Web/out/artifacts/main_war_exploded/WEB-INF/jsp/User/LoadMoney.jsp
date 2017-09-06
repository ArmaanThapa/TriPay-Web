<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage="" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<sec:csrfMetaTags/>
<title>VPayQwik | Load Money</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">

	<link href='<c:url value='/resources/css/font-family.css'/>'
		  rel='stylesheet' type='text/css'>

<link rel="stylesheet"
	href="<c:url value='/resources/css/font-awesome.min.css'/>" type='text/css'>

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
	src="<c:url value="/resources/js/header.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/userdetails.js"/>"></script>
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>'
	type="image/png" />
<!-- Latest compiled and minified CSS -->

<link href='<c:url value="/resources/css/css_style.css"/>'
	rel='stylesheet' type='text/css'>

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

<body>
	<div class="se-pre-con"></div>
	<jsp:include page="/WEB-INF/jsp/User/Header.jsp" />
	<jsp:include page="/WEB-INF/jsp/User/Menu.jsp" />

	<!-----------------end navbar---------------------->

	<!------------- end main-------------------->

	<div class="background"></div>
	<!---blue box---->

	<div class="container" id="load">
		<div class="row">
			<div class="container" id="Account">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<div class="form-group">
						<h1>Load Money</h1>
						<hr>


					</div>
					<form method="post" name="customerData"
						action="<c:url value='/User/LoadMoney/Process' />"	class="form-inline">

						<div class="form-group">
							<c:out value="${msg}" default="" escapeXml="true"/>
							<div class="input-group">
								<div class="input-group-addon"><i class="fa fa-rupee"></i></div>
								<input type="number" class="form-control numeric" name="amount"
									 min="1" max="9999" placeholder="<spring:message code="page.user.loadMoney.form.input.amount"/>"
									 size="10" required />
									<sec:csrfInput/>
							</div>

						</div>
						<div class="form-group">
							<input type="radio" class="form-control" name="useVnet"  value="true" checked/>
							<img src="<c:url value="/resources/images/net.png"/>">
							<input type="radio" class="form-control" name="useVnet" value="false" />
							<img src="<c:url value="/resources/images/hd_Others.png"/>">
						</div>
						<input type="hidden" id="loadmoney_username" name="name" />
						<input type="hidden" id="loadmoney_phone" name="phone" />
						<input type="hidden" id="loadmoney_email" name="email" />
						<input type="hidden" id="loadmoney_address" name="address" />
						
						<!-- Button -->
						<div class="form-group">
								<input type="submit"
									value="<spring:message code="page.user.loadMoney.form.button"/>"  class="btn btn-block"
									style="background: #ff0000; color: #FFFFFF;"/>
						</div>
					</form>
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/jsp/User/Footer.jsp" />

</body>
</html>
<!-- ========================================================================================================================== -->

