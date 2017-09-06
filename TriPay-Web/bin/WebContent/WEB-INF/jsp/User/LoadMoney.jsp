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
	src="${pageContext.request.contextPath}/resources/js/header.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/userdetails.js"></script>
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>'
	type="image/png" />
<!-- Latest compiled and minified CSS -->

<%-- <link href='<c:url value="/resources/css/style_main.css"/>'
	rel='stylesheet' type='text/css'> --%>
<link href='<c:url value="/resources/css/css_style.css"/>'
	rel='stylesheet' type='text/css'>

</head>

<body
	onLoad="ActiveMenu('MobileTopup');ActiveSubmenu('${menu}');ActiveFadeIn('${menu}');">
	<jsp:include page="/WEB-INF/jsp/User/Header.jsp" />
	<jsp:include page="/WEB-INF/jsp/User/Menu.jsp" />

	<!-----------------end navbar---------------------->

	<!------------- end main-------------------->

	<div class="background"></div>
	<!---blue box---->

	<div class="container" id="load">
		<div class="row" style="background: url(img/bg.jpg) no-repeat;">
			<div class="container" id="Account">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<div class="form-group">
						<h1>Load Money</h1>
						<hr>
						<p class="error">${msg}</p>
					</div>
					<form method="post" name="customerData"
						action="<c:url value='/User/LoadMoney/Process' />"
						class="form-inline">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">Rs.</div>
								<input type="number" class="form-control" name="amount" value=""
									placeholder="<spring:message code="page.user.loadMoney.form.input.amount"/>"
									maxlength="5" size="10" required />
									<sec:csrfInput/>
							</div>
							<p>${error.amount}</p>
						</div>
						<input type="hidden" id="loadmoney_username" name="name" value="Vibhanshu"/>
						<input type="hidden" id="loadmoney_address" name="address" value="koramangala"/>
						<input type="hidden" id="loadmoney_city" name="city" value="Bangalore"/>
						<input type="hidden" id="loadmoney_state" name="state" value="Karnataka"/>
						<input type="hidden" id="loadmoney_country" name="country" value="IND" />
						<input type="hidden" id="loadmoney_postal_code" name="postalCode" value="560034"/>
						<input type="hidden" id="loadmoney_phone" name="phone" value="8769986881"/>
						<input type="hidden" id="loadmoney_email" name="email" value="vibhanshuvyas60@gmail.com"/>
												
						
						<!-- Button -->
						<div class="form-group">
							<div class="col-sm-offset-2">
								<input type="submit" id="lm_submit"
									value="<spring:message code="page.user.loadMoney.form.button"/>"
									class="btn btn-primary btn-md"
									style="width: 100px; margin-top: -10px;">
							</div>
						</div>
					</form>
				</div>
				<div class="col-md-2"></div>
			</div>
			<!--div account-->
		</div>
		<!---end row --->
	</div>
	<!---- end aboutus-->



	<jsp:include page="/WEB-INF/jsp/User/Footer.jsp" />

</body>
</html>


<!-- ========================================================================================================================== -->

