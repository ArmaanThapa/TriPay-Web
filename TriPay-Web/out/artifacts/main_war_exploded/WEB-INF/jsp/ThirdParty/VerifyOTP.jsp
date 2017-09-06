<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <script type="text/javascript">
        $(document).ready(function() {
            $("#fp_resend_otp").click(function () {
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/Api/v1/User/Android/en/Login/ForgotPassword",
                    dataType: 'json',
                    data: JSON.stringify({
                        "username": "" + $('#contactNo').val() + ""

                    }),
                    success: function (response) {
                        console.log(response);
                        if (response.code.includes("S00")) {
                            console.log("success");
                            console.log(response.details);
                            $("#fpOTP_message").html(response.details);
                        }
                    }

                });
            });
        });

    </script>
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

<center><div class="wrapper" style="margin-top: 50px;">
    <p class="title">Verify OTP</p>
    <hr>

${msg}<br>
    <sf:form modelAttribute="forgotPasswordOTP" action="/ws/api/VerifyOTP" method="post">
        <sf:input path="otp" placeholder="OTP"/><br>
        <h style="color: red;">>${error.otp}</h><br>

        <sf:input path="contactNo" hidden="hidden"/>
        <input type="submit" class="btn" value="Continue"/><br><br>
        <a style="color: #2A3F54;" id="fp_resend_otp" href="#" >Resend OTP</a>
    </sf:form>
    </p>
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
