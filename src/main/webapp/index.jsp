<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Netflix</title>
</head>
<body>
<h1>Welcome to movie web
</h1>
<br/>
<a href="${pageContext.request.contextPath}/login">Login</a>
<br>
<br>
<a href="${pageContext.request.contextPath}/signup/regform">Sign up</a>
<br>
<br>
<form action="${pageContext.request.contextPath}/main" method="post">
    <label>
        <input type="text" name="email" placeholder="Enter email" oninput="change()">
        <c:if test="${emailRegex == false}">
            <p style="color:red" id="change">Wrong email. Enter again</p>
        </c:if>
        <c:if test="${isOnlineUser == true}">
            <p style="color:red" id="change1">User is online in other application</p>
        </c:if>
    </label>
    <br>
<input type="submit" value="Start">
</form>
<script>

    function change() {
        var paragraphElement = document.getElementById("change");
        paragraphElement.style.display = "none"
        var paragraphElement1 = document.getElementById("change1");
        paragraphElement1.style.display = "none"
    }
</script>
</body>

</html>