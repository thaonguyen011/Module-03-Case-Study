<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 14/12/2023
  Time: 09:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Netflix</title>
</head>
<body>
<p>reg form</p>
<form method="post">
    <label>
        <input type="text" name="email" value="${email}">
    </label>
    <label>
        <input type="password" name="password" id="password" onchange="check()">
    </label>
    <p id="check"></p>
    <label>
        <input type="checkbox" name="sendSpecialEmail">
    </label>
    <input type="submit" value="Submit">
</form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js">
    function check() {
        const pass = $("#password").value;

        if (pass.length < 4 || pass.length > 60) {
            $("#check").value = "Enter pass between 4 and 60 characters"
        }
    }
</script>
</body>
</html>
