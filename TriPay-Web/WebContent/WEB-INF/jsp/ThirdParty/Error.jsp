<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Payment Gateway</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href='<c:url value="/resources/css/merchant_css.css"/>'>
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
        </div>
    </div>
</div>
</div>

<center><div class="wrapper" style="margin-top: 20px;">



        <center> <h1>Merchant Authentication Failed</h1>  </center>


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

