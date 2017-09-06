<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>VpayQwik | Dashboard</title>
    <link rel="stylesheet" href="<c:url value='/merchant_css.css'/>">
    <script src="<c:url value='/resources/js/jquery.js'/>"></script>
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
<body background='<c:url value="/resources/images/merchantbg.jpg"/>'>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="payqwik">
                <img src="<c:url value="/resources/images/vpaylogo.png"/>">
            </div>
            <div class="mer">
                <img src="<c:url value='${sessionScope.image}'/>">
            </div>
        </div>
    </div>




<center><div class="wrapper" style="margin-top: 20px;">
    <c:if test="${msg ne null}">
        <div class="alert alert-info">${msg}</div>
    </c:if>
    <p>Welcome ${fullName} to VpayQwik<br>
        your balance is  ${balance}</p>
    <b><sf:form modelAttribute="pgRequest" action="/ws/api/Process" method="post"></b>

        <div class="row" style="margin-top: 5px;">
            <div class="col-md-offset-4 col-md-4">
        <table border="0">
            <tbody>
            <tr>
                <td><span>Wallet Amount </span></td>
                <td> <sf:input path="walletAmount" readonly="true" style="margin-left: 10;"/></td>
            </tr><br>
            <tr>
                <td><span>Amount to Load</span></td>
                <td> <sf:input path="amountToLoad" placeholder="Amount To Load" readonly="true" style="margin-left: 10;"/><br></td>
            </tr><br>
            <tr>
                <td><span>Net Amount</span></td>
                <td> <sf:input path="netAmount" placeholder="Net Amount" readonly="true" style="margin-left: 10;"/><br></td>
            </tr><br>
            </tbody>
        </table>  <br>

    <input class="btn" type="submit" value="continue"/>
    </sf:form>
            </div>
</div>
         </div>
</body>
</html>
