<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="menu" id="main_menu">
	<div class="container">
		<div class="row" style="margin-top: 10px; margin-bottom: 10px;">
			<a href="${pageContext.request.contextPath}/User/MobileTopup"><spring:message
					code="page.user.menu.topup" /></a><a
				href="${pageContext.request.contextPath}/User/BillPayment"><spring:message
					code="page.user.menu.billPayment" /></a><a
				href="${pageContext.request.contextPath}/User/SendMoney">
				<spring:message code="page.user.menu.sendMoney" /></a>
				<a href="http://travel.vpayqwik.com/" target="_blank">
				<spring:message	code="page.user.menu.travel" /></a>
				<a href='<spring:url value="/User/RedeemCoupon"/>'><spring:message code="page.user.menu.coupon" /></a>
				<a href="<spring:url value="/User/PayAtStore"/>"><spring:message	code="page.user.menu.payStore" /></a>
		</div>
	</div>
</div>