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
<%
    session = request.getSession();
    String username = (String) session.getAttribute("emailSignIn");
    if (username == null) {
        username = (String) request.getAttribute("emailSignIn");
        if (username == null) {
            username = "";
        }
    }

%>

<body>
    <h1>Sign in</h1>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <c:if test="${isOnlineUser == true}">
            <p style="color: red">User online in other application</p>
        </c:if>
        <label>
            <input type="text" id="username" name="username" placeholder="username" oninput="getValue()" value="<%=username%>"  >
        </label>
        <br> <br>
        <c:if test="${auth == 0}">
            <p style="color: red" id="not">Email not sign up</p>
        </c:if>
        <label>
            <input type="password" name="password" placeholder="password">
        </label>
        <br> <br>
        <c:if test="${auth > 0 && auth < 6}" >
            <p style="color: red">Wrong password ${auth} times</p>
        </c:if>
        <c:if test="${auth == 6}" >
            <p style="color: red">Block user</p>
        </c:if>
        <label>
            <input type="checkbox" name="rememberMe" value="true">
        </label>Remember me
        <br> <br>
        <label>
            <input type="submit" value="Login">
        </label>
    </form>
    <a href="${pageContext.request.contextPath}/login/forgetPassword?username=<%=username%>" id="forgetPass1">Forget password </a> <br><br>
    <a href="${pageContext.request.contextPath}/login/forgetPassword?username=<%=username%>" id="forgetPass2">Need help </a>
    <br><br>
    <p>New to Netflix? <a href="${pageContext.request.contextPath}/main">Sign up now.</a> </p>


</body>
<script>
    function getValue() {
        const username = document.getElementById("username").value;
        document.getElementById("forgetPass1").href += username;
        document.getElementById("forgetPass2").href += username;
        document.getElementById("not").style.display = "none";

    }
</script>
</html>
