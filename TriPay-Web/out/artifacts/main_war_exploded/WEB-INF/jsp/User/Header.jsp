<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link rel="stylesheet"
	href="<c:url value='/resources/css/font-awesome.min.css'/>" type='text/css'>
<link href='<c:url value='/resources/css/font-family.css'/>'
	  rel='stylesheet' type='text/css'>
	  

	<div class="topline"></div>
	<nav class="navbar navbar-default"
		style="background: rgba(255, 255, 255, 1.00); min-height: 40px; margin-bottom: 0px;">
		<div class="container">
			<div class="navbar-header col-md-4 col-xs-8">
				<a class="navbar-brand" href="<c:out value="/User/Home"/>"><img
					src='<c:url value="/resources/images/vijaya pay logo.png"/>' alt="logo" style="width: 250px" ></a>
			</div>
			<ul class="nav navbar-nav navbar-right" style="float: left;">
			
			
				<li><a href="<c:url value="/User/LoadMoney/Process"/>"> <i class="fa fa-rupee"></i> <h id="account_balance">${balance}</h> <img
						src='<c:url value="/resources/images/main/wallet.png"/>'
						alt="wallet" style="margin-left: 28px; width: 40px; "> <p>Add Money</p>
						 </a></li>
						 
				<div class="dropdown" >
				   <li><a href="#"><p id="first_name"></p>
				   <img id="small_display_pic" src="<c:url value="/resources/images/spinner.gif"/>" class="img-circle"
							style=" margin-top: 15px;" width="40" height="40"/>
                     </a></li>
					<div class="dropdown-content">
						<a href="<c:url value="/User/Home"/>"><i class="fa fa-tachometer" style="color: #17bcc8"></i> Dashboard</a>
						<a href="<c:url value="/User/Settings"/>"><i class="fa fa-cogs" aria-hidden="true" style="color: #17bcc8"></i> Settings</a>
						<a href="<c:url value="/User/UpdateWallet"/>" id="hidethis1"><img src='<c:url value="/resources/images/main/wallet.png"/>' alt="wallet" style="width: 17px; "> <i class="fa fa-plus" style="color: #17bcc8"></i> Update Wallet</a>
						<a href="<c:url value="/User/UpgradeWallet"/>" id="hidethis2"><img src='<c:url value="/resources/images/main/wallet.png"/>' alt="wallet" style="width: 17px; "> <i class="fa fa-plus" style="color: #17bcc8"></i> Upgrade Wallet</a>
						<a href='<c:url value="/User/Receipts"/>'><i class="fa fa-files-o" aria-hidden="true" style="color: #17bcc8"></i> My Receipts</a>
						<a href="<c:url value="/User/InviteFriends"/>"><i class="fa fa-group" aria-hidden="true" style="color: #17bcc8"></i> Invite Friends/Family</a>
					    <a href="<c:url value="/User/Logout"/>"><i class="fa fa-sign-out" aria-hidden="true" style="color: #17bcc8"></i> Logout</a>
					</div>
				</div>
			</ul>
		</div>
		<!-- /.container-fluid -->
	</nav>