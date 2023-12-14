<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Welcome to movie web
</h1>
<br/>
<a href="${pageContext.request.contextPath}/users?action=login">Login</a><br>
<a href="${pageContext.request.contextPath}/signup/regform">Sign up</a>
</body>
</html>