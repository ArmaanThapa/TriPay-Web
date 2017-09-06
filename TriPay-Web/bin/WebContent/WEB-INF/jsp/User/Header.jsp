<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link rel="stylesheet"
	href="<c:url value='/resources/css/font-awesome.min.css'/>" type='text/css'>

	<div class="topline"></div>
	<nav class="navbar navbar-default"
		style="background: rgba(255, 255, 255, 1.00); min-height: 40px; margin-bottom: 0px;">
		<div class="container">
			<div class="navbar-header col-md-4 col-xs-8">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/User/Home"><img
					src='<c:url value="/resources/images/vijaya pay logo.png"/>' alt="logo" style="width: 250px" "></a>
			</div>
			<ul class="nav navbar-nav navbar-right" style="float: left;">
				<li><a href="${pageContext.request.contextPath}/User/LoadMoney/Process"> Rs.<h id="account_balance"></h> <img
						src='<c:url value="/resources/images/main/wallet.png"/>'
						alt="wallet" style="margin-left: 28px; width: 40px; "> <p>Add Money</p>
						 </a></li>
						 
				<div class="dropdown">
				   <li><a href="#"><p id="first_name"></p>
				   <img id="small_display_pic" class="img-circle"
							style=" margin-top: 15px;" width="40" height="40"/>
                     </a></li>
					<div class="dropdown-content">
						<a href="${pageContext.request.contextPath}/User/Home"><span class="glyphicon glyphicon-dashboard" ></span> Dashboard</a> 
						<a href="<c:url value="/User/Settings"/>"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Settings</a>
						<a href='<c:url value="/User/Receipts"/>'><span class="glyphicon glyphicon-duplicate" aria-hidden="true"></span> My Receipts</a>
						<a href="${pageContext.request.contextPath}/User/InviteFriends"><span class="glyphicon glyphicon-user" id="friends_left"></span><span class="glyphicon glyphicon-user" id="friends"></span> Invite Friends</a>
					    <a href="${pageContext.request.contextPath}/User/Logout"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> Logout</a>
					</div>
				</div>
			</ul>
		</div>
		<!-- /.container-fluid -->
	</nav>
