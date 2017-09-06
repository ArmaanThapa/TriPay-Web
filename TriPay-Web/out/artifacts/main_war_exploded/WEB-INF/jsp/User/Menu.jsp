<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>.wrap {
	position: relative;
	-webkit-transition: all 0.3s ease-out;
	-moz-transition: all 0.3s ease-out;
	-ms-transition: all 0.3s ease-out;
	-o-transition: all 0.3s ease-out;
	transition: all 0.3s ease-out;
}
.wrap.active {
	left: 16em;
}
a.menu-link {
	display: block;
	padding: 1em;
	z-index: 999;
}
nav[role=navigation] {
	background: #17bcc8;
	clear: both;
	overflow: hidden;
}
.js nav[role=navigation] {
	width: 16em;
	height: 100%;
	position: absolute;
	top: 0;
	left: -16em;
}
nav[role=navigation] ul {
	margin: 0;
	padding: 0;
	border-top: 1px solid #808080;
}
nav[role=navigation] li a {
	display: block;
	padding: 0.8em;
	color: #fff;
	border-bottom: 1px solid #fff;
}
.lorem {
	clear: both;
}

@media screen and (min-width: 41.25em) {
	a.menu-link {
		display: none;
	}
	.js nav[role=navigation] {
		max-width: none;
		position: static;
		width: auto;
	}
	.wrap.active {
		left: 0;
	}
	nav[role=navigation] ul {
		margin: 0;
		border: 0;
	}

	nav[role=navigation]  li {
		display: inline-block;
		margin: 0 0.25em;
	}
	nav[role=navigation] li a {
		border: 0;
	}

}</style>
<div class="wrap" id="wrap">
	<a href="#menu" class="menu-link" style="background: #17bcc8;"><i class="fa fa-bars fa-2x" style="color: white;"></i></a>
	<nav id="menu" role="navigation">
		<center><ul>
			<li><a href="<c:url value="/User/MobileTopup"/>"> <i class="fa fa-mobile fa-2x menufa"></i> <spring:message
					code="page.user.menu.topup" /></a></li>
			<li> <a
					href="<c:url value="/User/BillPayment"/>"> <i class="fa fa-lightbulb-o fa-2x menufa"></i> <spring:message
					code="page.user.menu.billPayment" /></a></li>
			<li> <a
					href="<c:url value="/User/SendMoney"/>"><i class="fa fa-money fa-2x menufa"></i>
				<spring:message code="page.user.menu.sendMoney" /></a></li>
			<li>
			<%--<a href="<c:url value="/User/Travel/BusTravel"/>"><i class="fa fa-suitcase fa-2x menufa" aria-hidden="true"style="font-size:25px;color:white"></i>Travel</a>--%>
				<a href="http://travel.vpayqwik.com/" target="_blank"> <i class="fa fa-suitcase fa-2x menufa"></i>
					<spring:message	code="page.user.menu.travel" /></a></li>
			<li> <a href='<spring:url value="/User/RedeemCoupon"/>'> <i class="fa fa-ticket fa-2x menufa"></i> <spring:message code="page.user.menu.coupon" /></a></li>
			<li> <a href="<spring:url value="/User/PayAtStore"/>"> <i class="fa fa-university fa-2x menufa"></i> <spring:message	code="page.user.menu.payStore" /></a></li>
		</ul></center>
	</nav>
	<script>$(document).ready(function() {
		$('body').addClass('js');

		var $menu = $('#menu'),
				$menulink = $('.menu-link'),
				$wrap = $('#wrap');

		$menulink.click(function() {
			$menulink.toggleClass('active');
			$wrap.toggleClass('active');
			return false;
		});
	});</script>
	</div>
