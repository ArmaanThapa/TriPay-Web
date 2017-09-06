<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body onLoad="document.pay.submit()">
<h3>Please Wait while we're redirecting you to Payment Gateway...</h3>

	<form method="post" id="pay" action="https://secure.ebs.in/pg/ma/payment/request">
		<input type="hidden" class="form-control" name="channel" value= "${fn:escapeXml(loadmoney.channel)}"  />
		<input type="hidden" class="form-control" name="account_id" value="${fn:escapeXml(loadmoney.accountId)}"  />
		<input type="hidden" class="form-control" name="reference_no" value="${fn:escapeXml(loadmoney.referenceNo)}"  />
		<input type="hidden" class="form-control" name="amount" value="${fn:escapeXml(loadmoney.amount)}" />

		<input type="hidden" class="form-control" name="mode" value="${fn:escapeXml(loadmoney.mode)}" />
		<input type="hidden" class="form-control" name="currency" value="${fn:escapeXml(loadmoney.currency)}" />
		<input type="hidden" class="form-control" name="description" value="${fn:escapeXml(loadmoney.description)}"/>
		<input type="hidden" class="form-control" name="return_url" value="${fn:escapeXml(loadmoney.returnUrl)}" />
		<input type="hidden" class="form-control" name="name" value="${fn:escapeXml(loadmoney.name)}"/>
		<input type="hidden" class="form-control" name="address" value="${fn:escapeXml(loadmoney.address)}"  />
		<input type="hidden" class="form-control" name="city" value="${fn:escapeXml(loadmoney.city)}"/>
		<input type="hidden" class="form-control" name="state" value="${fn:escapeXml(loadmoney.state)}"/>
		<input type="hidden" class="form-control" name="country" value="${fn:escapeXml(loadmoney.country)}"/>
		<input type="hidden" class="form-control" name="postal_code" value="${fn:escapeXml(loadmoney.postalCode)}"/>
		<input type="hidden" class="form-control" name="phone" value="${fn:escapeXml(loadmoney.phone)}" />
		<input type="hidden" class="form-control" name="email" value="${fn:escapeXml(loadmoney.email)}" />
		<input type="hidden" class="form-control" name="ship_name" value="${fn:escapeXml(loadmoney.shipName)}" />
		<input type="hidden" class="form-control" name="ship_address" value="${fn:escapeXml(loadmoney.shipAddress)}"/>
		<input type="hidden" class="form-control" name="ship_city" value="${fn:escapeXml(loadmoney.shipCity)}" />
		<input type="hidden" class="form-control" name="ship_state" value="${fn:escapeXml(loadmoney.shipState)}"/>
		<input type="hidden" class="form-control" name="ship_country" value="${fn:escapeXml(loadmoney.shipCountry)}" />
		<input type="hidden" class="form-control" name="ship_postal_code" value="${fn:escapeXml(loadmoney.shipPostalCode)}"/>
		<input type="hidden" class="form-control" name="ship_phone" value="${fn:escapeXml(loadmoney.shipPhone)}"/>
		<input type="hidden" class="form-control" name="secure_hash" value="${fn:escapeXml(loadmoney.secureHash)}" />
 	</form>
  	<script>document.getElementById('pay').submit();</script> 
</body>
</html>