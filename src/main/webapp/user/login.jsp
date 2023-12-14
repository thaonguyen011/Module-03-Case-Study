<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 11/12/2023
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>

<body>

    <form method="post">
        <label>
            <input type="text" id="username" name="username" placeholder="username"  onchange="getValue()" >
        </label>
        <label>
            <input type="password" name="password" placeholder="password">
        </label>
        <c:if test="${loginAttempts > 0 && loginAttempts < 6}" >
            <p style="color: red">Wrong password ${loginAttempts} times</p>
        </c:if>
        <label>
            <input type="checkbox" name="rememberMe">
        </label>Remember me
        <label>
            <input type="submit" value="Login">
        </label>
    </form>
    <a href="${pageContext.request.contextPath}/forget-password?username=" id="forgetPass">Forget password </a>
</body>
<script>
    function getValue() {
        const username = document.getElementById("username").value;
        document.getElementById("forgetPass").href += username;
    }
</script>
</html>
