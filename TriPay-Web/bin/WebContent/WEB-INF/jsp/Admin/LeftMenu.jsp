<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">

					<div class="navbar nav_title" style="border: 0;">
						<!-- <a href="Home" class="site_title"><i class="fa fa-paw"></i> <span>VPayQwik</span></a> -->
						<a class="navbar-brand" href="#"><img src="/VPayQwik-Web/resources/images/vijayalogo.png" alt="" style="width: 190px;"></a>
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
							<h2>${UserName}</h2>
						</div>
					</div>
					<!-- /menu prile quick info -->
					<br />
					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<h3>General</h3>
							<ul class="nav side-menu">
								<li><a><i class="fa fa-home"></i> Users <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none">
										<li><a href="UserList">All</a></li>
										<li><a href="VerifiedUsers">Verified</a></li>
										<li><a href="UnverifiedUsers">Unverified</a></li>
										<li><a href="BlockedUsers">Blocked</a></li>
										<li><a href="ActiveUsers">Online</a></li>
										<li><a href="MaleUsers">Male User</a></li>
										<li><a href="FemaleUsers">Female User</a></li>
									</ul></li>
								<li><a><i class="fa fa-edit"></i> Reports <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none">
										<li><a href="DailyReport">Daily Reports</a></li>
										<!-- <li><a href="MonthlyReport">Monthly Reports</a></li> -->
									</ul></li>
								<li><a><i class="fa fa-desktop"></i> Logs <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none">
										<li><a href="SMSLog">Message Logs</a></li>
										<li><a href="EmailLog">Email Logs</a></li>
									</ul></li>
							</ul>
						</div>
						<div class="menu_section">
							<h3>Others</h3>
							<ul class="nav side-menu">
							<li><a><i class="fa fa-bug"></i> GCM Notification <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none">
										<li><a href="SendNotification">Send Notification</a></li>
									</ul></li>
								
								<li><a><i class="fa fa-windows"></i> Service <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none">
										<li><a href="#">Email</a></li>
										<li><a href="#">Bulk Email</a></li>
										<li><a href="SendPromotionalSMS">SMS</a></li>
										<li><a href="#">Bulk SMS</a></li>
									</ul></li>
								<li><a><i class="fa fa-bar-chart-o"></i> Merchant
										Service <span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none">
										<li><a href="ListMerchant">Merchant List</a></li>
										<li><a href="AddMerchant">Add Merchant</a></li>
									</ul></li>
						
							</ul>
						</div>
					</div>
					<!-- /sidebar menu -->

					<!-- /menu footer buttons -->
					<div class="sidebar-footer hidden-small">
						<a data-toggle="tooltip" data-placement="top" title="Settings">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="FullScreen">
							<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span
							class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
						</a> <a data-toggle="tooltip" data-placement="top" title="Logout">
							<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
						</a>
					</div>
					<!-- /menu footer buttons -->
				</div>
			</div>