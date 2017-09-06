<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Verify OTP</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href='<c:url value="/resources/css/merchant_css.css"/>'>
    <link rel="icon" href='<c:url value="/resources/images/favicon.png"/>' type="image/png" />
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">
    <link href='<c:url value="/resources/css/style_main.css"/>'/>
    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css"/>'>
    <script src='<c:url value="/resources/js/jquery.js"/>'></script>
    <script src='<c:url value="/resources/js/bootstrap.js"/>'></script>

</head>

<body background='<c:url value="/resources/images/merchantbg.jpg"/>'/>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="payqwik">
                <img src='<c:url value="/resources/images/vpaylogo.png"/>'/>
            </div>
            <div class="mer">
                <img src='<c:url value="${image}"/>'/>
            </div>
        </div>
    </div>
</div>
</div>

<center><div class="wrapper" style="margin-top: 20px;">

            <center><div class="wrapper" style="margin-top: 20px;">
                <div class="login">
                    <p class="title">Password Regenerate!!</p>
                    <hr>
                    <p style="font-size: 20px; margin-top:-15px; margin-bottom: 10px;">Hello User,<br>
                        Please Enter OTP Code received on mobile number</p>

                            <sf:form modelAttribute="verifyOTP" action="/ws/api/VerifyMobile" method="post" style="color: red;">
                    <sf:input path="otp" placeholder="Enter OTP Code"/>
                    <sf:input path="contactNo" hidden="hidden" /><br>
                                <c:if test="${msg ne null}">
                                    <c:out value="${msg}" escapeXml="true" />
                                </c:if>
                                <c:if test="${error ne null}">
                                    <c:out value="${error.otp}" escapeXml="true" />
                                </c:if><br>
                    <input class="btn" type="submit"  value="Continue"/>
                </sf:form>
                </div>
            </div></center>
            </div>
</center></body>
</html>
