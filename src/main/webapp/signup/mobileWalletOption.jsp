<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 14/12/2023
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Netflix</title>
</head>
<body>
<h1>Mobile Wallet Option</h1>
<form action="/signup/mobileWalletOption" method="post">
  <label>
    <input type="text" name="phone" placeholder="Mobile number" required>
  </label>
  <p>Plan: ${plan}<a href="/signup/editPlan"> Change</a></p>
  <label>
    <input type="checkbox" name="agree" value="agree" required>
  </label>I agree
  <input type="submit" value="Submit">
</form>

</body>
</html>
