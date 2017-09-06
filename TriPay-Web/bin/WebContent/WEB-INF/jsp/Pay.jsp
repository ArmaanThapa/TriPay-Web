<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body onLoad="document.pay.submit()">
	<form method="post" id="pay" action="https://172.16.1.10/Api/v1/User/Website/en/LoadMoney/Process">
		<input type="hidden" class="form-control" name="sessionId" value="${loadmoney.sessionId}" />
		<input type="hidden" class="form-control" name="channel" value="0" />
		<input type="hidden" class="form-control" name="account_id" value="20696" /> 
		<input type="hidden" class="form-control" name="reference_no" value="1" /> 
		<input type="hidden" class="form-control" name="amount" value="${loadmoney.amount}" /> 
		<input type="hidden" class="form-control" name="mode" value="TEST" /> 
		<input type="hidden" class="form-control" name="currency" value="INR" /> 
		<input type="hidden" class="form-control" name="description" value="Load Money through EBS" /> 
		<input type="hidden" class="form-control" name="return_url" value="https://66.207.206.54:8035/Api/v1/User/Website/en/LoadMoney/Redirect" />
		<input type="hidden" class="form-control" name="name" value="VPayQwik" />
		<input type="hidden" class="form-control" name="address" value="Bangalore" /> 
		<input type="hidden" class="form-control" name="city" value="Bangalore" /> 
		<input type="hidden" class="form-control" name="state" value="Karnataka" /> 
		<input type="hidden" class="form-control" name="country" value="IND" /> 
		<input type="hidden" class="form-control" name="postal_code" value="560034" />
		<input type="hidden" class="form-control" name="phone" value="9740116671" /> 
		<input type="hidden" class="form-control" name="email" value="prajun.adhikary@gmail.com" /> 
		<input type="hidden" class="form-control" name="ship_name" value="VPayQwik" />
		<input type="hidden" class="form-control" name="ship_address" value="Bangalore" /> 
		<input type="hidden" class="form-control" name="ship_city" value="Bangalore" /> 
		<input type="hidden" class="form-control" name="ship_state" value="Karnataka" /> 
		<input type="hidden" class="form-control" name="ship_country" value="IND" />
		<input type="hidden" class="form-control" name="ship_postal_code" value="560078" /> 
		<input type="hidden" class="form-control" name="ship_phone" value="9740116671" />
		<input type="hidden" class="form-control" name="secure_hash" value="1" />
		<input type="hidden" class="form-control" name="encryptedText" value="1" />
		<!-- <input type="submit" value="submit" /> -->
	</form>
	<script>document.getElementById('pay').submit();</script>
</body>
</html>