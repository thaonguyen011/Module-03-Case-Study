<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 13/12/2023
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forget Password</title>
</head>
<body>
<h1>Forget Password</h1>
<p>A code sent to your email. Please check mail and enter code to validate</p>
<form method="post">
  <label>
    <input type="text" name="code">
  </label>
  <label>
    <input type="submit" value="Submit">
  </label>
</form>
<p>Do not receive code</p>
<a href="${pageContext.request.contextPath}/send-code-again">Send code again</a>
</body>
</html>
