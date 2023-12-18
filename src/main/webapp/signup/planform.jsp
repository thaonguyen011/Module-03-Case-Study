<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 14/12/2023
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Netflix</title>
</head>
<body>
<h1>Plan form</h1>
<form action="/signup/planform" method="post">
  <label>
    <input type="radio" name="plan" value="mobile">
  </label>Mobile
  <label>
    <input type="radio" name="plan" value="basic">
  </label>Basic
  <label>
    <input type="radio" name="plan" value="standard">
  </label>Standard
  <label>
    <input type="radio" name="plan" value="premium">
  </label>Premium
  <input type="submit" value="Submit">
</form>

</body>
</html>
