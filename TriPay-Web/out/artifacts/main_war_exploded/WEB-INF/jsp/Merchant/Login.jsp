<%@ page contentType="text/html; charset=utf-8" language="java"
         import="java.sql.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>VPayQwik | Merchant Login</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="icon" href='<c:url value="/resources/images/favicon.png"/>'
          type="image/png" />
    <link rel="stylesheet"
          href="<c:url value="/resources/css/font-awesome.min.css"/>">

    <!-- Optional theme -->
    <link rel="stylesheet"
          href=<c:url value="/resources/css/bootstrap-theme.min.css"/>">

    <link href="<c:url value="/resources/css/admin.css"/>" rel='stylesheet'
          type='text/css'>
    <link rel="stylesheet"
          href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <script
            src="<c:url value="/resources/js/jquery.js"/>"></script>
    <script
            src="<c:url value="/resources/js/bootstrap.js"/>"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/signup.js"></script>
    <style>
        .no-js #loader {
            display: none;
        }

        .js #loader {
            display: block;
            position: absolute;
            left: 100px;
            top: 0;
        }

        .se-pre-con {
            position: fixed;
            left: 0px;
            top: 0px;
            width: 100%;
            height: 100%;
            z-index: 9999;
            background: url(/images/pq_large.gif) center no-repeat #fff;
        }
    </style>
    <script src="<c:url value='/resources/js/modernizr.js'/>"></script>

    <script type="text/javascript">
        $(window).load(function() {
            $(".se-pre-con").fadeOut("slow");
        });
    </script>

</head>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/js/header.js"></script>
<body>
<div class="se-pre-con"></div>
<div class="oneset">

    <nav class="navbar navbar-default"
         style="background: white; min-height: 80px; border-radius: 0; border-color: white; margin-bottom: 15px;">
        <c:if test="${msg ne null}">
        <div class="alert alert-info"><center>${msg}</center></div>
        </c:if>
        <div class="container" style="margin-top: 8px;">
            <div class="navbar-header">
                <a class="navbar-brand" href="#"><img
                        src="${pageContext.request.contextPath}/resources/images/vijayalogo.png"
                        alt="" style="width: 250px"></a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
                <ul class="nav navbar-nav navbar-right">

                        <sf:form modelAttribute="login" method="post" action="/Merchant/Home">
                          <div class="group">
                            <sf:input path="username" required="required"/>
                              <span
                                      class="highlight"></span> <span class="bar"></span>
                              <label>Email</label>
                            <p class="error">${error.mobileNumber}</p>
                      </div>
                          <div class="group" style="margin-right: 20px;">
                            <sf:password path="password" required="required"/>
                              <span
                                      class="highlight"></span> <span class="bar"></span>
                              <label>Password</label>
                            <p class="error">${error.password}</p>
                              </div>
                            <input type="submit" value="Login" class="btn"
                                   style="margin-top: 12px; margin-left: 20px; background: #ec2029; color: #FFFFFF; border-radius: 0; width: 100px; margin-left: 10px;">
            </sf:form>

                </ul>
            </div>

        </div>

    </nav>


</div>

<div class="navbar navbar-fixed-bottom" id="footer" style="background: rgba(255, 255, 255, 0.44)">
    <div class="container-fluid">
        <div class="span pull-left">
            <a href="#"><img
                    src="${pageContext.request.contextPath}/resources/images/vijayafooter2.jpg"
                    style="height: 30px; margin-top: 10px;"></a></a>
        </div>
        <div class="span pull-right">
            <p><img
                    src="${pageContext.request.contextPath}/resources/images/msewafooter.png"
                    style="height: 40px; margin-top: 10px;">&copy; Copyright MSewa Software Pvt. Ltd.</p>
        </div>
    </div>
</div>

</body>
</html>
