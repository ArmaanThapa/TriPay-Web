<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment Confirmation</title>
    <script href="<c:url value="/resources/js/jquery.js"/>"></script>
</head>
<body>

    ${msg}
    Please wait while we're redirecting to Merchant Site
    <form method="post" id="pay" action="${url}">
        <input type="hidden" class="form-control" name="orderID" value="${fn:escapeXml(orderID)}"  />
        <input type="hidden" class="form-control" name="success" value="${fn:escapeXml(success)}"  />
        <input type="hidden" class="form-control" name="description" value="${fn:escapeXml(msg)}" />
        <input type="hidden" class="form-control" name="additionalInfo" value="${fn:escapeXml(additionalInfo)}"/>
    </form>
    <script>document.getElementById('pay').submit();</script>
</body>
</html>
