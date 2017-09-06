<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<sec:csrfMetaTags/>
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>VPayQwik | Invite Friends</title>
<link rel="stylesheet"
	href="<c:url value='/resources/css/font-awesome.min.css'/>" type='text/css'>

<!-- Optional theme -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap-theme.min.css'/>" type='text/css'>
<link href='<c:url value='/resources/css/font-family.css'/>'
	rel='stylesheet' type='text/css'>

<link href="<c:url value="/resources/css/css_style.css"/>"
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.min.css'/>" type='text/css'>
<script
	src="<c:url value='/resources/js/jquery.js'/>"></script>
<script
	src="<c:url value='/resources/js/bootstrap.js'/>"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/userdetails.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/header.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/invitefriends.js"></script>

<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>'
	type="image/png" />
<script src="https://www.google.com/recaptcha/api.js?onload=myCallBack&render=explicit"  async defer></script>
   <script>
      var recaptcha1;
      var recaptcha2;
      var myCallBack = function() {
    	  console.log("callback called");
        recaptcha1 = grecaptcha.render('recaptcha1', {
          'sitekey' : '6Let5SETAAAAAJ_G4499RIEKYc85RODmHdLV7xD3', //Replace this with your Site key
          'theme' : 'dark'
        });
        
        recaptcha2 = grecaptcha.render('recaptcha2', {
          'sitekey' : '6Let5SETAAAAAJ_G4499RIEKYc85RODmHdLV7xD3', //Replace this with your Site key
          'theme' : 'dark'
        });
        
      };
    </script>


<!-- Latest compiled and minified CSS -->

<%-- <link href='<c:url value="/resources/css/style_main.css"/>'
	rel='stylesheet' type='text/css'> --%>
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
	background: url(../images/pq.gif) center no-repeat #fff;
}
</style>
<script
	src="<c:url value='/resources/js/modernizr.js'/>"></script>

<script src="<c:url value="/resources/js/spinner.js" />"></script>
</head>

<body
	onLoad="ActiveMenu('MobileTopup');ActiveSubmenu('${menu}');ActiveFadeIn('${menu}');SelectOperator();">
<!-- 	<div class="se-pre-con"></div>
 -->	<jsp:include page="/WEB-INF/jsp/User/Header.jsp" />
	<jsp:include page="/WEB-INF/jsp/User/Menu.jsp" />

	<!-----------------end navbar---------------------->

	<!------------- end main-------------------->

	<div class="background"></div>
	<!---blue box---->

	<div class="container" id="money_box">
		<div class="row">
			<div class="col-md-4" id="Prepaid">
				<!---form---->
				<div class="box hidden-xs"></div>

				<ul class="nav nav-pills">
					<li class="active"><a data-toggle="pill" href="#IEmailFadeIn">Invite
							By Email</a></li>
					<li><a data-toggle="pill" href="#IMobileFadeIn">Invite By
							Mobile</a></li>
				</ul>

				<div class="tab-content" id="sendmoney">
					<div id="IEmailFadeIn" class="tab-pane fade in active">

						<form action="#" method="post">
							<div class="group_1">

								<input type="email" name="email" id="ife_email" required="required" /> <span class="highlight"></span> <span
									class="bar"></span> <label><spring:message
										code="page.user.inviteFriend.form.input.email" /></label>

								<p id="error_ife_email" class="error"></p>
							</div>
							<div class="group_1">
								<input type="text" name="receiversName" id="ife_receiver"
									required="required"> <span
									class="highlight"></span> <span class="bar"></span> <label><spring:message
										code="page.user.inviteFriend.form.input.receiver" /></label>

								<p class="error" id="error_ife_receiver"></p>
							</div>
		        <div class="group_1"><div id="recaptcha1"></div></div>		
							<button type="button" class="btn" id="ife_submit"
								style="width: 80%; background: #ff0000; color: #FFFFFF;">
								<spring:message code="page.user.inviteFriend.form.input.message" />
							</button>
						</form>


					</div>

					<div id="IMobileFadeIn" class="tab-pane fade">
						<form action="#" method="post">
							<div class="group_1">
								<input type="number" name="mobileNo" id="ifm_mobile" 
									required="required" /> <span class="highlight"></span> <span
									class="bar"></span> <label><spring:message
										code="page.user.inviteFriend.form.input.mobileNumber" /></label>

								<p id="ifm_mobile"></p>
							</div>
							<div class="group_1">
								<input type="text" name="receiversName" id="ifm_receiver"
									 required="required"> <span
									class="highlight"></span> <span class="bar"></span> <label><spring:message
										code="page.user.inviteFriend.form.input.receiver" /></label>

								<p class="error" id="ifm_receiver"></p>
							</div>
						<div class="group_1"><div id="recaptcha2"></div></div>		

							<button type="button" class="btn" id="ifm_submit"
								style="width: 80%; background: #ff0000; color: #FFFFFF;">
								<spring:message code="page.user.inviteFriend.form.input.message" />
							</button>
						</form>

					</div>
				</div>

			</div>
			<!----end col-md-4-->

			<div class="col-md-8 hidden-xs">
				<div class="slider" style="margin-right: -15px; margin-left: -15px;">
					<div class="carousel slide hidden-xs" data-ride="carousel"
						id="mycarousel">
						<ol class="carousel-indicators">
							<li class="active" data-slide-to="0" data-target="#mycarousel"></li>
							<li data-slide-to="1" data-target="#mycarousel"></li>
						</ol>

						<div class="carousel-inner">

							<div class="item active" id="slide1">
								<img src='<c:url value="/resources/images/slider_5.jpg"/>' />
							</div>
							<!---end item---->

							<div class="item">

								<img src='<c:url value="/resources/images/slider_6.jpg"/>'
									style="width:">
							</div>
							<!---end item---->
						</div>
						<!--end carousel inner------>

					</div>
					<!---end caeousel slider---->
				</div>
				<!---end slider----->
			</div>
		</div>
		<!-----end col-md-8-->
	</div>
	<!---end row-->
	<!----end container-->
	<jsp:include page="/WEB-INF/jsp/User/Footer.jsp" />
	<script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>
	<script type="text/javascript"
		src='<c:url value="/resources/js/wow.js"/>' /></script>
	<script>
		new WOW().init();
	</script>
		<script
	src="<c:url value='/resources/js/bootstrap.js'/>"></script>
	

</body>
</html>



