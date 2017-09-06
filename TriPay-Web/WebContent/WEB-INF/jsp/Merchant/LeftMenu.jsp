<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-md-3 left_col">
	<div class="left_col scroll-view">

		<div class="navbar nav_title" style="border: 0;">
			<a href="Home" class="site_title"><img
				src="${user.image}" alt=""
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
					<c:out value="${user.firstName} ${user.lastName}" default="" escapeXml="true" />
				</h2>
			</div>
		</div>
		<!-- /menu prile quick info -->
		<br />
		<!-- sidebar menu -->
		<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
			<div class="menu_section">
				<h3>Merchant Panel</h3>
				<ul class="nav side-menu">
					<li><a><i class="fa fa-edit"></i> Reports <span
							class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu" style="display: none">
							<li><a href="<c:url value="/Merchant/Transactions"/>">Transactions Report</a></li>

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