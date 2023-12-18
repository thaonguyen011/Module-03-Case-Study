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
<h1>Step 3 of 3</h1>
<h1>Choose payment form</h1>
<form action="${pageContext.request.contextPath}/signup/paymentPicker" method="post">
  <label>
    <input type="radio" name="paymentForm" value="credit">Credit
  </label>
  <label>
    <input type="radio" name="paymentForm" value="wallet">Wallet
  </label>
  <input type="submit" value="Submit">
</form>
<script>
  localStorage.setItem("lastSignUpStep", "Step3");

  let dataSend = localStorage.getItem("lastSignUpStep");
  let xhr = new XMLHttpRequest();
  xhr.open('POST', '/signup/paymentPicker', true);

</script>
</body>
</html>
