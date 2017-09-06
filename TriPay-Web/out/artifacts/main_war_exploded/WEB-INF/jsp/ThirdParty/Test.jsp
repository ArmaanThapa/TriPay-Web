<%-- <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %> --%>
<html>
<head>
    <title>Pay Using VPayQwik Wallet</title>
</head>
<body>
    <form action="https://www.vpayqwik.com/ws/api/auth" method="post">
        <input name="amount" placeholder="Amount" value="110" />
        <input type="text" placeholder = "Order ID" name="transactionID" value="1234" />
        <input name="id" type="number" placeholder="id" value="1921"/>
        <input name="hash" value="EF08839A30BD665664140954AFA8E047" placeholder="hash" />
        <input type="submit" value="Continue" />
    </form>
</body>
</html>
