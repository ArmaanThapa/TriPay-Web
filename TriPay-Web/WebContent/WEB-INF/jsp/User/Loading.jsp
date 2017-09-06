<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Processing..</title>

<script type="text/javascript">
	function Redirect() {
//		window.location = "http://fgmtest.firstglobalmoney.com:8034/User/Home";
		window.location = "https://vpayqwik.com/User/Home";
//		window.location = "http://localhost:8080/User/Home";

	}
	document.write("Processing...");
	setTimeout('Redirect()', 5000);
</script>

</head>
<body>
	<center>
		<div style="margin-top: 13%;">
			<img src='<c:url value='/images/pq_large.gif'/>' alt="Loading...">
			<div>
				<p>
					<b
						style="color: #08bbc7; font-family: sans-serif; font-size: 15pt;">Please
						wait, do not close the window or press the back button </b>
				</p>
			</div>
		</div>
	</center>
</body>
</html>

