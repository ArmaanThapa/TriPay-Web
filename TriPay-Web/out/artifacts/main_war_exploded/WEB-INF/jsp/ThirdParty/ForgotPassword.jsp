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
    <title>VPayQwik | Login</title>
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

<center><div class="wrapper" style="margin-top: 55px;">
    <p class="title">Forgot Password</p>
    <hr>

    <sf:form modelAttribute="forgotPassword"  action="/ws/api/ForgotPassword" method="post">
                <sf:input path="username" placeholder="Mobile Number"/><br>
        <h style="color: red;">${error.username}</h><br>
                <input type="submit" style="border-radius: 0; background: red; color: white" class="btn" value="Continue"/>
            </sf:form>
</div></center>
<div class="navbar navbar-fixed-bottom" id="footer">
    <div class="container-fluid">

        <div class="span pull-left">
            <a href="#"><img src='<c:url value="/resources/images/vijaya_logo.png"/>'
                             style="height: 30px;"></a>
        </div>
        <div class="span pull-right">
            <p>&copy; Copyright MSewa Software Pvt. Ltd.</p>
        </div>
    </div>
</div>



</body>
</html>
