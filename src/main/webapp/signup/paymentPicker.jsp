<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 14/12/2023
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Netflix</title>
</head>
<body>
<h1>Choose payment form</h1>
<form method="post">
  <label>
    <input type="radio" name="paymentForm" value="credit">Credit
  </label>
  <label>
    <input type="radio" name="paymentForm" value="wallet">Wallet
  </label>
  <input type="submit" value="Submit">
</form>

</body>
</html>
