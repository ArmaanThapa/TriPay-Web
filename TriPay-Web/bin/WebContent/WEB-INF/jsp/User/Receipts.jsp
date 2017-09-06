<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<sec:csrfMetaTags/>
<title>VPayQwik | Receipts</title>
<link href='<c:url value='/resources/css/font-family.min.css'/>'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet"
	href="<c:url value='/resources/css/font-awesome.min.css'/>" type='text/css'>

<!-- Optional theme -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap-theme.min.css'/>" type='text/css'>

<link href="<c:url value="/resources/css/css_style.css"/>"
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.min.css'/>" type='text/css'>
<script
	src="<c:url value='/resources/js/jquery.js'/>"></script>
<script
	src="<c:url value='/resources/js/bootstrap.js'/>"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/userdetails.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/header.js"></script>
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>' type="image/png" />

<link href='<c:url value='/resources/css/font-family.css'/>'
	rel='stylesheet' type='text/css'>


<link href='<c:url value="/resources/css/css_style.css"/>'
	rel='stylesheet' type='text/css'>

</head>

<body>
	
	<jsp:include page="/WEB-INF/jsp/User/Header.jsp" />
	<jsp:include page="/WEB-INF/jsp/User/Menu.jsp" />

	<!-----------------end navbar---------------------->

	<!------------- end main-------------------->

	<div class="background"></div>
	<!---blue box---->


	<div class="container" id="box">
		<div id="row">
			<div class="col-md-12 " id="receipts">
				<table class=" table  table-hover">
					<thead>
						<tr >
							<th><center>DATE</center></th>
							<th><center>DESCRIPTION</center></th>
							<th><center>AMOUNT</center></th>
							<th><center>STATUS</center></th>
						</tr>
</thead>
					<tbody style="color: gray"> 
						<c:forEach items="${transactions}" var="transaction">
							<tr>
								<td><center><c:out value="${transaction.date}" escapeXml="true"/></center></td>
								<td><center><c:out value="${transaction.description}" escapeXml="true"/>
										<b>Transaction ID:</b> <c:out value="${transaction.transactionRefNo}" escapeXml="true"/>
									</center></td>
								<td><center><c:out value="${transaction.amount}" escapeXml="true"/></center></td>
								<td><center>
								<c:if test='${transaction.status eq "Initiated"}'>
								<span class="label label-warning"><c:out value="${transaction.status}" escapeXml="true"/></span>
								</c:if>
								<c:if test='${transaction.status eq "Success"}'>
								<span class="label label-success"><c:out value="${transaction.status}" escapeXml="true"/></span>
								</c:if>
								<c:if test='${transaction.status eq "Processing" }'>
								<span class="label label-primary"><c:out value="${transaction.status}" escapeXml="true"/></span>
								</c:if>
								<c:if test='${transaction.status eq "Failed"}'>
								<span class="label label-danger"><c:out value="${transaction.status}" escapeXml="true"/></span>
								</c:if>
								</center></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!---col-md-12-->
		</div>
		<!--row-->
	</div>
	<!-------END BOX----------->

	<!---end row-->
	<!----end container-->
	<jsp:include page="/WEB-INF/jsp/User/Footer.jsp" />

</body>
</html>



