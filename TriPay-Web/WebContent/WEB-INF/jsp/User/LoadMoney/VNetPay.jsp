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
<body onLoad="document.vpay.submit()">
    <h3>Please wait while we're redirecting you to Vijaya Bank ...</h3>
        <form id="vpay" action="https://www.vijayabankonline.in/NASApp/BANA623WAR/BANKAWAY?Action.ShoppingMall.Login.Init=Y&BankId=029&MD=P&USER_LANG_ID=001&UserType=1&AppType=corporate" method="post" >
            <input type="hidden" name="PID" value="${fn:escapeXml(vnet.pid)}"/>
            <input type="hidden" name="AMT" value="${fn:escapeXml(vnet.amount)}"/>
            <input type="hidden" name="MERCHANT_NAME" value="${fn:escapeXml(vnet.merchantName)}"/>
            <input type="hidden" name="MID" value="${fn:escapeXml(vnet.mid)}"/>
            <input type="hidden" name="ITC" value="${fn:escapeXml(vnet.itc)}"/>
            <input type="hidden" name="CRN" value="${fn:escapeXml(vnet.crn)}"/>
            <input type="hidden" name="PRN" value="${fn:escapeXml(vnet.prn)}"/>
            <input type="hidden" name="RU" value="${fn:escapeXml(vnet.returnUrl)}"/>
        </form>
    <script>document.getElementById('vpay').submit();</script>
</body>
</html>
