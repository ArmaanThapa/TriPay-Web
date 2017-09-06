<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body onLoad="document.pay.submit()">
	<form method="post" id="pay" action="https://secure.ebs.in/pg/ma/payment/request">
		<input type="hidden" class="form-control" name="channel" value= "${loadmoney.channel}" />
		<input type="hidden" class="form-control" name="account_id" value="${loadmoney.accountId}" /> 
		<input type="hidden" class="form-control" name="reference_no" value="${loadmoney.referenceNo}" /> 
		<input type="hidden" class="form-control" name="amount" value="${loadmoney.amount}" /> 
		<input type="hidden" class="form-control" name="mode" value="${loadmoney.mode}" /> 
		<input type="hidden" class="form-control" name="currency" value="${loadmoney.currency}" /> 
		<input type="hidden" class="form-control" name="description" value="${loadmoney.description}" /> 
		<input type="hidden" class="form-control" name="return_url" value="${loadmoney.returnUrl}" />
		<input type="hidden" class="form-control" name="name" value="${loadmoney.name}" />
		<input type="hidden" class="form-control" name="address" value="${loadmoney.address}" /> 
		<input type="hidden" class="form-control" name="city" value="${loadmoney.city}" /> 
		<input type="hidden" class="form-control" name="state" value="${loadmoney.state}" /> 
		<input type="hidden" class="form-control" name="country" value="${loadmoney.country}" /> 
		<input type="hidden" class="form-control" name="postal_code" value="${loadmoney.postalCode}" />
		<input type="hidden" class="form-control" name="phone" value="${loadmoney.phone}"/> 
		<input type="hidden" class="form-control" name="email" value="${loadmoney.email}"/> 
		<input type="hidden" class="form-control" name="ship_name" value="${loadmoney.shipName}" />
		<input type="hidden" class="form-control" name="ship_address" value="${loadmoney.shipAddress}" /> 
		<input type="hidden" class="form-control" name="ship_city" value="${loadmoney.shipCity}" /> 
		<input type="hidden" class="form-control" name="ship_state" value="${loadmoney.shipState}" /> 
		<input type="hidden" class="form-control" name="ship_country" value="${loadmoney.shipCountry}" />
		<input type="hidden" class="form-control" name="ship_postal_code" value="${loadmoney.shipPostalCode}" /> 
		<input type="hidden" class="form-control" name="ship_phone" value="${loadmoney.shipPhone}" />
		<input type="hidden" class="form-control" name="secure_hash" value="${loadmoney.secureHash}" />
<!--   	 <input type="submit" value="submit" />
 --> 	</form>
  	<script>document.getElementById('pay').submit();</script> 
</body>
</html>