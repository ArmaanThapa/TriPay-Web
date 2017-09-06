<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-md-3 left_col">
	<div class="left_col scroll-view">

		<div class="navbar nav_title" style="border: 0;">
			<a href="Home" class="site_title"><img
				src="<c:url value="/resources/images/tripaylogo.png"/>" alt=""
				style="width: 200px; margin-left: 6PX;"></a>
			<!-- <a class="navbar-brand" href="#"><img src="<c:url value="/resources/images/vijayalogo.png"/>" alt="" style="width: 190px;"></a>-->
		</div>
		<div class="clearfix"></div>
		<div class="profile">
			<div class="profile_pic">
				<img
					src="${pageContext.request.contextPath}/resources/admin/images/pic.png"
					alt="..." class="img-circle profile_img">
			</div>
			<div class="profile_info">
				<span>Welcome,</span>
				<h2>
					<c:out value="${UserName}" default="" escapeXml="true" />
				</h2>
			</div>
		</div>
		<!-- /menu prile quick info -->
		<br />
		<!-- sidebar menu -->
		<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
			<div class="menu_section">
				<h3>Admin Panel</h3>
				<ul class="nav side-menu">
					<li><a><i class="fa fa-home"></i> Users <span
							class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu" style="display: none">
							<li><a href="<c:url value="/Admin/UserList"/>">All</a></li>
							<li><a href="<c:url value="/Admin/VerifiedUsers"/>">Verified</a></li>
							<li><a href="<c:url value="/Admin/UnverifiedUsers"/>">Unverified</a></li>
							<li><a href="<c:url value="/Admin/BlockedUsers"/>">Blocked</a></li>
							<li><a href="<c:url value="/Admin/LockedUsers"/>">Locked</a></li>
							<li><a href="<c:url value="/Admin/ActiveUsers"/>">Online</a></li>
							<li><a href="<c:url value="/Admin/MaleUsers"/>">Male User</a></li>
							<li><a href="<c:url value="/Admin/FemaleUsers"/>">Female User</a></li>
							<li><a href="<c:url value="/Admin/KYCusers"/>">KYC Users</a></li>
							
							
						</ul>
					</li>
					<li><a><i class="fa fa-edit"></i> Reports <span
							class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu" style="display: none">
							<li><a href="<c:url value="/Admin/TransactionReport"/>">Transactions Report</a></li>
							<li><a href="<c:url value="/Admin/PoolAccount"/>">Pool Account
									Reports</a></li>
							<li><a href="<c:url value="/Admin/CommissionAccount"/>">Commission
									Reports</a></li>
							<li><a href="<c:url value="/Admin/SettlementAccount"/>">Settlement
									Reports</a></li>
							<li><a href="<c:url value="/Admin/NEFTList"/>">NEFT Requests</a></li>
						</ul></li>
					<li><a><i class="fa fa-desktop"></i> Logs <span
							class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu" style="display: none">
							<li><a href="<c:url value="/Admin/SMSLog"/>">Message Logs</a></li>
							<li><a href="<c:url value="/Admin/EmailLog"/>">Email Logs</a></li>
						</ul></li>
				</ul>

			</div>
			<div class="menu_section">
				<h3>Others</h3>
				<ul class="nav side-menu">
					<li><a><i class="fa fa-bug"></i> GCM Notification <span
							class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu" style="display: none">
							<li><a href="<c:url value='/Admin/SendNotification'/>">Send Notification</a></li>
						</ul></li>

					<li><a><i class="fa fa-windows"></i> Service <span
							class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu" style="display: none">
							<li><a href="#">Email</a></li>
							<li><a href="#">Bulk Email</a></li>
							<li><a href="<c:url value="/Admin/SendPromotionalSMS"/>">SMS</a></li>
							<li><a href="#">Bulk SMS</a></li>
							<li><a href="Version">Version</a></li>
						</ul></li>
					<li><a><i class="fa fa-bar-chart-o"></i> Merchant Service
							<span class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu" style="display: none">
							<li><a href="<c:url value="/Admin/ListMerchant"/>">Merchant
									List</a></li>
							<li><a href="<c:url value="/Admin/Merchant"/>">Add Merchant</a></li>
							<li><a href="<c:url value="/Admin/MerchantNEFTList"/>">Merchant NEFT Requests</a></li>
						</ul></li>

					<li><a><i class="fa fa-windows"></i> Promo Code <span
							class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu" style="display: none">
							<li><a href="GeneratePromoCode">Generate Promo Code</a></li>
							<li><a href="PromoCodeList">List Promo Codes</a></li>
							<li><a href="<c:url value='/Admin/PromoTransactions'/>">Transactions</a></li>
						</ul></li>

				</ul>
			</div>
		</div>
		<!-- /sidebar menu -->

		<!-- /menu footer buttons -->
		<div class="sidebar-footer hidden-small">
			<a data-toggle="tooltip" data-placement="top" title="Settings"> <span
				class="glyphicon glyphicon-cog" aria-hidden="true"></span>
			</a> <a data-toggle="tooltip" data-placement="top" title="FullScreen">
				<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
			</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span
				class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
			</a> <a data-toggle="tooltip" data-placement="top" title="Logout"> <span
				class="glyphicon glyphicon-off" aria-hidden="true"></span>
			</a>
		</div>
		<!-- /menu footer buttons -->
	</div>
</div>