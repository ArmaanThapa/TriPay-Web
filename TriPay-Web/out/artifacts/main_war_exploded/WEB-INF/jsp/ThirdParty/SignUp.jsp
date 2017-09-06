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

<center><div class="wrapper" style="margin-top: 20px;">
    <sf:form modelAttribute="signUp" action="/ws/api/SignUp" method="post" >
        <p class="title">Sign Up</p>
        <hr>
            <sf:input path="firstName" placeholder="Name"/>
        <h style="color: red;">${error.firstName}</h><br>
        <sf:input path="contactNo" placeholder="Mobile No."/>
        <h style="color: red;">${error.contactNo}</h><br>
            <sf:input path="email" placeholder="Email"/>
        <h style="color: red;">${error.email}</h><br>
            <sf:input path="dateOfBirth" placeholder="DOB(yyyy-mm-dd)"/>
        <h style="color: red;">${error.dateOfBirth}</h><br>
            <sf:password path="password" placeholder="Password"/>
        <h style="color: red;">${error.password}</h><br>
            <sf:password path="confirmPassword" placeholder="Confirm Password"/>
        <h style="color: red;"> ${error.confirmPassword}</h><br>

        <input class="btn" type="submit" value="Sign Up"/>
    </sf:form><br>
    Already a registered user ? <a href="<c:url value="/ws/api/getLoginPage"/>">Login</a>
</div></center><br>
        </body>
</html>
